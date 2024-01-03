package game;

import java.util.ArrayList;
import ui.EndGameJFrame;

public class EndGamer {

  private ArrayList<Token> tokens;
  private int winner; // -1 for tie, 0 for token1, 1 for token2
  private int token1Score;
  private int token2Score;
  public static final int TIE = -1;
  public static final int TOKEN1 = 0;
  public static final int TOKEN2 = 1;

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

    System.out.println("token1Reputation: " + token1Reputation);
    System.out.println("token2Reputation: " + token2Reputation);

    int token1ArtifactCardNumber = token1.getArtifactCards().size();
    int token2ArtifactCardNumber = token2.getArtifactCards().size();

    if (token1.getArtifactCardByName("Wisdom Idol") != null) {
      token1Reputation += 1;
    }

    if (token2.getArtifactCardByName("Wisdom Idol") != null) {
      token2Reputation += 1;
    }

    int token1GoldAmount = token1.getGoldBalance();
    int token2GoldAmount = token2.getGoldBalance();

    System.out.println("token1GoldAmount: " + token1GoldAmount);
    System.out.println("token2GoldAmount: " + token2GoldAmount);

    token1GoldAmount += token1ArtifactCardNumber * 2;
    token2GoldAmount += token2ArtifactCardNumber * 2;

    int scoreFromGoldToken1 = (int) Math.floor(token1GoldAmount / 3);
    int scoreFromGoldToken2 = (int) Math.floor(token2GoldAmount / 3);

    int token1Score = token1Reputation * 10 + scoreFromGoldToken1;
    int token2Score = token2Reputation * 10 + scoreFromGoldToken2;

    System.out.println("token1Score: " + token1Score);
    System.out.println("token2Score: " + token2Score);

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
