import game.*;

public class App {
    public static void main(String[] args) throws Exception {
        Game game = Game.getInstance(); // Get the singleton instance of the game
        game.openMainMenu();    // Open main menu screen
    }
}
    