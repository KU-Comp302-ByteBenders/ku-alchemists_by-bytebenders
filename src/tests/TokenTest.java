package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import game.Token;
import game.Board;
import game.Potion;


public class TokenTest {
    Token token = new Token("User1", "Avatar1", "Token1");
    Board board = new Board("User1", "User2", "Avatar1", "Avatar2");
    Token testToken;
    
    //creating ingredients for the token
    @Before
    public void setUp() {    
        board.createIngredients();
        token.addIngredient(board.getIngredientByName("feather"));
        token.addIngredient(board.getIngredientByName("dragon fruit"));
        token.addIngredient(board.getIngredientByName("ginger"));
        token.addIngredient(board.getIngredientByName("obsidian"));      
    }

    //controlling the make experiment result with the expected result
    //if the name's are equal, the test is passed
    @Test
    public void testMakeExperiment() {
         
        Potion potion1 = token.makeExperiment("feather", "dragon fruit", false);
        Potion potion2 = new Potion("red", token.getIngredients().get(0)
                        ,token.getIngredients().get(1), "+", "not negative potion");
        
        assertEquals(potion1.getName(), potion2.getName());
    }

    //if the color's are equal, the test is passed
    @Test
    public void testMakeExperiment2() {
         
        Potion potion1 = token.makeExperiment("ginger", "obsidian", true);
        Potion potion2 = new Potion("Yellow", token.getIngredients().get(0)
                        ,token.getIngredients().get(1), "+", "not negative potion");
        
        assertEquals(potion1.getPotionColor(), potion2.getPotionColor());
    }

    //if the guarantee's are equal, the test is passed
    @Test
    public void testMakeExperiment3() {
         
        Potion potion1 = token.makeExperiment("feather", "obsidian", true);
        Potion potion2 = new Potion("Yellow", token.getIngredients().get(0)
                        ,token.getIngredients().get(1), "-", "negative potion");

        assertEquals(potion1.getGuarantee(), potion2.getGuarantee());
    }

    //if the name's are not equal, the test is passed but name should be 0 in here
    @Test
    public void testMakeExperiment4() {
         
        Potion potion1 = token.makeExperiment("ginger", "ginger", false);
        Potion potion2 = new Potion("transparent", token.getIngredients().get(0)
                        ,token.getIngredients().get(1), "+", "not negative potion");
        
        assertFalse(potion1.getName().equals(potion2.getName()));
    }

    //if the color's are equal, the test is passed
    @Test
    public void testMakeExperiment5() {
         
        Potion potion1 = token.makeExperiment("feather", "ginger", false);
        Potion potion2 = new Potion("transparent", token.getIngredients().get(0)
                        ,token.getIngredients().get(1), "0", "not negative potion");
        
        assertTrue(potion1.getPotionColor().equals(potion2.getPotionColor()));
        assertEquals("transparent", potion2.getPotionColor());
    }

    @Test
    public void testWithCombinations(){
        //repOK test
        testToken = new Token(null, "Avatar1", "Token1");
        assertFalse(testToken.repOK());
        
        testToken = new Token("User1", "Avatar1", "Token1");
        assertTrue(testToken.repOK());
        
        testToken.addGold(1);
        assertTrue(testToken.repOK());
        
        testToken.decreaseGold(1);
        assertTrue(testToken.repOK());
        
        testToken.addReputation(-1);
        assertFalse(testToken.repOK());
        
        testToken.decreaseSickness(1);
        assertFalse(testToken.repOK());

        

        
    }
    @Test
    public void testPotionTest(){
        // Test testPotion method control sickness level
        int firstSickness = token.getSicknessLevel();
        assertEquals(firstSickness, 0);
        Potion potion1 = token.makeExperiment("feather", "obsidian", true);        
        
        int lastSickness = token.getSicknessLevel();
        token.testPotion(potion1, false);
        assertEquals(lastSickness, 1);
        

    }

    @Test
    public void sellPotionTest(){     
        // Test sellPotion method control gold balance
        token.sellPotion("+");
        int lastGold = token.getGoldBalance();
        assertEquals(3, lastGold);

        token.sellPotion("-");
        lastGold = token.getGoldBalance();
        assertEquals(4, lastGold);

        token.sellPotion("0");
        lastGold = token.getGoldBalance();
        assertEquals(6, lastGold);
    }

    @Test
    public void forageForIngredientTest(){
        // Test forageForIngredient method
        int lastIngredient = token.getIngredients().size();
        token.forageForIngredient(board);
        assertEquals(lastIngredient + 1, token.getIngredients().size());
    }

    @Test
    public void removeIngredientTest(){
        // Test removeIngredient method 
        int firstIngredientSize = token.getIngredients().size();
        token.removeIngredient(("feather"));
        assertEquals(firstIngredientSize - 1, token.getIngredients().size());
    }

    @Test
    public void transmuteIngredientTest(){
        // Test transmuteIngredient method control whether its storage is shrinked or not
        int firstIngredientSize = token.getIngredients().size();
        token.transmuteIngredient("ginger");
        assertEquals(firstIngredientSize - 1, token.getIngredients().size());
    }

}

 