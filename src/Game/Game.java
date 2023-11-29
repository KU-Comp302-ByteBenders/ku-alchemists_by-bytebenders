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
        
    }

    private void startGame(String username1, String username2, String avatar1, String avatar2){
        // Start the game
        System.out.println("Start the game");
        Board board = new Board( username1,  username2,  avatar1,  avatar2);
    }

    public void openPauseMenu(){

    }


    public void closePauseMenu(){

    }

    public void inactivateBoard(){
        activateBoard = false;
    }


}
