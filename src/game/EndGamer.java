package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ui.EndGameJFrame;

public class EndGamer implements Serializable {

  private static final long serialVersionUID = 5L;

  private ArrayList<Token> tokens;
  private int winner; // index of the winner - (-1) for tie
  private ArrayList<Integer> tiePlayersTies;
  private int maxLeftOverGold;
  private int[] tokenScores;

  public EndGamer(Board board) {
    tokens = board.getTokens();
    calculateResults(board);
  }

  public int getWinner() {
    return winner;
  }

  public int[] getTokenScores() {
    return tokenScores;
  }

  public ArrayList<Token> getTokens() {
    return tokens;
  }

  public ArrayList<Integer> getTiePlayersTies() {
    return tiePlayersTies;
  }

  public int getMaxLeftOverGold() {
    return maxLeftOverGold;
  }

  public void openEndGame() {
    EndGameJFrame endGameJFrame = new EndGameJFrame(this);
  }

  public void calculateResults(Board board) {
    int playerNumber = board.getTokens().size();
    ArrayList<Token> tokens = board.getTokens();
    int[] tokenReputations = new int[playerNumber];
    int[] tokenArtifactCardNumbers = new int[playerNumber];
    int[] tokenGoldAmounts = new int[playerNumber];
    tokenScores = new int[playerNumber];
    ArrayList<Integer> tiePlayers = new ArrayList<Integer>();
    tiePlayersTies = new ArrayList<Integer>();
    int[] tieBreakerGolds = new int[playerNumber];

    for (int i = 0; i < playerNumber; i++) {
      Token token = tokens.get(i);
      tokenReputations[i] = token.getReputation();
      tokenArtifactCardNumbers[i] = token.getArtifactCards().size();
      tokenGoldAmounts[i] = token.getGoldBalance();
      tokenGoldAmounts[i] += tokenArtifactCardNumbers[i] * 2;

      int scoreFromGold = (int) Math.floor(tokenGoldAmounts[i] / 3);
      tieBreakerGolds[i] = tokenGoldAmounts[i] % 3;
      tokenScores[i] = tokenReputations[i] * 10 + scoreFromGold;
    }

    int maxScore = tokenScores[0];
    int maxScorePlayer = 0;
    boolean lookLeftOver = false;

    for (int i = 1; i < playerNumber; i++) {
      if (tokenScores[i] > maxScore) {
        maxScore = tokenScores[i];
        maxScorePlayer = i;
        lookLeftOver = false;
      } else if (tokenScores[i] == maxScore) {
        if (!tiePlayers.contains(maxScorePlayer)) {
          tiePlayers.add(maxScorePlayer);
        }
        tiePlayers.add(i);
        lookLeftOver = true;
      }
    }

    if (lookLeftOver) {
      maxLeftOverGold = tieBreakerGolds[tiePlayers.get(0)];
      int maxTieBreakerPlayer = tiePlayers.get(0);
      boolean isTie = false;

      for (int playerIndex : tiePlayers) {
        if (tieBreakerGolds[playerIndex] > maxLeftOverGold) {
          maxLeftOverGold = tieBreakerGolds[playerIndex];
          maxTieBreakerPlayer = playerIndex;
          isTie = false;
        } else if (tieBreakerGolds[playerIndex] == maxLeftOverGold) {
          tiePlayersTies.add(playerIndex);
          tiePlayersTies.add(maxTieBreakerPlayer);
          isTie = true;
        }
      }

      for (int i : tiePlayersTies) {
        System.out.println(i);
      }

      tiePlayersTies = removeDuplicates(tiePlayersTies);
      winner = isTie ? -1 : maxTieBreakerPlayer;
    } else {
      winner = maxScorePlayer;
    }
  }

  public static <T> ArrayList<T> removeDuplicates(List<T> listWithDuplicates) {
    HashSet<T> uniqueSet = new HashSet<>(listWithDuplicates);
    ArrayList<T> uniqueList = new ArrayList<>(uniqueSet);
    return uniqueList;
  }
}
