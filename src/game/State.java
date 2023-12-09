package game;
import java.util.ArrayList;

public class State {
    private ArrayList<Token> tokens;
    private int actionsMadeInRound; // track the number of actions made in that round
    private int turn;
    private int round;
    private int numberOfPlayers;

    public State(ArrayList<Token> tokens) {
        this.tokens = tokens;
        actionsMadeInRound = 0;
        turn = 1;
        round = 1;
        numberOfPlayers = tokens.size();
    }

    public int getRound() {
        return round;
    }

    public Token getCurrentToken() {
        return tokens.get(turn - 1); // decrement 1 because of zero indexing
    }

    public int getCurrentTokenIndex() {
        return turn - 1;
    }

    public boolean turnOfToken(Token token) {
        return tokens.get(turn - 1).equals(token); // decrement 1 because of zero indexing
    }

    private void updateTurn() {
        turn++;
        if (turn > numberOfPlayers) {
            turn = 1;
        }
    }

    private void updateActionsMadeInRound() {
        actionsMadeInRound++;
    }

    private void updateRound() {
        // each player gets 3 actions per round
        // so the total number of actions needed to advance to the next round is 
        // 3 * number of players
        int totalActionsNeeded = numberOfPlayers * 3;
        if (actionsMadeInRound == totalActionsNeeded) {
            round++;
            actionsMadeInRound = 0;
        }
    }

    // this method is to be invoked after each game action
    // it updates all parameters of the game state
    public void updateState() {
        updateTurn();
        updateActionsMadeInRound();
        // updateRound();
    }

    public boolean gameIsOver() {
        return round == 4; // when the third round finishes, round integer will be 4
    }
}
