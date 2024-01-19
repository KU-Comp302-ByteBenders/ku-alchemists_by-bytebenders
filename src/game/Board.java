package game;

import game.ArtifactCards.ArtifactCard;
import game.ArtifactCards.ElixirOfInsightEffect;
import game.ArtifactCards.MagicMortarEffect;
import game.ArtifactCards.PrintingPressEffect;
import game.ArtifactCards.WisdomIdolEffect;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import ui.OfflineBoardJFrame;

public class Board implements Serializable {

  private ArrayList<Token> tokens;
  private ArrayList<Ingredient> ingredients;
  private ArrayList<ArtifactCard> artifactCards;
  private ArrayList<Theory> theories;
  private State state;
  private static final long serialVersionUID = 1L;

  // For online version
  private int round = 1;
  private int turn = 1;
  private int whoseTurn = 0;

  // These two will be used in publishTheory and debunkTheory.
  // The order of static ingredients will remain the same.
  private ArrayList<Ingredient> staticIngredients = new ArrayList<Ingredient>();
  private ArrayList<AlchemyMarker> staticAlchemyMarkers = new ArrayList<AlchemyMarker>();

  public Board(ArrayList<Token> tokens) {
    this.tokens = tokens;
    ingredients = new ArrayList<Ingredient>();
    artifactCards = new ArrayList<ArtifactCard>();
    theories = new ArrayList<Theory>();

    this.addIngredient();
    this.createArtifactCards();
    for (int i = 0; tokens.size() > i; i++) {
      tokens.get(i).addGold(10);
    }

    // init state object
    state = new State(tokens);
    Token firstToken = state.selectRandomToken();
    OfflineBoardJFrame offlineBoardJFrame = new OfflineBoardJFrame(firstToken, this, state, tokens); // online board
                                                                                                     // creation
    // TransitionStarterJFrame transitionStarterJFrame = new
    // TransitionStarterJFrame(firstToken, this, state);

    theories = new ArrayList<Theory>();
  }

  public Board(HashMap<String, String> credentials) {
    int length = credentials.size();
    tokens = new ArrayList<Token>();
    ingredients = new ArrayList<Ingredient>();
    artifactCards = new ArrayList<ArtifactCard>();
    theories = new ArrayList<Theory>();

    for (String username : credentials.keySet()) {
      String avatar = credentials.get(username);
      Token token = new Token(username, avatar, avatar);
      tokens.add(token);
      token.addGold(10);
    }

    state = new State(tokens);

    this.addIngredient();
    this.createArtifactCards();
  }

  public void createIngredients() {
    Aspect aspect1 = new Aspect("Red", "Small", "+", "src/ui/utils/aspects/aspect_1.png");
    Aspect aspect2 = new Aspect("Blue", "Small", "+", "src/ui/utils/aspects/aspect_2.png");
    Aspect aspect3 = new Aspect("Yellow", "Small", "+", "src/ui/utils/aspects/aspect_3.png");
    Aspect aspect4 = new Aspect("Red", "Small", "-", "src/ui/utils/aspects/aspect_4.png");
    Aspect aspect5 = new Aspect("Blue", "Small", "-", "src/ui/utils/aspects/aspect_5.png");
    Aspect aspect6 = new Aspect("Yellow", "Small", "-", "src/ui/utils/aspects/aspect_6.png");
    Aspect aspect7 = new Aspect("Red", "Big", "+", "src/ui/utils/aspects/aspect_7.png");
    Aspect aspect8 = new Aspect("Blue", "Big", "+", "src/ui/utils/aspects/aspect_8.png");
    Aspect aspect9 = new Aspect("Yellow", "Big", "+", "src/ui/utils/aspects/aspect_9.png");
    Aspect aspect10 = new Aspect("Red", "Big", "-", "src/ui/utils/aspects/aspect_10.png");
    Aspect aspect11 = new Aspect("Blue", "Big", "-", "src/ui/utils/aspects/aspect_11.png");
    Aspect aspect12 = new Aspect("Yellow", "Big", "-", "src/ui/utils/aspects/aspect_12.png");

    AlchemyMarker alchemyMarker1 = new AlchemyMarker(
        aspect7,
        aspect8,
        aspect9,
        "src/ui/utils/markers/alchemy_marker1.png");
    AlchemyMarker alchemyMarker2 = new AlchemyMarker(
        aspect10,
        aspect11,
        aspect12,
        "src/ui/utils/markers/alchemy_marker2.png");
    AlchemyMarker alchemyMarker3 = new AlchemyMarker(
        aspect1,
        aspect12,
        aspect5,
        "src/ui/utils/markers/alchemy_marker3.png");
    AlchemyMarker alchemyMarker4 = new AlchemyMarker(
        aspect9,
        aspect4,
        aspect2,
        "src/ui/utils/markers/alchemy_marker4.png");
    AlchemyMarker alchemyMarker5 = new AlchemyMarker(
        aspect1,
        aspect6,
        aspect8,
        "src/ui/utils/markers/alchemy_marker5.png");
    AlchemyMarker alchemyMarker6 = new AlchemyMarker(
        aspect3,
        aspect11,
        aspect4,
        "src/ui/utils/markers/alchemy_marker6.png");
    AlchemyMarker alchemyMarker7 = new AlchemyMarker(
        aspect3,
        aspect7,
        aspect5,
        "src/ui/utils/markers/alchemy_marker7.png");
    AlchemyMarker alchemyMarker8 = new AlchemyMarker(
        aspect6,
        aspect7,
        aspect5,
        "src/ui/utils/markers/alchemy_marker8.png");

    staticAlchemyMarkers.add(alchemyMarker1);
    staticAlchemyMarkers.add(alchemyMarker2);
    staticAlchemyMarkers.add(alchemyMarker3);
    staticAlchemyMarkers.add(alchemyMarker4);
    staticAlchemyMarkers.add(alchemyMarker5);
    staticAlchemyMarkers.add(alchemyMarker6);
    staticAlchemyMarkers.add(alchemyMarker7);
    staticAlchemyMarkers.add(alchemyMarker8);

    Ingredient ingredient1 = new Ingredient("dragon_fruit", 1, alchemyMarker1, "src/ui/utils/ingredient_1.jpg");
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

  public void addXamountIngredientToToken(Token token, int xAmount) {
    for (int i = 0; i < xAmount; i++) {
      token.addIngredient(ingredients.get(xAmount));
      ingredients.remove(xAmount);
    }
  }

  public void createArtifactCards() {
    ArtifactCard artifactCard1 = new ArtifactCard("Elixir of Insight", 2, new ElixirOfInsightEffect());
    ArtifactCard artifactCard2 = new ArtifactCard("Magic Mortar", 1, new MagicMortarEffect());
    ArtifactCard artifactCard3 = new ArtifactCard("Printing Press", 2, new PrintingPressEffect());
    ArtifactCard artifactCard4 = new ArtifactCard("Wisdom Idol", 3, new WisdomIdolEffect());

    artifactCards.add(artifactCard1);
    artifactCards.add(artifactCard2);
    artifactCards.add(artifactCard3);
    artifactCards.add(artifactCard4);
  }

  public Ingredient getIngredientFromDeck() {
    Ingredient returnValue = ingredients.get(0);
    ingredients.remove(0);
    return returnValue;
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  public ArrayList<Theory> getTheories() {
    return theories;
  }

  public ArrayList<ArtifactCard> getArtifactCards() {
    return artifactCards;
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

  public State getState() {
    return state;
  }

  public void publishTheory(Ingredient ingredient, AlchemyMarker alchemyMarker, Token token) throws Exception {
    // Check if player has enough gold
    if (ingredient == null || alchemyMarker == null) {
      throw new Exception("Please select an ingredient and an alchemy marker!");
    }

    if (token.getGoldBalance() < 1 && !token.checkPrintingPress()) {
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

    // Exception checks passed
    // Create theory and add to the theories list
    Theory theory = new Theory(ingredient, alchemyMarker, token);
    theories.add(theory);

    token.addReputation(1);
    token.decreaseGold(1);

    // If the player has artifact card printing press, give him 1 gold
    if (token.checkPrintingPress() && token.getGoldBalance() >= 1) {
      token.getArtifactCardByName("Printing Press").applyEffect(token, this, null);
    }

    System.out.println("Theory published!");
  }

  public boolean debunkTheory(Theory theory, Aspect aspect, Token token) throws Exception {
    if (theory.belongsToToken(token)) {
      throw new Exception("You can't debunk your own theory!");
    }

    boolean wisdomIdolAppliedFlag = false;
    Token owner = theory.getTheoryOwner();
    ArtifactCard card = owner.getArtifactCardByName("Wisdom Idol");
    if (card != null) {
      card.applyEffect(owner, this, null);
      wisdomIdolAppliedFlag = card.isToBeAppliedFlag();
    }

    // debunk was successful but the player played it's artifact card wisdom idol
    if (theory.debunkSuccess(aspect) && wisdomIdolAppliedFlag == true) {
      token.addReputation(2); // Increase reputation of the debunker
      owner.removeArtifactCard(card); // Remove the used wisdom idol card from the player
      return true;
    } else if (theory.debunkSuccess(aspect) && wisdomIdolAppliedFlag == false) {
      token.addReputation(2); // Increase reputation of the debunker
      theory.getTheoryOwner().decreaseReputation(1); // Decrease reputation of the publisher
      return true;
    } else if (!theory.debunkSuccess(aspect) && wisdomIdolAppliedFlag == true) { // Debunk failed
      token.decreaseReputation(1); // Decrease reputation of the publisher
      owner.removeArtifactCard(card); // Remove the used wisdom idol card from the player
      return false;
    } else {
      token.decreaseReputation(1); // Decrease reputation of the publisher
      return false;
    }
  }

  public void addArtifactCardToToken(Token token, ArtifactCard artifactCard) {
    token.addArtifactCard(artifactCard);
  }

  // This method was named buyArtifactCard in the design
  public static void giveArtifactCardtoToken(Token token, ArtifactCard artifactCard) {
    if (token.getGoldBalance() >= artifactCard.getGoldPrice()) {
      token.decreaseGold(artifactCard.getGoldPrice());
      token.addArtifactCard(artifactCard);
      System.out.println("artifactCard succesfully added to token");
    } else {
      System.err.println("The player does not have enough gold to buy an artifact card");
    }
  }

  public Ingredient getIngredientByName(String name) {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getName().equals(name)) {
        return ingredient;
      }
    }
    return null;
  }

  public void reorderIngredients(Ingredient firstIng, Ingredient secondIng, Ingredient thirdIng) {
    this.ingredients.set(0, firstIng);
    this.ingredients.set(1, secondIng);
    this.ingredients.set(2, thirdIng);
  }

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  public int getWhoseTurn() {
    return whoseTurn;
  }

  public void setWhoseTurn(int whoseTurn) {
    this.whoseTurn = whoseTurn;
  }

  public void endTurn() {
    if (whoseTurn == tokens.size() - 1) {
      turn++;
      if (turn % 3 == 1) {
        round++;
        System.out.println("turn: " + turn);
        System.out.println("round: " + round);
        if (round == 4) {
          EndGamer endGamer = new EndGamer(this);
          endGamer.openEndGame();
        }
      }
      whoseTurn = 0;
    } else {
      whoseTurn++;
    }
  }
}
