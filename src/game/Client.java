package game;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Serializable {
  private static final long serialVersionUID = 4L;

  public Client() {}

  public void connect(String ip, String username, String avatar) {
    try {
      Socket clientSocket = new Socket();
      clientSocket.connect(new InetSocketAddress(ip, 5001), 1000);
      DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
      ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
      ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream());

      dataOut.writeUTF("credentials " + username + " " + avatar);
      dataOut.flush();

      new Thread(() -> {
        try {
          while (true) {
            Object serverObject = objectIn.readObject(); // The object that comes from server
            System.out.println("Server object: " + serverObject.toString());
            // TODO: HANDLE THE BOARD THAT COMES FROM SERVER AND OPEN A NEW FRAME
            if (serverObject instanceof Board) {
              Board board = (Board) serverObject;
              System.out.println("board object: " + board.toString());
            }
          }
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      })
        .start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
