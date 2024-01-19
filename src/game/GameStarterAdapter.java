package game;

public class GameStarterAdapter implements IGameStarterAdapter {

  // Game is a client, services are onlineGameStarter and offlineGameStarter
  Game game;
  OnlineGameStarter onlineGameStarter;
  OfflineGameStarter offlineGameStarter;

  public GameStarterAdapter(Game game) {
    this.game = game;
  }

  @Override
  public void startGame() {
    if (game.isOffline()) {
      offlineGameStarter = new OfflineGameStarter(game);
      offlineGameStarter.startOfflineGame();
    } else {
      onlineGameStarter = new OnlineGameStarter(game);
      onlineGameStarter.startOnlineGame();
    }
  }
}
