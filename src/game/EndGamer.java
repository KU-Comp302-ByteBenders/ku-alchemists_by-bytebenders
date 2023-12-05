package game;

import java.util.ArrayList;
import ui.EndGameJFrame;

public class EndGamer {

  private ArrayList<Token> tokens;
  private int winner; // -1 for tie, 0 for token1, 1 for token2
  private int token1Score;
  private int token2Score;

  public EndGamer(Board board) {
    tokens = board.getTokens();
    calculateResults(board);
  }

  public int getWinner() {
    return winner;
  }

  public int getToken1Score() {
    return token1Score;
  }

  public int getToken2Score() {
    return token2Score;
  }

  public ArrayList<Token> getTokens() {
    return tokens;
  }

  public void openEndGame() {
    EndGameJFrame endGameJFrame = new EndGameJFrame(this);
  }

  public void calculateResults(Board board) {
    Token token1 = board.getTokens().get(0);
    Token token2 = board.getTokens().get(1);

    int token1Reputation = token1.getReputation();
    int token2Reputation = token2.getReputation();

    int token1ArtifactCardNumber = token1.getArtifactList().size();
    int token2ArtifactCardNumber = token2.getArtifactList().size();

    int token1GoldAmount = token1.getGoldBalance();
    int token2GoldAmount = token2.getGoldBalance();

    token1GoldAmount += token1ArtifactCardNumber * 2;
    token2GoldAmount += token2ArtifactCardNumber * 2;

    int scoreFromGoldToken1 = (int) Math.floor(token1GoldAmount / 3);
    int scoreFromGoldToken2 = (int) Math.floor(token2GoldAmount / 3);

    int token1Score = token1Reputation * 10 + scoreFromGoldToken1;
    int token2Score = token2Reputation * 10 + scoreFromGoldToken2;

    if (token1Score > token2Score) {
      winner = 0;
    } else if (token1Score < token2Score) {
      winner = 1;
    } else {
      int leftOverGoldToken1 = token1GoldAmount % 3;
      int leftOverGoldToken2 = token2GoldAmount % 3;
      if (leftOverGoldToken1 > leftOverGoldToken2) {
        winner = 0;
      } else if (leftOverGoldToken1 < leftOverGoldToken2) {
        winner = 1;
      } else {
        winner = -1;
      }
    }
  }
}
