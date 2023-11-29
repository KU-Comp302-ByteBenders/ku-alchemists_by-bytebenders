package game;

import java.util.ArrayList;

public class Token {
    // TODO: Change the types and implements the functions

    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> potions;
    private ArrayList<String> artifactCards;
    private ArrayList<String> artifactEffects;
    private ArrayList<String> resultTriangle;

    private int goldBalance;
    private int sicknessLevel;

    public Token(String name, String avatarImage, String tokenImage ){}

    public void forageForIngredient(){}

    public void addGold(int amount){}

    public void decreaseGold(int amount){}

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void makeExperiment(String ingredient1, String ingredient2, Boolean testOnSelf ){}

    public void addPotion(String potion){}

    public void removeIngredient(String ingredient){}

    public void sellPotion(String potion){}

    public void removePotion(String potion){}

    public void publishTheory(String ingredient, String alchemyMarker){}

    public void addReputation(int amount){}

    public void decreaseReputation(int amount){}

    public void buyArtifactCard(String artifactCard){}

    public void useArtifactCard(String artifactCard){}

    public void transmuteIngredient(String ingredient){}


}
