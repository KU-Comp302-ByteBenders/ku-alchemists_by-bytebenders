package game;


public class Game {
    public int round;
    public String state;
    private Boolean activateBoard;
    //protected Token tokenInfos;

    private static Game instance = null;

    public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

    private Game() {
        // initialize tokens 
        // sta
    }

    public void openPauseMenu(){

    }

    public void closePauseMenu(){

    }

    public void inactivateBoard(){
        activateBoard = false;
    }


}
