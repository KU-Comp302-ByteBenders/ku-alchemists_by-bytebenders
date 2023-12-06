package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import ui.BoardJFrame;

public class Board {

  private ArrayList<Token> tokens;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<ArtifactCard> artifactCards;
  private ArrayList<Theory> theories;

  // These two will be used in publishTheory and debunkTheory.
  // The order of static ingredients will remain the same.
  private ArrayList<Ingredient> staticIngredients = new ArrayList<Ingredient>(); 
  private ArrayList<AlchemyMarker> staticAlchemyMarkers = new ArrayList<AlchemyMarker>();

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

    AlchemyMarker alchemyMarker1 = new AlchemyMarker(aspect7, aspect8, aspect9, "src/ui/utils/markers/alchemy_marker1.png");
    AlchemyMarker alchemyMarker2 = new AlchemyMarker(aspect10, aspect11, aspect12, "src/ui/utils/markers/alchemy_marker2.png");
    AlchemyMarker alchemyMarker3 = new AlchemyMarker(aspect1, aspect12, aspect5, "src/ui/utils/markers/alchemy_marker3.png");
    AlchemyMarker alchemyMarker4 = new AlchemyMarker(aspect9, aspect4, aspect2, "src/ui/utils/markers/alchemy_marker4.png");
    AlchemyMarker alchemyMarker5 = new AlchemyMarker(aspect1, aspect6, aspect8, "src/ui/utils/markers/alchemy_marker5.png");
    AlchemyMarker alchemyMarker6 = new AlchemyMarker(aspect3, aspect11, aspect4, "src/ui/utils/markers/alchemy_marker6.png");
    AlchemyMarker alchemyMarker7 = new AlchemyMarker(aspect3, aspect7, aspect5, "src/ui/utils/markers/alchemy_marker7.png");
    AlchemyMarker alchemyMarker8 = new AlchemyMarker(aspect6, aspect7, aspect5, "src/ui/utils/markers/alchemy_marker8.png");

    staticAlchemyMarkers.add(alchemyMarker1);
    staticAlchemyMarkers.add(alchemyMarker2);
    staticAlchemyMarkers.add(alchemyMarker3);
    staticAlchemyMarkers.add(alchemyMarker4);
    staticAlchemyMarkers.add(alchemyMarker5);
    staticAlchemyMarkers.add(alchemyMarker6);
    staticAlchemyMarkers.add(alchemyMarker7);
    staticAlchemyMarkers.add(alchemyMarker8);

    Ingredient ingredient1 = new Ingredient("dragon fruit", 1, alchemyMarker1, "src/ui/utils/ingredient_1.jpg");
    Ingredient ingredient2 = new Ingredient("emerald", 2, alchemyMarker2, "src/ui/utils/ingredient_2.jpg");
    Ingredient ingredient3 = new Ingredient("feather", 3, alchemyMarker3, "src/ui/utils/ingredient_3.jpg");
    Ingredient ingredient4 = new Ingredient("ginger", 4, alchemyMarker4, "src/ui/utils/ingredient_4.jpg");
    Ingredient ingredient5 = new Ingredient("moondust", 5, alchemyMarker5, "src/ui/utils/ingredient_5.jpg");
    Ingredient ingredient6 = new Ingredient("obsidian", 6, alchemyMarker6, "src/ui/utils/ingredient_6.jpg");
    Ingredient ingredient7 = new Ingredient("redstone", 7, alchemyMarker7, "src/ui/utils/ingredient_7.jpg");
    Ingredient ingredient8 = new Ingredient("saffron", 8, alchemyMarker8, "src/ui/utils/ingredient_8.jpg");

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

    staticIngredients.addAll(ingredients); // Copy the newly made deck to staticIngredients
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

  public void addArtifactCard() {
    ArtifactCard artifactCard1 = new ArtifactCard("art1");
    ArtifactCard artifactCard2 = new ArtifactCard("art2");
    ArtifactCard artifactCard3 = new ArtifactCard("art3");
    ArtifactCard artifactCard4 = new ArtifactCard("art4");
    ArtifactCard artifactCard5 = new ArtifactCard("art5");
    ArtifactCard artifactCard6 = new ArtifactCard("art6");
    ArtifactCard artifactCard7 = new ArtifactCard("art7");
    ArtifactCard artifactCard8 = new ArtifactCard("art8");
    ArtifactCard artifactCard9 = new ArtifactCard("art9");
    ArtifactCard artifactCard10 = new ArtifactCard("art10");

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

    Collections.shuffle(artifactCards);
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

  public ArrayList<Ingredient> getStaticIngredients() {
    return staticIngredients;
  }

  public ArrayList<AlchemyMarker> getStaticAlchemyMarkers() {
    return staticAlchemyMarkers;
  }

  public void publishTheory(Ingredient ingredient, AlchemyMarker alchemyMarker, Token token) throws Exception {
    // Check if player has enough gold
    if (ingredient == null || alchemyMarker == null) {
      throw new Exception("Please select an ingredient and an alchemy marker!");
    }
    
    if (token.getGoldBalance() < 1) {
      throw new Exception("Not enough gold!");
    }

    for (Theory theory : theories) {
      if (theory.isAboutIngredient(ingredient)) { // There is already a theory about the ingredient
        throw new Exception("A theory on this ingredient already exists!");

      }
      if (theory.hasAlchemyMarker(alchemyMarker)) { // There is the same Alchemy Marker on another theory
        throw new Exception("Your Alchemy Marker is already on another theory!");
      }
    }

    // Create theory and add to the theories list
    Theory theory = new Theory(ingredient, alchemyMarker, token);
    theories.add(theory);

    token.addReputation(1);
    token.decreaseGold(1); 
    
    System.out.println("Theory published!");
  }

  public void debunkTheory(Theory theory, AlchemyMarker alchemyMarker, Token token) {
    try {
      if (theory.belongsToToken(token)) {
        throw new Exception("You can't debunk your own theory!");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    if (theory.debunkSuccess(alchemyMarker)) {
      token.addReputation(2); // Increase reputation of the debunker
      theory.getTheoryOwner().decreaseReputation(1); // Decrease reputation of the publisher
    } else { // Debunk failed
      token.decreaseReputation(1); // Decrease reputation of the publisher
    }
  }
}
