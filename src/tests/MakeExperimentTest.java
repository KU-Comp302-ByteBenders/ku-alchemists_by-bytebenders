package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import game.Token;
import game.AlchemyMarker;
import game.Aspect;
import game.Board;
import game.Ingredient;
import game.Potion;

import org.junit.jupiter.api.*;

public class MakeExperimentTest {
    @BeforeEach
    public void setUp() {
            
    }

    
    @Test
    public void testMakeExperiment() {
        Token token = new Token("User1", "Avatar1", "Token1");
        Board board = new Board("User1", "User2", "Avatar1", "Avatar2");
        board.createIngredients();
        token.addIngredient(board.getIngredientByName("feather"));
        token.addIngredient(board.getIngredientByName("dragon fruit"));
        token.addIngredient(board.getIngredientByName("ginger"));
        token.addIngredient(board.getIngredientByName("obsidian"));    
        
        Potion potion1 = token.makeExperiment("feather", "dragon fruit", false);
        Potion potion2 = new Potion("red", token.getIngredients().get(0), token.getIngredients().get(1), "+", "not negative potion");
        assertEquals(potion1.getName(), potion2.getName());

    }
}
