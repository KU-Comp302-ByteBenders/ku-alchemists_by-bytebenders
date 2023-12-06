package game;

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

  public static void openPublishMenu(JFrame boardJFrame, Board board) {
    // Open the publish theory action menu
    PublishTheoryJFrame publishTheoryJFrame = new PublishTheoryJFrame(board);
  }

  public static void openPublicationTrack(JFrame boardJFrame, Board board) {
    // Open the publish theory action menu
    PublicationTrackJFrame publicationTrackJFrame = new PublicationTrackJFrame(board);
  }

  public static void inactivateBoard(JFrame board) {
    board.setVisible(false);
  }

  public static void activateBoard(JFrame board) {
    board.setVisible(true);
  }
}
