package game;

import game.ArtifactCards.ArtifactCard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import ui.*;
import ui.interfaces.BoardFrame;
import ui.interfaces.ChangeableVisibility;

/*
 * This class is the Controller class.
 * Also a Singleton class.
 */
public class Game implements Serializable {

  private static final long serialVersionUID = 6L;
  private Boolean activateBoard;
  Server server;
  Client client;

  OnlineBoardJFrame onlineBoardJFrame;

  private static Game instance = null;

  public static Game getInstance() {
    if (instance == null) {
      instance = new Game();
    }
    return instance;
  }

  private Game() {}

  public void openCountDownFrame(Token token, Board board) {
    CountDownFrame countDownJFrame = new CountDownFrame(token, board);
  }

  public void openOnlineBoard(Token token, Board board, ChangeableVisibility frame) {
    frame.changeVisible(false);
    OnlineBoardJFrame onlineBoardJFrame = new OnlineBoardJFrame(token, board);
    this.onlineBoardJFrame = onlineBoardJFrame;
  }

  public void reopenOnlineBoard(Token token, Board board) {
    OnlineBoardJFrame onlineBoardJFrame = new OnlineBoardJFrame(token, board);
    this.onlineBoardJFrame.dispose();
    this.onlineBoardJFrame = onlineBoardJFrame;
  }

  public void openMainMenu() {
    // Open the main menu
    MainMenuJFrame mainMenu = new MainMenuJFrame();
  }

  public void openLogin(ChangeableVisibility frame) {
    // Open the login screen
    LoginJFrame loginScreen = new LoginJFrame();
    // Close the main menu
    frame.changeVisible(false);
  }

  public void openLoginSingle(ChangeableVisibility frame, boolean isClient) {
    // Open the login screen
    LoginOneUserJFrame loginScreen = new LoginOneUserJFrame(isClient);
    // Close the main menu
    frame.changeVisible(false);
  }

  public void openGameMode(ChangeableVisibility frame) {
    // Open the game mode screen
    GameModeJFrame gameMode = new GameModeJFrame();
    // Close the login screen
    frame.changeVisible(false);
  }

  public void openWaitingRoom(ChangeableVisibility frame, String username, String avatar) {
    Server server = new Server();
    this.server = server;
    String ip = server.getIp();

    Thread networkThread = new Thread(() -> {
      server.startServer(username, avatar);
    });
    networkThread.start();

    WaitingJFrame waitingRoom = new WaitingJFrame(ip, username, avatar);
    server.setWaitingFrame(waitingRoom);
    frame.changeVisible(false);
  }

  public void startGame(ArrayList<String> playerNames, ArrayList<String> playerAvatars, LoginJFrame loginScreen) {
    ArrayList<Token> tokens = new ArrayList<>();
    // Assuming you want to handle a dynamic number of players
    int numPlayers = playerNames.size();
    System.out.println(numPlayers);

    if (numPlayers < 2) {
      JOptionPane.showMessageDialog(
        loginScreen,
        "Add at least 1 more player before starting the game.",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    } else {
      // You may need to adjust this part based on your actual game logic
      // For now, it assumes a fixed number of players (2)
      // Assuming you want to access the first two players for now

      for (int i = 0; numPlayers > i; i++) {
        Token token1 = new Token(playerNames.get(i), playerAvatars.get(i), playerAvatars.get(i));
        System.out.println(token1.getUsername());
        System.out.println(tokens);
        tokens.add(token1);
        System.out.println("sfsgdsg");
      }
      System.out.println(tokens);

      Board board = new Board(tokens);
      System.out.println("aaaaa");
      loginScreen.setVisible(false);
    }
  }

  public void startGameOnline(ChangeableVisibility frame) {
    frame.changeVisible(false);
    server.startGame();
  }

  public void publishAction(String action) {
    if (server == null) {
      client.sendAction(action);
    } else {
      server.publishAction(action);
    }
  }

  public void openPauseMenu() {}

  public void closePauseMenu() {}

  public static void openPublishMenu(BoardFrame boardFrame, Board board, State state, Token token1) {
    // Open the publish theory action menu
    PublishTheoryJFrame publishTheoryJFrame = new PublishTheoryJFrame(boardFrame, board, state, token1);
  }

  public static void openDebunkMenu(BoardFrame boardFrame, Board board, State state, Token token1) {
    // Open the debunk theory action menu
    DebunkTheoryJFrame debunkTheoryJFrame = new DebunkTheoryJFrame(boardFrame, board, state, token1);
  }

  public static void openPublicationTrack(JFrame boardJFrame, Board board) {
    // Open the publish theory action menu
    PublicationTrackJFrame publicationTrackJFrame = new PublicationTrackJFrame(board);
  }

  public static void openPauseMenu(JFrame board) {
    PauseMenuJframe pauseMenuJframe = new PauseMenuJframe(board);
  }

  public static void closePauseMenu(JFrame pauseMenu) {
    pauseMenu.dispose();
  }

  public static void inactivateBoard(ChangeableVisibility board) {
    board.changeVisible(false);
  }

  public static void activateBoard(ChangeableVisibility board) {
    board.changeVisible(true);
  }

  public static void openArtifactBuyScreen(BoardFrame boardFrame, Board board, Token token, State state) {
    BuyArtifactFrame buyArtifactFrame = new BuyArtifactFrame(boardFrame, board, token, state);
    buyArtifactFrame.setVisible(true);
  }

  public static void closeArtifactBuyScreen(JFrame pauseMenu) {
    pauseMenu.dispose();
  }

  public static void activateTransmuteIngredientFrame(
    ArrayList<Ingredient> displayedIngredients,
    Board mainBoard,
    BoardFrame boardFrame,
    State state
  ) {
    TransmuteIngredientFrame transmuteJFrame = new TransmuteIngredientFrame(
      displayedIngredients,
      mainBoard,
      boardFrame,
      state
    );
  }

  public static void openTriangleBoard(JButton button) {
    TriangleBoardJFrame triangleBoardJFrame = new TriangleBoardJFrame(button);
  }

  public static void openHelpScreen() {
    HelpJFrame helpJFrame = new HelpJFrame();
  }

  public static void openExperimentFrame(Token token, Board board, BoardFrame boardFrame, State state) {
    MakeExperimentJFrame makeExperimentJFrame = new MakeExperimentJFrame(token, board, boardFrame, state);
  }

  public static void openPotionJFrame(Token token, Board board, BoardFrame boardFrame, State state) {
    PotionJFrame potionJFrame = new PotionJFrame(token, board, boardFrame, state);
  }

  public static void openWisdomIdolConfirmationDialog(ArtifactCard artifactCard) {
    // Add confirmation dialog
    int confirmed = JOptionPane.showConfirmDialog(
      null,
      "Is the Theory Owner sure that they want to apply the Wisdom Idol effect?",
      "Confirmation",
      JOptionPane.YES_NO_OPTION
    );

    if (confirmed == JOptionPane.YES_OPTION) {
      // If the user confirmed, set the flag to true
      artifactCard.setToBeAppliedFlag(true);
    }
  }

  public static void controlRoundAction(BoardFrame boardFrame, State state, Boolean endTurnFlag) {
    boardFrame.controlRoundActions(endTurnFlag, state);
  }

  public static void activateTransmuteIngredientFrame(
    ArrayList<Ingredient> displayedIngredients,
    Board mainBoard,
    BoardJFrame boardFrame,
    State state
  ) {
    boardFrame.activateTransmuteIngredientFrame(displayedIngredients, mainBoard, state);
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
