package game;

import java.util.ArrayList;
import javax.swing.*;
import ui.*;

/*
 * This class is the Controller class.
 * Also a Singleton class.
 */
public class Game {

  public int round;
  public String state;
  private Boolean activateBoard;
  //protected Token tokenInfos;

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

  public void openLogin(JFrame mainMenu) {
    // Open the login screen
    LoginJFrame loginScreen = new LoginJFrame();
    // Close the main menu
    mainMenu.setVisible(false);
  }

  public void startGame(String username1, String username2, String avatar1, String avatar2, JFrame loginScreen) {
    // Create Board Controller. Board Controller opens the BoardJFrame
    Board board = new Board(username1, username2, avatar1, avatar2);
    // Close the login screen
    loginScreen.setVisible(false);
  }

  public void openPauseMenu() {}

  public void closePauseMenu() {}

  public static void openPublishMenu(BoardJFrame boardJFrame, Board board) {
    // Open the publish theory action menu
    PublishTheoryJFrame publishTheoryJFrame = new PublishTheoryJFrame(boardJFrame, board);
  }

  public static void openDebunkMenu(BoardJFrame boardJFrame, Board board) {
    // Open the debunk theory action menu
    DebunkTheoryJFrame debunkTheoryJFrame = new DebunkTheoryJFrame(boardJFrame, board);
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

  public static void inactivateBoard(JFrame board) {
    board.setVisible(false);
  }

  public static void activateBoard(JFrame board) {
    board.setVisible(true);
  }

  public static void openArtifactBuyScreen(BoardJFrame boardJFrame, Board board, Token token1, Token token2) {
    BuyArtifactFrame buyArtifactFrame = new BuyArtifactFrame(boardJFrame, board, token1, token2);
    buyArtifactFrame.setVisible(true);
  }

  public static void closeArtifactBuyScreen(JFrame pauseMenu) {
    pauseMenu.dispose();
  }

  public static void activateTransmuteIngredientFrame(
    ArrayList<Ingredient> displayedIngredients,
    Board mainBoard,
    BoardJFrame boardFrame
  ) {
    TransmuteIngredientFrame transmuteJFrame = new TransmuteIngredientFrame(
      displayedIngredients,
      mainBoard,
      boardFrame
    );
  }

  public static void openTriangleBoard(JButton button) {
    TriangleBoardJFrame triangleBoardJFrame = new TriangleBoardJFrame(button);
  }

  public static void openHelpScreen() {
    HelpJFrame helpJFrame = new HelpJFrame();
  }

  public static void openExperimentFrame(Board board) {
    MakeExperimentJFrame makeExperimentJFrame = new MakeExperimentJFrame(board);
  }
}
