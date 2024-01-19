package game;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import ui.WaitingJFrame;

public class Server implements Serializable {

  private static final long serialVersionUID = 9L;

  private static final int PORT = 5001;
  private static final List<ClientHandler> clients = new ArrayList<>();
  public WaitingJFrame waitingFrame;
  private static final HashMap<String, String> credentials = new HashMap<>(); // username, avatar
  private String username;
  private Token token;
  private Board board;

  public Server() {
  }

  public void startServer(String username, String avatar) {
    this.username = username;
    credentials.put(username, avatar); // Host's credentials

    String ip = getIp();

    try {
      ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getByName(ip));
      System.out.println("Server waiting for clients on port " + PORT);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection established with client");

        ClientHandler clientHandler = new ClientHandler(clientSocket);
        clientHandler.setWaitingFrame(waitingFrame);
        clientHandler.setServer(this);
        clients.add(clientHandler);
        new Thread(clientHandler).start(); // Clients starts to listen.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void startGame() {
    // Send the board to all clients
    Board board = new Board(credentials);
    this.board = board;
    for (Token token : board.getTokens()) {
      if (token.getUsername().equals(username)) {
        this.token = token;
      }
    }

    for (ClientHandler client : clients) {
      client.setBoard(board);
      client.setToken(token);
      client.sendObject(board);
    }
    Game game = Game.getInstance();
    game.openCountDownFrame(token, board);
  }

  public String getIp() {
    String ip = "127.0.0.1"; // Dummy IP address
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

        while (inetAddresses.hasMoreElements()) {
          InetAddress inetAddress = inetAddresses.nextElement();
          if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
            ip = inetAddress.getHostAddress();
            System.out.println("Your IP Address on the local network: " + inetAddress.getHostAddress());
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return ip;
  }

  public HashMap<String, String> getCredentials() {
    return credentials;
  }

  public void setWaitingFrame(WaitingJFrame waitingFrame) {
    this.waitingFrame = waitingFrame;
  }

  public void publishAction(String action) {
    for (ClientHandler client : clients) {
      client.sendMessage(action);
    }
  }

  // Inner class !!
  private static class ClientHandler implements Runnable {

    private Socket clientSocket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    private WaitingJFrame waitingFrame;
    private Server server;
    private Board board;
    private Token token;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;

      try {
        dataIn = new DataInputStream(clientSocket.getInputStream());
        dataOut = new DataOutputStream(clientSocket.getOutputStream());
        objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
        objectIn = new ObjectInputStream(clientSocket.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      try {
        while (true) {
          String clientMessage = dataIn.readUTF();
          System.out.println("Client message: " + clientMessage);
          String[] messageParts = clientMessage.split(" ");
          Game game = Game.getInstance();
          for (String part : messageParts) {
            System.out.println(part);
          }

          if (messageParts[0].equals("credentials")) {
            String username = messageParts[1];
            String avatar = messageParts[2];
            server.getCredentials().put(username, avatar);
            waitingFrame.addPlayer(username, avatar);
          }

          if (messageParts[0].equals("Action")) {
            broadcastMessage(clientMessage);
            String index = messageParts[1];
            String action = messageParts[2];
            server.publishAction(action);

            System.out.println("Action: " + action);
            System.out.println("Index: " + index);

            if (action.equals("EndTurn")) {
              System.out.println("Inside End turn");
              board.endTurn();
              if (!(board.getRound() == 4)) {
                game.reopenOnlineBoard(token, board);
              } else {
                game.closeOnlineBoard();
              }
            }

            if (action.equals("ForageForIngredient")) {
              System.out.println("Inside ForageForIngredient");
              board.getTokens().get(Integer.parseInt(index)).forageForIngredient(board);
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("DeductionBoard")) {
              System.out.println("Inside DeductionBoard");
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("TransmuteIngredient")) {
              System.out.println("Inside TransmuteIngredient");
              String ingredientName = messageParts[3];
              System.out.println(ingredientName);
              board.getTokens().get(Integer.parseInt(index)).transmuteIngredient(ingredientName);
              board.getTokens().get(Integer.parseInt(index)).addGold(1);
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("MakeExperiment")) {
              System.out.println("Inside MakeExperiment");
              String ingredientName = messageParts[3];
              String ingredientName2 = messageParts[4];
              String testOnSelf = messageParts[5];
              board.getTokens().get(Integer.parseInt(index)).makeExperiment(ingredientName, ingredientName2,
                  Boolean.valueOf(testOnSelf));
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("SellPotion")) {
              System.out.println("Inside SellPotion");
              String potionName = messageParts[3];
              board.getTokens().get(Integer.parseInt(index)).sellPotion(potionName);
              game.reopenOnlineBoard(token, board);
            }
            // TODO: Add other actions
          }
        }
      } catch (IOException e) {
        // Handle client disconnection
        System.out.println("Client disconnected");
        clients.remove(this);
        close();
      }
    }

    public void sendObject(Object object) {
      try {
        objectOut.writeObject(object);
        objectOut.flush();
        System.out.println("sended the object.");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public void sendMessage(String message) {
      try {
        dataOut.writeUTF(message);
        dataOut.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void broadcastMessage(String message) {
      for (ClientHandler client : clients) {
        if (client != this) {
          client.sendMessage(message);
        }
      }
    }

    private void broadcastObject(Object object) {
      for (ClientHandler client : clients) {
        if (client != this) {
          client.sendObject(object);
        }
      }
    }

    private void close() {
      try {
        dataIn.close();
        dataOut.close();
        clientSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void setWaitingFrame(WaitingJFrame waitingFrame) {
      this.waitingFrame = waitingFrame;
    }

    private void setServer(Server server) {
      this.server = server;
    }

    private void setBoard(Board board) {
      this.board = board;
    }

    private void setToken(Token token) {
      this.token = token;
    }
  }
}
