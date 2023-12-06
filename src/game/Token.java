package game;

import java.util.ArrayList;

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

  public Token(String username, String avatarImage, String tokenImage) {
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

  public Ingredient forageForIngredient(Board board) {
    Ingredient ingredient = board.getIngredientFromDeck();
    ingredients.add(ingredient);
    return ingredient;
  }

  public void addGold(int amount) {
    goldBalance += amount;
  }

  public void decreaseGold(int amount) {}

  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
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

  public int getScore() {
    return 0;
  }

  public ArrayList<ArtifactCard> getArtifactCards() {
    return artifactCards;
  }

  public void makeExperiment(String ingredient1, String ingredient2, Boolean testOnSelf) {}

  public void addPotion(String potion) {}

  public void removeIngredient(String ingredient) {
    for( Ingredient ing : ingredients){
      if(ing.getName().equals(ingredient)){
        ingredients.remove(ing);
        break;
      }
    } 
  }

  public void sellPotion(String potion) {}

  public void removePotion(String potion) {}

  public void publishTheory(Board board, Ingredient ingredient, AlchemyMarker alchemyMarker) throws Exception {
    board.publishTheory(ingredient, alchemyMarker, this);
  }

  public void debunkTheory(Board board, Theory theory, AlchemyMarker alchemyMarker) {
    board.debunkTheory(theory, alchemyMarker, this);
  }

  public void addReputation(int amount) {}

  public void decreaseReputation(int amount) {}

  public void buyArtifactCard(String artifactCard) {}

  public void useArtifactCard(String artifactCard) {}

  public void transmuteIngredient(String ingredientName) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(ingredientName)) {
          ingredients.remove(ingredient);
      }
    }
  }
}

  
