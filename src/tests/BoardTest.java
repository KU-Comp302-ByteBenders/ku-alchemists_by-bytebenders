package tests;

import game.Board;
import game.Token;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testTokenCreationAndAddition() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // I check if the board has 2 tokens
        assertEquals(2, board.getTokens().size());
    }

    @Test
    void testTokensGold() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // I check whether the tokens' gold balance is 10
        for (Token token : board.getTokens()) {
            assertEquals(10, token.getGoldBalance());
        }
    }

    @Test
    void testAddIngredient() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // I check whether the default ingredient cards amount of tokens' is 2
        for (Token token : board.getTokens()) {
            assertEquals(2, token.getIngredients().size());
        }
    }



    @Test
    void testArtifactsCards() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // I check whether the artifact card list is empty
        assertFalse(board.getArtifactCards().isEmpty());
    }

    @Test
    void testInitialGameState() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // Create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // Check if the state object is not null
        assertNotNull(board.getState());
    }


}