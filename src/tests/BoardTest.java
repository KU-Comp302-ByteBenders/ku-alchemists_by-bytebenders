package tests;

import game.AlchemyMarker;
import game.Board;
import game.Ingredient;
import game.Theory;
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
    void testAddIngredientToNonEmptyBoard() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        board.addIngredient();
        int initialSize = board.getIngredients().size();
        board.addIngredient();
        assertTrue(board.getIngredients().size() > initialSize, "Adding ingredients should increase the total number of ingredients");
    }

     // Glass-box tests
    @Test
    void testAddIngredientDistribution() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        board.addIngredient();

        int initialSize = 0;
        for (Token token : board.getTokens()) {
            initialSize += token.getIngredients().size();
        }

        board.addIngredient();
        int finalSize = 0;
        for (Token token : board.getTokens()) {
            finalSize += token.getIngredients().size();
        }

        assertTrue(finalSize > initialSize, "Adding ingredients should increase the total number of ingredients in tokens");
    }

    @Test
    void testAddIngredientRemovalFromBoard() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        board.addIngredient();
        int initialSize = board.getIngredients().size();
        board.addIngredient();
        assertTrue(board.getIngredients().size() > initialSize, "Adding ingredients should increase the total number of ingredients on the board");
    }

    @Test
void testAddIngredientDistributionAndEmptyBoard() {
    // parameters for testing
    String username1 = "User1";
    String username2 = "User2";
    String avatar1 = "Avatar1";
    String avatar2 = "Avatar2";

    // I create a board with the parameters
    Board board = new Board(username1, username2, avatar1, avatar2);

    // Check that each token has exactly 2 ingredients
    for (Token token : board.getTokens()) {
        assertEquals(2, token.getIngredients().size(), "Each token should have 4 ingredients after the first call addIngredient().");
    }

    board.addIngredient();
    // Check that each token gets 2 more ingredients when addIngredient() is called
    for (Token token : board.getTokens()) {
        assertEquals(4, token.getIngredients().size(), "Each token should have 4 ingredients after the first call addIngredient().");
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
    void testPublishTheoryWithNullIngredient() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        Token token = board.getTokens().get(0);
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);

        Exception exception = assertThrows(Exception.class, () -> {
            board.publishTheory(null, alchemyMarker, token);
        });

        assertEquals("Please select an ingredient and an alchemy marker!", exception.getMessage());
    }

    @Test
    void testPublishTheoryWithNullAlchemyMarker() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        Token token = board.getTokens().get(0);
        Ingredient ingredient = board.getStaticIngredients().get(0);

        Exception exception = assertThrows(Exception.class, () -> {
            board.publishTheory(ingredient, null, token);
        });

        assertEquals("Please select an ingredient and an alchemy marker!", exception.getMessage());
    }

    @Test
    void testPublishTheoryWithInsufficientGold() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        Token token = board.getTokens().get(0);
        token.decreaseGold(10); // decrease the gold balance to 0
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Ingredient ingredient = board.getStaticIngredients().get(0);

        Exception exception = assertThrows(Exception.class, () -> {
            board.publishTheory(ingredient, alchemyMarker, token);
        });

        assertEquals("Not enough gold!", exception.getMessage());
    }

    @Test
    void testPublishTheoryWithExistingTheory() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        Token token = board.getTokens().get(0);
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Ingredient ingredient = board.getStaticIngredients().get(0);
        Theory existingTheory = new Theory(ingredient, alchemyMarker, token);
        board.getTheories().add(existingTheory);

        Exception exception = assertThrows(Exception.class, () -> {
            board.publishTheory(ingredient, alchemyMarker, token);
        });

        assertEquals("A theory on this ingredient already exists!", exception.getMessage());
    }

    @Test
    void testPublishTheorySuccessfully() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        Token token = board.getTokens().get(0);
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Ingredient ingredient = board.getStaticIngredients().get(0);

        assertDoesNotThrow(() -> {
            board.publishTheory(ingredient, alchemyMarker, token);
        });
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