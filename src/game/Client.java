package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public Client() {}

  public void connect(String ip, String username, String avatar) {
    try {
      Socket clientSocket = new Socket();
      clientSocket.connect(new InetSocketAddress(ip, 5001), 1000);
      DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());

      dataOut.writeUTF("credentials " + username + " " + avatar);
      dataOut.flush();

      new Thread(() -> {
        try {
          while (true) {
            String serverMessage = dataIn.readUTF(); // The message that comes from server
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      })
        .start();

      new Thread(() -> {
        while (true) {
          // Client can publish a message. (preferably a log)
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
