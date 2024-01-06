package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import ui.WaitingJFrame;

public class Server {

  private static final int PORT = 5001;
  private static final List<ClientHandler> clients = new ArrayList<>();
  public WaitingJFrame waitingFrame;

  public Server() {}

  public void startServer() {
    String ip = getIp();
    try {
      ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getByName(ip));
      System.out.println("Server waiting for clients on port " + PORT);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection established with client");

        // Create a new thread to handle the client
        ClientHandler clientHandler = new ClientHandler(clientSocket);
        clientHandler.setWaitingFrame(waitingFrame);
        clients.add(clientHandler);
        new Thread(clientHandler).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getIp() {
    String ip = "127.0.0.1";
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

  public void setWaitingFrame(WaitingJFrame waitingFrame) {
    this.waitingFrame = waitingFrame;
  }

  private static class ClientHandler implements Runnable {

    private Socket clientSocket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private WaitingJFrame waitingFrame;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;
      try {
        dataIn = new DataInputStream(clientSocket.getInputStream());
        dataOut = new DataOutputStream(clientSocket.getOutputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      try {
        while (true) {
          String clientMessage = dataIn.readUTF();
          if (clientMessage.split(" ")[0].equals("credentials")) {
            waitingFrame.addPlayer(clientMessage.split(" ")[1], clientMessage.split(" ")[2]);
          }
          // Broadcast the message to all clients
          broadcastMessage(clientMessage);
        }
      } catch (IOException e) {
        // Handle client disconnection
        System.out.println("Client disconnected");
        clients.remove(this);
        close();
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
  }
}
