package game;

import ui.TransitionJFrame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    Collections.shuffle(tokens);
    this.tokens = tokens;
    actionsMadeInRound = 0;
    currentRound = 1;
    currentTurn = 1;
    numberOfPlayers = tokens.size();
    initializeTurnMaps(tokens);
    for(Token token : tokens){
      System.out.println(token.getUsername());
    }
  }

  private void initializeTurnMaps(ArrayList<Token> tokens) {
    for (Token token : tokens) {
      round1Turns.put(token, 1);
      round2Turns.put(token, 1);
      round3Turns.put(token, 1);
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

  public HashMap<Token, Integer> getRound1Turns() {
      return round1Turns;
  }

  public HashMap<Token, Integer> getRound2Turns() {
      return round2Turns;
  }
  public HashMap<Token, Integer> getRound3Turns() {
      return round3Turns;
  }

  public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public ArrayList<Token> getTokens() {
        return tokens;
    }


  public Token getCurrentToken() {
    return tokens.get(currentTurn - 1); // decrement 1 because of zero indexing
  }


  public void endTurn(Board board, Token token1) {
      Token currentToken;
      int index = tokens.indexOf(token1);

    if (index >= 0 && index < tokens.size() - 1) {
      currentToken = tokens.get(index + 1);
    } else {
      currentToken = tokens.get(0);
    }

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
        TransitionJFrame transitionJFrame = new TransitionJFrame(currentToken, board, this, tokens);
      }
  }

  public void updateCurrentRound() {
    int controller = 0;
    if(currentRound ==1 ){
      for (Integer value : round1Turns.values()) {
        if (value == 4) {
          controller++;
        }
      }
      if(controller == numberOfPlayers){
        currentRound++;
      }
    }
    else if(currentRound == 2){
      for (Integer value : round2Turns.values()) {
        if (value == 4) {
          controller++;
        }
      }
      if(controller == numberOfPlayers){
        currentRound++;
      }
    }
    else if(currentRound == 3){
      for (Integer value : round3Turns.values()) {
        if (value == 4) {
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
    return tokens.get(0);
  }

}
