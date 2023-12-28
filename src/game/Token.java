package game;

import game.ArtifactCards.ArtifactCard;
import java.util.ArrayList;
import ui.BoardJFrame;
import ui.MakeExperimentJFrame;

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
  public static ArrayList<Potion> potionsForFrame;

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
    potionsForFrame = new ArrayList<Potion>();
  }

  public Ingredient forageForIngredient(Board board) {
    Ingredient ingredient = board.getIngredientFromDeck();
    ingredients.add(ingredient);
    return ingredient;
  }

  public void addGold(int amount) {
    goldBalance += amount;
  }

  public void decreaseGold(int amount) {
    if (goldBalance > amount) {
      goldBalance -= amount;
    } else {
      goldBalance = 0;
    }
  }

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

  public ArrayList<Potion> getPotions() {
    return potions;
  }

  public Potion makeExperiment(String ingredient1, String ingredient2, Boolean testOnSelf) { //if player test on student false, otherwise true;
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
              Potion newPotion = new Potion(ing1.getAlchemyMarker().getAspectList().get(j).getColor(), 
                                            ing1, ing2, ing1.getAlchemyMarker().getAspectList().get(i).getSign(),
                                            "not negative potion");
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
      Potion neutralPotion = new Potion("transparent", ing1, ing2, "0","not negative potion");
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
    if (testOnSelf) {
      if (potion.getName().equals("-")) {
        increaseSickness(1);
        potion.setGuarantee("negative potion");
        
        if (sicknessLevel == 3) {
          goldBalance = 0;
        }
      }
      else if (potion.getName().equals("+")) { //if sickness level is more than 0, positive potion decrease it by 1
        if(getSicknessLevel() > 0){
          decreaseSickness(1);
          potion.setGuarantee("guaranteed");
        }
        else{
          sicknessLevel = 0;
        }
      }
      }
    else {
      if (potion.getName().equals("-")) {
        decreaseGold(1); 
        potion.setGuarantee("negative potion");
      }
    }
  }

  private Ingredient findIngredientByName(String name) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(name)) {
        return ingredient;
      }
    }
    return null;
  }

  public void removeIngredient(String ingredient) {
    for (Ingredient ing : ingredients) {
      if (ing.getName().equals(ingredient)) {
        ingredients.remove(ing);
        break;
      }
    }
  }

  public void sellPotion(String potion) {
    if (potion.equals("+")) {
      goldBalance += 3;
      removePotion(potion);
    }
    else if (potion.equals("-")) {
      goldBalance += 1;
      removePotion(potion);
    }
    else {
      goldBalance += 2;
      removePotion(potion);
    }
    
  }

  public void removePotion(String potion) {
    for (Potion pot : potions) {
      if (pot.getName().equals(potion)) {
        potions.remove(pot);
        break;
      }
    }
  }

  public void publishTheory(Board board, Ingredient ingredient, AlchemyMarker alchemyMarker) throws Exception {
    board.publishTheory(ingredient, alchemyMarker, this);
  }

  public boolean debunkTheory(Board board, Theory theory, Aspect aspect) throws Exception {
    // if theory was successfully debunked, return true
    return board.debunkTheory(theory, aspect, this);
  }

  public void addReputation(int amount) {
    reputation += amount;
  }

  public void decreaseReputation(int amount) {
    reputation -= amount;
  }

  public void buyArtifactCard(ArtifactCard artifactCard) {
    Board.giveArtifactCardtoToken(this, artifactCard);
  }

  //Burada niye direkt artifactCard.applyEffect yapamıyom anlamadım. SIKINTI ÇIKARABİLİR
  public void useArtifactCard(ArtifactCard artifactCard) {
    Effect effect = (Effect) artifactCard;
    effect.applyEffect(this);
  }

  public void addArtifactCard(ArtifactCard artifactCard) {
    artifactCards.add(artifactCard);
  }

  public void transmuteIngredient(String ingredientName) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(ingredientName)) {
        ingredients.remove(ingredient);
      }
    }
  }

  public void removeArtifactCard(ArtifactCard artifactCard) {
    artifactCards.remove(artifactCard);
  }

  public void decreaseSickness(int amount) {
    sicknessLevel -= amount;
  }
  public void increaseSickness(int amount) {
    sicknessLevel += amount;
  }

  public int getSicknessLevel() {
    return sicknessLevel;
  }
}
