package game;

import ui.BoardJFrame;
import ui.TransitionJFrame;
import ui.TransitionStarterJFrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class State implements Serializable {

  private static final long serialVersionUID = 10L;

  private ArrayList<Token> tokens;
  private int actionsMadeInRound; // track the number of actions made in that round
  private int currentTurn;
  private int currentRound;
  private int numberOfPlayers;
  private boolean endOfGame = false;

  private HashMap<Token, Integer> round1Turns = new HashMap<>();
  private HashMap<Token, Integer> round2Turns = new HashMap<>();
  private HashMap<Token, Integer> round3Turns = new HashMap<>();

  public State(ArrayList<Token> tokens) {
    this.tokens = tokens;
    actionsMadeInRound = 0;
    currentRound = 1;
    currentTurn = 0;
    numberOfPlayers = tokens.size();
    initializeTurnMaps(tokens);
  }

  private void initializeTurnMaps(ArrayList<Token> tokens) {
    for (Token token : tokens) {
      round1Turns.put(token, 0);
      round2Turns.put(token, 0);
      round3Turns.put(token, 0);
    }
  }

  public int getCurrentTurn() {
    return currentTurn;
  }
  public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }
  public int getCurrentRound() {
    return currentRound;
  }
  public void setCurrentRound(int currentRound) {
    this.currentRound = currentRound;
  }


  public Token getCurrentToken() {
    return tokens.get(currentTurn - 1); // decrement 1 because of zero indexing
  }


  public void endTurn(Board board, Token token1, Token token2) {
      if(currentRound == 1){
        round1Turns.put(token1, round1Turns.get(token1) + 1);
      }
      else if(currentRound == 2){
        round2Turns.put(token1, round2Turns.get(token1) + 1);
      }
      else if(currentRound == 3) {
        round3Turns.put(token1, round3Turns.get(token1) + 1);

      }
    updateCurrentRound();
      if(endOfGame){
        EndGamer endGamer = new EndGamer(board);
        endGamer.openEndGame();
      }
      else {
        TransitionJFrame transitionJFrame = new TransitionJFrame(token2, board, this);
      }
  }

  public void updateCurrentRound() {
    int controller = 0;
    if(currentRound ==1 ){
      for (Integer value : round1Turns.values()) {
        if (value == 3) {
          controller++;
        }
      }
      if(controller == numberOfPlayers){
        currentRound++;
      }
    }
    else if(currentRound == 2){
      for (Integer value : round2Turns.values()) {
        if (value == 3) {
          controller++;
        }
      }
      if(controller == numberOfPlayers){
        currentRound++;
      }
    }
    else if(currentRound == 3){
      for (Integer value : round3Turns.values()) {
        if (value == 3) {
          controller++;
        }
      }
      if(controller == numberOfPlayers){
        endOfGame = true;
      }
    }
  }


  private void updateActionsMadeInRound() {
    actionsMadeInRound++;
  }

  public Token selectRandomToken() {
    if (tokens == null || tokens.isEmpty()) {
      return null;
    }

    Random random = new Random();
    int randomIndex = random.nextInt(tokens.size());
    return tokens.get(randomIndex);
  }

}
