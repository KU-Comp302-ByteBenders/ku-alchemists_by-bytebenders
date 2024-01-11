package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import game.ArtifactCards.ArtifactCard;
import ui.*;
import ui.interfaces.ChangeableVisibility;

/*
 * This class is the Controller class.
 * Also a Singleton class.
 */
public class Game implements Serializable {
  private static final long serialVersionUID = 6L;
  public int round;
  private Boolean activateBoard;
  Server server;

  private static Game instance = null;

  public static Game getInstance() {
    if (instance == null) {
      instance = new Game();
    }
    return instance;
  }

  private Game() {}

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

  public void startGame(String username1, String username2, String avatar1, String avatar2, JFrame loginScreen) {
    Board board = new Board(username1, username2, avatar1, avatar2);
    loginScreen.setVisible(false);
  }

  public void startGameOnline(HashMap<String, String> credentials, JFrame loginScreen) {
    Board board = new Board(credentials);
    loginScreen.setVisible(false);
  }

  public void startGameOnline() {
    server.startGame();
  }

  public void openPauseMenu() {}

  public void closePauseMenu() {}

  public static void openPublishMenu(BoardJFrame boardJFrame, Board board, State state, Token token1) {
    // Open the publish theory action menu
    PublishTheoryJFrame publishTheoryJFrame = new PublishTheoryJFrame(boardJFrame, board, state, token1);
  }

  public static void openDebunkMenu(BoardJFrame boardJFrame, Board board, State state, Token token1) {
    // Open the debunk theory action menu
    DebunkTheoryJFrame debunkTheoryJFrame = new DebunkTheoryJFrame(boardJFrame, board, state, token1);
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

  public static void openArtifactBuyScreen(BoardJFrame boardJFrame, Board board, Token token, State state) {
    BuyArtifactFrame buyArtifactFrame = new BuyArtifactFrame(boardJFrame, board, token, state);
    buyArtifactFrame.setVisible(true);
  }

  public static void closeArtifactBuyScreen(JFrame pauseMenu) {
    pauseMenu.dispose();
  }

  public static void activateTransmuteIngredientFrame(
    ArrayList<Ingredient> displayedIngredients,
    Board mainBoard,
    BoardJFrame boardFrame,
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

  public static void openExperimentFrame(Token token, Board board, BoardJFrame boardJFrame, State state) {
    MakeExperimentJFrame makeExperimentJFrame = new MakeExperimentJFrame(token, board, boardJFrame, state);
  }

  public static void openPotionJFrame(Token token, Board board, BoardJFrame boardJFrame, State state) {
    PotionJFrame potionJFrame = new PotionJFrame(token, board, boardJFrame, state);
  }

  public static void openWisdomIdolConfirmationDialog(ArtifactCard artifactCard) {
    // Add confirmation dialog
    int confirmed = JOptionPane.showConfirmDialog(null, 
    "Is the Theory Owner sure that they want to apply the Wisdom Idol effect?", "Confirmation", 
    JOptionPane.YES_NO_OPTION);

    if (confirmed == JOptionPane.YES_OPTION) {
      // If the user confirmed, set the flag to true
      artifactCard.setToBeAppliedFlag(true);
    }
  }

  public static void controlRoundAction(BoardJFrame boardJFrame, State state, Boolean endTurnFlag) {
    boardJFrame.controlRoundActions(endTurnFlag, state);
  }
}
