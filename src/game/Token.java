package game;

import game.ArtifactCards.ArtifactCard;
import game.ArtifactCards.EffectStrategy;

import java.util.ArrayList;
import ui.BoardJFrame;


public class Token {
  //  OVERVIEW: This class provides the token's properties and methods

  //  the rep
  private ArrayList<Ingredient> ingredients;
  private ArrayList<Potion> potions;
  private ArrayList<ArtifactCard> artifactCards;
  private int goldBalance;
  private int sicknessLevel;
  private String avatarImage;
  private String tokenImage;
  private String username;
  private int reputation;
  public static ArrayList<Potion> potionsForFrame;

  

  // The rep invariant is 
	// username != null && avatarImage != null && tokenImage != null 
  // && goldBalance >= 0 && sicknessLevel >= 0 && reputation >= 0
	// 
	// 

  //constructor
  public Token(String username, String avatarImage, String tokenImage) {
  
    ingredients = new ArrayList<Ingredient>();
    potions = new ArrayList<Potion>();
    artifactCards = new ArrayList<ArtifactCard>();
    
    goldBalance = 0;
    sicknessLevel = 0;
    reputation = 0;
    this.avatarImage = avatarImage;
    this.tokenImage = tokenImage;
    this.username = username;
    potionsForFrame = new ArrayList<Potion>();
  }


  //Methods
  public Ingredient forageForIngredient(Board board) {
    //REQUIRES: board != null
    //MODIFIES: ingredients arraylist
    //EFFECTS: returns an ingredient from the board's ingredient deck
    Ingredient ingredient = board.getIngredientFromDeck();
    ingredients.add(ingredient);
    return ingredient;
  }

  public void addGold(int amount) {
    //EFFECTS: adds the given amount to the token's gold balance
    goldBalance += amount;
  }

  public void decreaseGold(int amount) {
    //MODIFIES: goldBalance
    //EFFECTS: decreases the given amount from the token's gold balance
    if (goldBalance > amount) {
      goldBalance -= amount;
    } else {
      goldBalance = 0;
    }
  }

  public void addIngredient(Ingredient ingredient) {
    //REQUIRES: ingredient != null
    //EFFECTS: adds the given ingredient to the token's ingredient list
    ingredients.add(ingredient);
  }

  public ArrayList<Ingredient> getIngredients() {
    //EFFECTS: returns the token's ingredient list
    return ingredients;
  }

  public String getAvatarImage() {
    //EFFECTS: returns the token's avatar image
    return avatarImage;
  }

  public String getUsername() {
    //EFFECTS: returns the token's username
    return username;
  }

  public int getGoldBalance() {
    //EFFECTS: returns the token's gold balance
    return goldBalance;
  }

  public int getReputation() {
    //EFFECTS: returns the token's reputation
    return reputation;
  }

  public int getScore() {
    //EFFECTS: returns the token's score
    return 0;
  }

  public ArrayList<ArtifactCard> getArtifactCards() {
    //EFFECTS: returns the token's artifact card list
    return artifactCards;
  }

  public ArrayList<Potion> getPotions() {
    //EFFECTS: returns the token's potion list
    return potions;
  }

  public Potion makeExperiment(String ingredient1, String ingredient2, Boolean testOnSelf) { 
    //REQUIRES: ingredient1 != null, ingredient2 != null
    //EFFECTS: returns the potion that is created by the given ingredients. 
    //          If the ingredients' color and sign same and size different, the potion is positive or negative.
    //          If the ingredients' color and sign same and size same, the potion is neutral.
    //MODIFIES: ingredients, potions arraylists
    Ingredient ing1 = findIngredientByName(ingredient1);
    Ingredient ing2 = findIngredientByName(ingredient2);

    boolean controller = false;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (
          ing1
            .getAlchemyMarker()
            .getAspectList()
            .get(i)
            .getSign()
            .equals(ing2.getAlchemyMarker().getAspectList().get(j).getSign())
        ) {
          if (
            ing1
              .getAlchemyMarker()
              .getAspectList()
              .get(i)
              .getColor()
              .equals(ing2.getAlchemyMarker().getAspectList().get(j).getColor())
          ) {
            if (
              !ing1
                .getAlchemyMarker()
                .getAspectList()
                .get(i)
                .getSize()
                .equals(ing2.getAlchemyMarker().getAspectList().get(j).getSize())
            ) {
              Potion newPotion = new Potion(
                ing1.getAlchemyMarker().getAspectList().get(j).getColor(),
                ing1,
                ing2,
                ing1.getAlchemyMarker().getAspectList().get(i).getSign(),
                "not negative potion"
              );
              controller = true;
              potions.add(newPotion);
              potionsForFrame.add(newPotion);
              testPotion(newPotion, testOnSelf);
              ingredients.remove(ing1);
              ingredients.remove(ing2);
              return newPotion;
            }
          }
        }
      }
    }
    if (!controller) {
      Potion neutralPotion = new Potion("transparent", ing1, ing2, "0", "not negative potion");
      potions.add(neutralPotion);
      potionsForFrame.add(neutralPotion);
      ingredients.remove(ing1);
      ingredients.remove(ing2);
      return neutralPotion;
    }
    ingredients.remove(ing1);
    ingredients.remove(ing2);
    return null;
  }

  public void testPotion(Potion potion, Boolean testOnSelf) {
    //REQUIRES: potion != null
    //EFFECTS: tests the given potion on the token. If the potion is positive, it decreases the token's sickness level by 1.
    //         If the potion is negative and test on yourself, 
    //         it increases the token's sickness level by 1. If it tested on student, you lose 1 gold
    //MODIFIES: sicknessLevel, goldBalance
    if (testOnSelf) {
      if (potion.getName().equals("-")) {
        increaseSickness(1);
        potion.setGuarantee("negative potion");

        if (sicknessLevel == 3) {
          goldBalance = 0;
        }
      } 
      else if (potion.getName().equals("+")) { 
        if (getSicknessLevel() > 0) {
          decreaseSickness(1);
          potion.setGuarantee("guaranteed");
        } else {
          sicknessLevel = 0;
        }
      }
    } else {
      if (potion.getName().equals("-")) {
        decreaseGold(1);
        potion.setGuarantee("negative potion");
      }
    }
  }

  private Ingredient findIngredientByName(String name) {
    //REQUIRES: ingredient name
    //EFFECTS: returns the ingredient that has the given name 
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(name)) {
        return ingredient;
      }
    }
    return null;
  }

  public void removeIngredient(String ingredient) {
   //MODIFIES: ingredients arraylist
   //EFFECTS: removes the given ingredient from the token's ingredient list 
    for (Ingredient ing : ingredients) {
      if (ing.getName().equals(ingredient)) {
        ingredients.remove(ing);
        break;
      }
    }
  }

  public void sellPotion(String potion) {
    //REQUIRES: potion 
    //MODIFIES: goldBalance
    //EFFECTS: sells the given potion and adds the gold to the token's gold balance
    if (potion.equals("+")) {
      goldBalance += 3;
      removePotion(potion);
    } else if (potion.equals("-")) {
      goldBalance += 1;
      removePotion(potion);
    } else {
      goldBalance += 2;
      removePotion(potion);
    }
  }

  public void removePotion(String potion) {
    //REQUIRES: potion
    //MODIFIES: potions arraylist
    //EFFECTS: removes the given potion from the token's potion list
    for (Potion pot : potions) {
      if (pot.getName().equals(potion)) {
        potions.remove(pot);
        break;
      }
    }
  }

  public void publishTheory(Board board, Ingredient ingredient, AlchemyMarker alchemyMarker) throws Exception {
    //REQUIRES: board, ingredient, alchemyMarker != null
    //EFFECTS: publishes the given theory to the board
    board.publishTheory(ingredient, alchemyMarker, this);
  }

  public boolean debunkTheory(Board board, Theory theory, Aspect aspect) throws Exception {
    //EFFECT: if theory was successfully debunked, return true
    return board.debunkTheory(theory, aspect, this);
  }

  public void addReputation(int amount) {
    //EFFECTS: adds the given amount to the token's reputation
    this.reputation += amount;
  }

  public void decreaseReputation(int amount) {
    //EFFECTS: decreases the given amount from the token's reputation
    this.reputation -= amount;
  }

  public void buyArtifactCard(ArtifactCard artifactCard) {
    //EFFECTS: buys the given artifact card and adds it to the token's artifact card list
    Board.giveArtifactCardtoToken(this, artifactCard);
  }

  public void useArtifactCard(ArtifactCard artifactCard, Board board) {
    //EFFECTS: applies the given artifact card's effect to the board
    artifactCard.applyEffect(this, board, null);
  }

  public void addArtifactCard(ArtifactCard artifactCard) {
    //EFFECTS: adds the given artifact card to the token's artifact card list
    artifactCards.add(artifactCard);
  }

  public void transmuteIngredient(String ingredientName) {
    //EFFECT: transmutes the given ingredient and adds it to the token's ingredient list
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(ingredientName)) {
        ingredients.remove(ingredient);
      }
    }
  }

  public void removeArtifactCard(ArtifactCard artifactCard) {
    //EFFECTS: removes the given artifact card from the token's artifact card list
    artifactCards.remove(artifactCard);
  }

  public void decreaseSickness(int amount) {
    //EFFECTS: decreases the given amount from the token's sickness level
    sicknessLevel -= amount;
  }

  public void increaseSickness(int amount) {
    //EFFECTS: increases the given amount to the token's sickness level
    sicknessLevel += amount;
  }

  public int getSicknessLevel() {
    //EFFECTS: returns the token's sickness level
    return sicknessLevel;
  }

  public boolean checkPrintingPress() {
    //EFFECTS: returns true if the token has the printing press artifact card
    for (ArtifactCard artifactCard : artifactCards) {
      if (artifactCard.getName().equals("Printing Press")) {
        return true;
      }
    }
    return false;
  }

  public ArtifactCard getArtifactCardByName(String name) {
    //EFFECTS: returns the artifact card that has the given name
    for (ArtifactCard artifactCard : artifactCards) {
      if (artifactCard.getName().equals(name)) {
        return artifactCard;
      }
    }
    return null;
  }
  
  public boolean repOK() {
    //EFFECTS: returns true if the rep invariant is satisfied
    if(username == null){ return false; }
    if(avatarImage == null){ return false; }
    if(tokenImage == null){ return false; }
    if(goldBalance < 0){ return false; }
    if(sicknessLevel < 0){ return false; }
    if(reputation < 0){ return false; }

    return true;
  }
}
