package tests;

import game.AlchemyMarker;
import game.Aspect;
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

     /**
     * Requires: A Board instance with two users and their avatars.
     * Modifies: Nothing.
     * Effects: Asserts that each token has exactly 2 ingredients by default.
     */
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

    /**
     * Requires: A Board instance with two users and their avatars.
     * Modifies: The ingredients list in the Board instance.
     * Effects: Asserts that the size of the ingredients list increases after calling addIngredient().
     */
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

     /**
     * Requires: A Board instance with two users and their avatars.
     * Modifies: The ingredients list in the Board instance and the ingredients in each Token.
     * Effects: Asserts that the total number of ingredients in tokens increases after calling addIngredient().
     */
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

     /**
     * Requires: A Board instance with two users and their avatars.
     * Modifies: The ingredients list in the Board instance.
     * Effects: Asserts that the size of the ingredients list increases after calling addIngredient().
     */
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
    
    /*
     * Requires: A Board instance that has been initialized with two usernames and two avatars. The Board class should have an addIngredient() method and a getIngredients() method.
     * Modifies: This test modifies the Board instance by calling the addIngredient() method. This method is expected to add an ingredient to the Board.
     * Effects: The test asserts that the getIngredients() method should return a non-empty list after addIngredient() is called. If this is not the case, the test will fail, indicating that the addIngredient() method did not work as expected.
     */
    @Test
    void testAddIngredient2() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);
        board.addIngredient();
        assertTrue(!board.getIngredients().isEmpty(), "The ingredients list shouldn't be empty after calling addIngredient()");
    }


    /**
     * Requires: A Board instance with two users and their avatars.
     * Modifies: The ingredients list in the Board instance and the ingredients in each Token.
     * Effects: Asserts that each token has exactly 2 ingredients by default, and 4 ingredients after calling addIngredient().
     */
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

        
        
        Exception exception = assertThrows(Exception.class, () -> {
            board.publishTheory(ingredient, alchemyMarker, token); // Publish the first theory, it should not throw an exception
            board.publishTheory(ingredient, alchemyMarker, token); // Publish the second theory with same ingredient and marker, it should throw an exception
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
        int previousGoldBalance = token.getGoldBalance();
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Ingredient ingredient = board.getStaticIngredients().get(0);

        assertDoesNotThrow(() -> {
            board.publishTheory(ingredient, alchemyMarker, token);
        });
        assertNotNull(board.getTheories().get(0));
        assertEquals(board.getTheories().get(0).isAboutIngredient(ingredient), true);
        assertEquals(board.getTheories().get(0).hasAlchemyMarker(alchemyMarker), true);
        assertEquals(board.getTheories().get(0).belongsToToken(token), true);
        assertEquals(previousGoldBalance, token.getGoldBalance() + 1);
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

    @Test
    void testInitialReputationPoints() {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        // I check whether the reputation points set correct or not
        for (Token token : board.getTokens()) {
            assertEquals(0, token.getReputation());
        }
    }

    @Test
    void testDebunkSuccesfulTheory() throws Exception {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        //I got the tokens for the debunker and theory owner
        Token debunkerToken = board.getTokens().get(0);
        Token theoryOwnerToken = board.getTokens().get(1);

        //I created a theory
        Ingredient ingredient = board.getIngredientFromDeck();
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Theory theory = new Theory(ingredient, alchemyMarker, theoryOwnerToken);
 
        //I attempt to debunk the theory
        Aspect debunkAspect = alchemyMarker.getAspect1();
        boolean result = board.debunkTheory(theory, debunkAspect, debunkerToken);
 
        // I check the results
        if (theory.debunkSuccess(debunkAspect)) {
            assertTrue(result);
            assertEquals(2, debunkerToken.getReputation()); // Reputation of debunker should increase by 2
            assertEquals(0, theoryOwnerToken.getReputation()); // Reputation of theory owner should decrease by 1
        } else {
            assertFalse(result);
            assertEquals(-1, debunkerToken.getReputation()); // Reputation of debunker should decrease by 1
            assertEquals(0, theoryOwnerToken.getReputation()); // Reputation of theory owner should remain unchanged
        }
    }

    @Test
    void testDebunkFailedTheory() throws Exception {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        //I got the tokens for the debunker and theory owner
        Token debunkerToken = board.getTokens().get(0);
        Token theoryOwnerToken = board.getTokens().get(1);

        //I created a theory
        Ingredient ingredient = board.getIngredientFromDeck();
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Theory theory = new Theory(ingredient, alchemyMarker, theoryOwnerToken);

        //I attempt to debunk the theory unsuccessfully
        Aspect debunkAspect = alchemyMarker.getAspect2(); // Choose a different aspect for debunking
        boolean result = board.debunkTheory(theory, debunkAspect, debunkerToken);

        // I check the results
        assertFalse(result);
        assertEquals(-1, debunkerToken.getReputation()); // Reputation of debunker should decrease by 1
        assertEquals(0, theoryOwnerToken.getReputation()); // Reputation of theory owner should remain unchanged
}

    @Test
    void testDebunkOwnTheory() throws Exception {
        // parameters for testing
        String username1 = "User1";
        String username2 = "User2";
        String avatar1 = "Avatar1";
        String avatar2 = "Avatar2";

        // I create a board with the parameters
        Board board = new Board(username1, username2, avatar1, avatar2);

        //I got the token for the theory owner
        Token theoryOwnerToken = board.getTokens().get(0);

        //I created a theory
        Ingredient ingredient = board.getIngredientFromDeck();
        AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
        Theory theory = new Theory(ingredient, alchemyMarker, theoryOwnerToken);

        //I attempt to debunk the own theory, should throw an exception
        Aspect debunkAspect = alchemyMarker.getAspect1();
        assertThrows(Exception.class, () -> board.debunkTheory(theory, debunkAspect, theoryOwnerToken));
}

        @Test
        void testDebunkUnsuccessfulTheoryWithInvalidAspect() throws Exception {
            // parameters for testing
            String username1 = "User1";
            String username2 = "User2";
            String avatar1 = "Avatar1";
            String avatar2 = "Avatar2";

            // I create a board with the parameters
            Board board = new Board(username1, username2, avatar1, avatar2);

            //  I got the tokens for the debunker and theory owner
            Token debunkerToken = board.getTokens().get(0);
            Token theoryOwnerToken = board.getTokens().get(1);

            //  I created a theory
            Ingredient ingredient = board.getIngredientFromDeck();
            AlchemyMarker alchemyMarker = board.getStaticAlchemyMarkers().get(0);
            Theory theory = new Theory(ingredient, alchemyMarker, theoryOwnerToken);

            //  I attempt to debunk the theory with an invalid aspect, it should fail
            Aspect invalidAspect = new Aspect("Invalid", "Invalid", "+", "src/ui/utils/aspects/invalid.png");
            boolean result = board.debunkTheory(theory, invalidAspect, debunkerToken);

        // I check the internal state of the Theory object
        assertFalse(result);

        // I ennsured that specific conditions in the internal state are met
        assertFalse(theory.debunkSuccess(invalidAspect));

        // I check the results
        assertEquals(-1, debunkerToken.getReputation()); // Reputation of debunker should decrease by 1
        assertEquals(0, theoryOwnerToken.getReputation()); // Reputation of theory owner should remain unchanged
}

}