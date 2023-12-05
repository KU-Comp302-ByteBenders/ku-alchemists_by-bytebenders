package game;

import java.util.ArrayList;

import game.ArtifactCards.ArtifactCard;

public class Token {
    // TODO: Change the types and implements the functions

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Potion> potions;
    private ArrayList<ArtifactCard> artifactCards;
    private ArrayList<Effect> artifactEffects;
    private ArrayList<String> resultTriangle;

    private int goldBalance;
    private int sicknessLevel;
    private String avatarImage;
    private String tokenImage;
    private String username;
    private int reputation;

    public Token(String username, String avatarImage, String tokenImage ){
        ingredients = new ArrayList<Ingredient>();
        potions = new ArrayList<Potion>();
        artifactCards = new ArrayList<ArtifactCard>();
        artifactEffects = new ArrayList<Effect>();
        resultTriangle = new ArrayList<String>();
        goldBalance = 0;
        sicknessLevel = 0;
        reputation = 0;
        this.avatarImage = avatarImage;
        this.tokenImage = tokenImage;
        this.username = username;
    }

    public void forageForIngredient(){}

    public void addGold(int amount){
        goldBalance += amount;
    }

    public void decreaseGold(int amount){}

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    public ArrayList<ArtifactCard> getArtifactList(){
        return artifactCards;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public String getUsername() {
        return username;
    }

    public int getGoldBalance() {
        return goldBalance;
    }

    public int getReputation() {
        return reputation;
    }

    public int getScore(){
        return 0;
    }

    public void makeExperiment(String ingredient1, String ingredient2, Boolean testOnSelf ){}

    public void addPotion(String potion){}

    public void removeIngredient(String ingredient){}

    public void sellPotion(String potion){}

    public void removePotion(String potion){}

    public void publishTheory(String ingredient, String alchemyMarker){}

    public void addReputation(int amount){
        reputation += amount;
    }

    public void decreaseReputation(int amount){
        reputation -= amount;
    }

    public void buyArtifactCard(ArtifactCard artifactCard){
        Board.giveArtifactCardtoToken(this);
    }

    //Burada niye direkt artifactCard.applyEffect yapamıyom anlamadım. SIKINTI ÇIKARABİLİR 
    public void useArtifactCard(ArtifactCard artifactCard){
        Effect effect = (Effect) artifactCard;
        effect.applyEffect(this);
    }

    public void addArtifactCard(ArtifactCard artifactCard){
        artifactCards.add(artifactCard);
    }

        public void decreaseSickness(int amount){
        sicknessLevel -= amount;
    }
    public void transmuteIngredient(String ingredient){}


}
