package game;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

import game.ArtifactCards.ArtifactCard;
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
              Game game = Game.getInstance();

              if (!(board.getRound() == 4)) {
                game.reopenOnlineBoard(token, board);
              } else {
                game.closeOnlineBoard();
              }
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
              //board.getTokens().get(Integer.parseInt(index)).addGold(1);
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
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("SellPotion")) {
              System.out.println("Inside SellPotion");
              String potionName = messageParts[3];
              board.getTokens().get(Integer.parseInt(index)).sellPotion(potionName);
              game.reopenOnlineBoard(token, board);
            }
            if (action.equals("PublishTheory")) {
              System.out.println("Inside PublishTheory");
              try {
                int ingredientIndex = Integer.parseInt(messageParts[3]);
                int markerIndex = Integer.parseInt(messageParts[4]);
                Ingredient ingredient = board.getStaticIngredients().get(ingredientIndex);
                AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(markerIndex);
                board.getTokens().get(Integer.parseInt(index)).publishTheory(board, ingredient, alchemyMarker);
                game.reopenOnlineBoard(token, board);
              } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
                continue;
              }
            }
            if (action.equals("DebunkTheory")) {
              System.out.println("Inside DebunkTheory");
              try {
                String theoryName = messageParts[3];
                Theory theory = board.getTheoryByName(theoryName);
                int ingredientIndex = Integer.parseInt(messageParts[4]);
                int aspectIndex = Integer.parseInt(messageParts[5]);
                Ingredient ingredient = board.getStaticIngredients().get(ingredientIndex);
                Aspect selectedAspect = ingredient.getAlchemyMarker().getAspectList().get(aspectIndex);
                board.getTokens().get(Integer.parseInt(index)).debunkTheory(board, theory, selectedAspect);
                game.reopenOnlineBoard(token, board);
              } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
                continue;
              }
            }

            if (action.equals("BuyArtifact")) {
              System.out.println("Inside BuyArtifact");
              try {
                if (messageParts[4].equals("Wisdom")) {
                  board.addArtifactCardToToken(board.getTokens().get(Integer.parseInt(index)), board.geArtifactCardByName("Wisdom Idol"));
                } else if (messageParts[4].equals("Elixir")) {
                  board.addArtifactCardToToken(board.getTokens().get(Integer.parseInt(index)), board.geArtifactCardByName("Elixir of Insight"));
                } else if (messageParts[4].equals("Printing")) {
                  board.addArtifactCardToToken(board.getTokens().get(Integer.parseInt(index)), board.geArtifactCardByName("Printing Press"));
                } else {
                  board.addArtifactCardToToken(board.getTokens().get(Integer.parseInt(index)), board.geArtifactCardByName("Magic Mortar"));
                }
                board.getTokens().get(Integer.parseInt(index)).addGold(-Integer.parseInt(messageParts[3]));
                game.reopenOnlineBoard(token, board);
              } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
                continue;
              }
            }

            if (action.equals("UseArtifact")) {
              System.out.println("Inside UseArtifact");
              try {
                ArtifactCard artifactCard;
                if( messageParts[3].equals("Wisdom")) { artifactCard = board.geArtifactCardByName("Wisdom Idol");}
                else if(messageParts[3].equals("Elixir")) { artifactCard = board.geArtifactCardByName("Elixir of Insight");
                board.getTokens().get(Integer.parseInt(index)).useArtifactCard(artifactCard, board);}
                else if(messageParts[3].equals("Printing")) { artifactCard = board.geArtifactCardByName("Printing Press");
                board.getTokens().get(Integer.parseInt(index)).useArtifactCard(artifactCard, board);}
                else{ artifactCard = board.geArtifactCardByName("Magic Mortar");
                board.getTokens().get(Integer.parseInt(index)).useArtifactCard(artifactCard, board);
              }
                
                game.reopenOnlineBoard(token, board);
              } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "samet hata");
                continue;
              }
            }

            if (action.equals("ElixirReorder")) {
              System.out.println("Inside UseArtifact");
              try {
                String firstIngredient = messageParts[2];
                String secondIngredient = messageParts[3];
                String thirdIngredient = messageParts[4];

                Ingredient firstIng = board.getIngredientByName(firstIngredient);
                Ingredient secondIng = board.getIngredientByName(secondIngredient);
                Ingredient thirdIng = board.getIngredientByName(thirdIngredient);

                board.reorderIngredients(firstIng, secondIng, thirdIng);
                game.reopenOnlineBoard(token, board);
              } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "samet hata");
                continue;
              }

            // TODO: Add other actions
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
