package game;

public class OnlineGameStarter {

  Game game;

  public OnlineGameStarter(Game game) {
    this.game = game;
  }

  public void startOnlineGame() {
    Server server = game.getServer();
    server.startGame();
  }
}
