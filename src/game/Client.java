package game;

import ui.OnlineBoardJFrame;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Serializable {
  private static final long serialVersionUID = 4L;
  public Board board;
  public State state;
  public Token token;

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
          while (board == null || state == null) {
            Object serverObject = objectIn.readObject(); // The object that comes from server
            System.out.println("Server object: " + serverObject.toString());
            if (serverObject instanceof Board) {
              board = (Board) serverObject;
              System.out.println("Board object: " + board.toString());
            } else if (serverObject instanceof State) {
              state = (State) serverObject;
              System.out.println("State object: " + state.toString());
            }
          }
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        } finally {
          for(Token token : board.getTokens()) {
            if(token.getUsername().equals(username)) {
              this.token = token;
            }
          }
          new OnlineBoardJFrame(token, board, state);
        }
      })
              .start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
