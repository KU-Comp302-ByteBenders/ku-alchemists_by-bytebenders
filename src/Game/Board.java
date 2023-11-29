package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import game.AlchemyMarker;
import game.Aspect;

public class Board {
    private ArrayList<Token> tokens;
    private ArrayList<Ingredient> ingredients;
    
    public Board(String username1, String username2, String avatar1, String avatar2) {
        Token token1 = new Token(username1, avatar1, avatar1);
        Token token2 = new Token(username2, avatar2, avatar2);
        tokens.add(token1);
        tokens.add(token2);
        creaIngredients();


        // Create a new board
        System.out.println("Create a new board");
        
    }

    public ArrayList<Ingredient> creaIngredients(){
        Aspect aspect1 = new Aspect("Red", "Small", "+");
        Aspect aspect2 = new Aspect("Blue", "Small", "+");
        Aspect aspect3 = new Aspect("Yellow", "Small", "+");
        Aspect aspect4 = new Aspect("Red", "Small", "-");
        Aspect aspect5 = new Aspect("Blue", "Small", "-");
        Aspect aspect6 = new Aspect("Yellow", "Small", "-");
        Aspect aspect7 = new Aspect("Red", "Big", "+");
        Aspect aspect8 = new Aspect("Blue", "Big", "+");
        Aspect aspect9 = new Aspect("Yellow", "Big", "+");
        Aspect aspect10 = new Aspect("Red", "Big", "-");
        Aspect aspect11 = new Aspect("Blue", "Big", "-");
        Aspect aspect12 = new Aspect("Yellow", "Big", "-");

        AlchemyMarker alchemyMarker1 = new AlchemyMarker(aspect7, aspect8, aspect9);
        AlchemyMarker alchemyMarker2 = new AlchemyMarker(aspect10, aspect11, aspect12);
        AlchemyMarker alchemyMarker3 = new AlchemyMarker(aspect1, aspect12, aspect5);
        AlchemyMarker alchemyMarker4 = new AlchemyMarker(aspect9, aspect4, aspect2);  
        AlchemyMarker alchemyMarker5 = new AlchemyMarker(aspect1, aspect6, aspect8); 
        AlchemyMarker alchemyMarker6 = new AlchemyMarker(aspect3, aspect11, aspect4);
        AlchemyMarker alchemyMarker7 = new AlchemyMarker(aspect3, aspect7, aspect5);
        AlchemyMarker alchemyMarker8 = new AlchemyMarker(aspect6, aspect7, aspect10);

        Ingredient ingredient1 = new Ingredient("ing1", 1, alchemyMarker1);
        Ingredient ingredient2 = new Ingredient("ing2", 2, alchemyMarker2);
        Ingredient ingredient3 = new Ingredient("ing3", 3, alchemyMarker3);
        Ingredient ingredient4 = new Ingredient("ing4", 4, alchemyMarker4);
        Ingredient ingredient5 = new Ingredient("ing5", 5, alchemyMarker5);
        Ingredient ingredient6 = new Ingredient("ing6", 6, alchemyMarker6);
        Ingredient ingredient7 = new Ingredient("ing7", 7, alchemyMarker7);
        Ingredient ingredient8 = new Ingredient("ing8", 8, alchemyMarker8);
        
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        ingredients.add(ingredient5);
        ingredients.add(ingredient6);
        ingredients.add(ingredient7);
        ingredients.add(ingredient8);

        for (Ingredient ingredient : ingredients) {
            for (int i = 0; i < 3; i++) {
                ingredients.add(ingredient);
            }
        }

        // Listeyi karıştır
        Collections.shuffle(ingredients, new Random());
        
        return ingredients;

    }
}
