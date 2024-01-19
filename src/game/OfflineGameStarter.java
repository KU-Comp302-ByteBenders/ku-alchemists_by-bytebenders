package game;

import java.util.ArrayList;

public class OfflineGameStarter {

  Game game;

  public OfflineGameStarter(Game game) {
    this.game = game;
  }

  public void startOfflineGame() {
    ArrayList<Token> tokens = new ArrayList<>();
    ArrayList<String> playerNames = game.getPlayerNames();
    ArrayList<String> playerAvatars = game.getPlayerAvatars();
    int numPlayers = playerNames.size();

    for (int i = 0; numPlayers > i; i++) {
      Token token1 = new Token(playerNames.get(i), playerAvatars.get(i), playerAvatars.get(i));
      tokens.add(token1);
    }

    Board board = new Board(tokens);
  }
}
