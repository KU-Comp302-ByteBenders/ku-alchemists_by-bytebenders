package game;

import game.ArtifactCards.ArtifactCard;
import java.io.Serializable;
import java.util.ArrayList;
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
  private IGameStarterAdapter gameStarterAdapter;
  private ArrayList<String> playerNames;
  private ArrayList<String> playerAvatars;

  private OnlineBoardJFrame onlineBoardJFrame;

  private static Game instance = null;

  public static Game getInstance() {
    if (instance == null) {
      instance = new Game();
    }
    return instance;
  }

  private Game() {
    gameStarterAdapter = new GameStarterAdapter(this);
  }

  public OnlineBoardJFrame getOnlineBoardJFrame() {
    return onlineBoardJFrame;
  }

  public void setGameStarterAdapter(IGameStarterAdapter gameStarterAdapter) {
    this.gameStarterAdapter = gameStarterAdapter;
  }

  public ArrayList<String> getPlayerNames() {
    return playerNames;
  }

  public void setPlayerNames(ArrayList<String> playerNames) {
    this.playerNames = playerNames;
  }

  public ArrayList<String> getPlayerAvatars() {
    return playerAvatars;
  }

  public void setPlayerAvatars(ArrayList<String> playerAvatars) {
    this.playerAvatars = playerAvatars;
  }

  public Server getServer() {
    return server;

  }

  public void restartGame() {
    instance = new Game();
  }

  public void openCountDownFrame(Token token, Board board) {
    CountDownFrame countDownJFrame = new CountDownFrame(token, board);
  }

  public void closeOnlineBoard() {
    this.onlineBoardJFrame.dispose();
  }

  public boolean isOffline() {
    return server == null && client == null;
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

  public void openEndGame(Board board) {
    EndGamer endGamer = new EndGamer(board);
    endGamer.openEndGame();
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
    this.playerNames = playerNames;
    this.playerAvatars = playerAvatars;
    gameStarterAdapter.startGame(); // Starts the game with the adapter.
    loginScreen.setVisible(false);
  }

  public void startGame(ChangeableVisibility frame) {
    frame.changeVisible(false);
    gameStarterAdapter.startGame(); //Starts the game with the adapter.
  }

  public void publishAction(String action) {
    if (server == null) {
      client.sendAction(action);
    } else {
      server.publishAction(action);
    }
  }

  public void openPauseMenu() {
  }

  public void closePauseMenu() {
  }

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
    State state,
    Token token
  ) {
    TransmuteIngredientFrame transmuteJFrame = new TransmuteIngredientFrame(
      displayedIngredients,
      mainBoard,
      boardFrame,
      state,
      token
    );
  }

  public static void openTriangleBoard(JButton button, Token token, int index, Board board) {
    TriangleBoardJFrame triangleBoardJFrame = new TriangleBoardJFrame(button, token, index, board);
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
        JOptionPane.YES_NO_OPTION);

    if (confirmed == JOptionPane.YES_OPTION) {
      // If the user confirmed, set the flag to true
      artifactCard.setToBeAppliedFlag(true);
    }
  }

  public static void controlRoundAction(BoardFrame boardFrame, State state, Boolean endTurnFlag) {
    System.out.println("geldin mi be");
    boardFrame.controlRoundActions(endTurnFlag, state);
  }

  public static void activateTransmuteIngredientFrame(
      ArrayList<Ingredient> displayedIngredients,
      Board mainBoard,
      OfflineBoardJFrame boardFrame,
      State state) {
    boardFrame.activateTransmuteIngredientFrame(displayedIngredients, mainBoard, state);
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
