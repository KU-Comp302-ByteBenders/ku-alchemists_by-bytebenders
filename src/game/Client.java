package game;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import ui.interfaces.ChangeableVisibility;

public class Client implements Serializable {

  private static final long serialVersionUID = 4L;
  DataInputStream dataIn;
  DataOutputStream dataOut;
  ObjectOutputStream objectOut;
  ObjectInputStream objectIn;
  public Board board;
  public State state;
  public Token token;
  public String username;
  ChangeableVisibility frame;
  Game game;

  public Client() {
  }

  public void connect(String ip, String username, String avatar, ChangeableVisibility frame) {
    this.username = username;
    this.frame = frame;
    try {
      Socket clientSocket = new Socket();
      clientSocket.connect(new InetSocketAddress(ip, 5001), 1000);
      DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
      this.dataIn = dataIn;
      DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
      this.dataOut = dataOut;
      ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
      this.objectOut = objectOut;
      ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());
      this.objectIn = objectIn;

      dataOut.writeUTF("credentials " + username + " " + avatar);
      dataOut.flush();

      Game game = Game.getInstance();
      this.game = game;
      game.setClient(this);

      new Thread(new ObjectReceiver()).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendAction(String action) {
    try {
      dataOut.writeUTF(action);
      dataOut.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void listenActions() {
    new Thread(new Listener()).start();
  }

  private class ObjectReceiver implements Runnable {

    @Override
    public void run() {
      try {
        while (board == null) {
          Object serverObject = objectIn.readObject(); // The object that comes from server
          if (serverObject instanceof Board) {
            board = (Board) serverObject;
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      } finally {
        for (Token token : board.getTokens()) {
          if (token.getUsername().equals(username)) {
            Client.this.token = token;
          }
        }
        frame.changeVisible(false);
        game.openCountDownFrame(token, board);
        listenActions();
      }
    }
  }

  private class Listener implements Runnable {

    @Override
    public void run() {
      try {
        while (true) { // Listens messages
          String message = dataIn.readUTF();
          System.out.println("Message from server: " + message);

          String[] messageParts = message.split(" ");

          if (messageParts[0].equals("Action")) {
            String index = messageParts[1];
            String action = messageParts[2];

            System.out.println("index: " + index);
            System.out.println("action: " + action);

            if (action.equals("EndTurn")) {
              System.out.println("Inside EndTurn");
              board.endTurn();
              System.out.println("whose turn:" + board.getWhoseTurn());
              Game game = Game.getInstance();
              game.reopenOnlineBoard(token, board);
            }

            if (action.equals("ForageForIngredient")) {
              System.out.println("Inside ForageForIngredient");
              board.getTokens().get(Integer.parseInt(index)).forageForIngredient(board);
              Game game = Game.getInstance();
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("DeductionBoard")) {
              System.out.println("Inside DeductionBoard");
              Game game = Game.getInstance();
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("TransmuteIngredient")) {
              System.out.println("Inside TransmuteIngredient");
              String ingredientName = messageParts[3];
              System.out.println(ingredientName);
              board.getTokens().get(Integer.parseInt(index)).transmuteIngredient(ingredientName);
              Game game = Game.getInstance();
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("MakeExperiment")) {
              System.out.println("Inside MakeExperiment");
              String ingredientName = messageParts[3];
              String ingredientName2 = messageParts[4];
              String testOnSelf = messageParts[5];
              board.getTokens().get(Integer.parseInt(index)).makeExperiment(ingredientName, ingredientName2,
                  Boolean.valueOf(testOnSelf));
              System.out.println(ingredientName);
              System.out.println(ingredientName2);
              System.out.println(testOnSelf);
              game.reopenOnlineBoard(token, board);
            }
            // TODO: add other actions
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
