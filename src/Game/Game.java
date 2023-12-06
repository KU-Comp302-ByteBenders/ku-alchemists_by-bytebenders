package game;

import javax.swing.*;
import ui.*;

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

  public void inactivateBoard() {
    activateBoard = false;
  }

  public static void openHelpScreen() {
    HelpJFrame helpJFrame = new HelpJFrame();
  }
}
