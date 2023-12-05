package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import ui.BoardJFrame;

import game.AlchemyMarker;
import game.Aspect;
import game.ArtifactCards.ArtifactCard;
import game.ArtifactCards.GoldArtifactCard;
import game.ArtifactCards.HealingArtifactCard;
import game.ArtifactCards.ReputationArtifactCard;


public class Board {

  private ArrayList<Token> tokens;
  private ArrayList<Ingredient> ingredients;
  private static ArrayList<ArtifactCard> artifactCards;
  private ArrayList<Theory> theories;

  public Board(String username1, String username2, String avatar1, String avatar2) {
    tokens = new ArrayList<Token>();
    ingredients = new ArrayList<Ingredient>();
    artifactCards = new ArrayList<ArtifactCard>();
    theories = new ArrayList<Theory>();

    Token token1 = new Token(username1, avatar1, avatar1);
    Token token2 = new Token(username2, avatar2, avatar2);
    tokens.add(token1);
    tokens.add(token2);
    this.addIngredient();
    this.addArtifactCard();
    token1.addGold(10);
    token2.addGold(10);
    theories = new ArrayList<Theory>();

    BoardJFrame boardJFrame = new BoardJFrame(this);
  }

  public void createIngredients() {
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

    Ingredient ingredient1 = new Ingredient("dragon fruit", 1, alchemyMarker1);
    Ingredient ingredient2 = new Ingredient("emerald", 2, alchemyMarker2);
    Ingredient ingredient3 = new Ingredient("feather", 3, alchemyMarker3);
    Ingredient ingredient4 = new Ingredient("ginger", 4, alchemyMarker4);
    Ingredient ingredient5 = new Ingredient("moondust", 5, alchemyMarker5);
    Ingredient ingredient6 = new Ingredient("obsidian", 6, alchemyMarker6);
    Ingredient ingredient7 = new Ingredient("redstone", 7, alchemyMarker7);
    Ingredient ingredient8 = new Ingredient("saffron", 8, alchemyMarker8);

    for (int i = 0; i < 4; i++) {
      ingredients.add(ingredient1);
      ingredients.add(ingredient2);
      ingredients.add(ingredient3);
      ingredients.add(ingredient4);
      ingredients.add(ingredient5);
      ingredients.add(ingredient6);
      ingredients.add(ingredient7);
      ingredients.add(ingredient8);
    }

    Collections.shuffle(ingredients);
  }

  public void addIngredient() {
    createIngredients();
    for (Token token : tokens) {
      for (int i = 0; i < 2; i++) {
        token.addIngredient(ingredients.get(0));
        ingredients.remove(0);
      }
    }
  }

  public void addArtifactCard(){
    ArtifactCard artifactCard1 = new GoldArtifactCard("Small Fortune Pouch",1);
    ArtifactCard artifactCard2 = new GoldArtifactCard("Small Fortune Pouch",1);
    ArtifactCard artifactCard3 = new GoldArtifactCard("Small Fortune Pouch",1);
    ArtifactCard artifactCard4 = new GoldArtifactCard("Treasure Trove",2);
    ArtifactCard artifactCard5 = new GoldArtifactCard("Treasure Trove",2);
    ArtifactCard artifactCard6 = new GoldArtifactCard("King's Bounty",3);
    ArtifactCard artifactCard7 = new HealingArtifactCard("Healing Draught",1);
    ArtifactCard artifactCard8 = new HealingArtifactCard("Healing Draught",1);
    ArtifactCard artifactCard9 = new HealingArtifactCard("Healing Draught",1);
    ArtifactCard artifactCard10 = new HealingArtifactCard("Elixir of Vitality",2);
    ArtifactCard artifactCard11 = new HealingArtifactCard("Elixir of Vitality",2);
    ArtifactCard artifactCard12 = new HealingArtifactCard("Celestial Mend",3);
    ArtifactCard artifactCard13 = new ReputationArtifactCard("Kindly Gesture",1);
    ArtifactCard artifactCard14 = new ReputationArtifactCard("Kindly Gesture",1);
    ArtifactCard artifactCard15 = new ReputationArtifactCard("Kindly Gesture",1);
    ArtifactCard artifactCard16 = new ReputationArtifactCard("Respected Deed",2);
    ArtifactCard artifactCard17 = new ReputationArtifactCard("Respected Deed",2);
    ArtifactCard artifactCard18 = new ReputationArtifactCard("Virtue Badge",3);

    artifactCards.add(artifactCard1);
    artifactCards.add(artifactCard2);
    artifactCards.add(artifactCard3);
    artifactCards.add(artifactCard4);
    artifactCards.add(artifactCard5);
    artifactCards.add(artifactCard6);
    artifactCards.add(artifactCard7);
    artifactCards.add(artifactCard8);
    artifactCards.add(artifactCard9);
    artifactCards.add(artifactCard10);
    artifactCards.add(artifactCard11);
    artifactCards.add(artifactCard12);
    artifactCards.add(artifactCard13);
    artifactCards.add(artifactCard14);
    artifactCards.add(artifactCard15);
    artifactCards.add(artifactCard16);
    artifactCards.add(artifactCard17);
    artifactCards.add(artifactCard18);

    Collections.shuffle(artifactCards, new Random());

}
    //Named buyArtifactCard in the design
    public static void giveArtifactCardtoToken(Token token){
        Random random = new Random();
        int randomNumber = random.nextInt();

        if (token.getGoldBalance() >= 1) {
            token.decreaseGold(1);
            token.addArtifactCard(artifactCards.get(randomNumber));
            artifactCards.remove(randomNumber);}

        else{
            System.err.println("The player does not have enough gold to buy an artifact card");
        }

    }


  public Ingredient getIngredientFromDeck() {
    int maxNumber = ingredients.size();
    Random random = new Random();
    int randomIndex = random.nextInt(maxNumber);
    Ingredient returnValue = ingredients.get(randomIndex);
    ingredients.remove(randomIndex);
    return returnValue;
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  public ArrayList<Theory> getTheories() {
    return theories;
  }

  public ArrayList<Token> getTokens() {
    return tokens;
  }
}
