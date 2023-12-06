package game.ArtifactCards;

import game.Board;
import game.Effect;
import game.Ingredient;
import game.Token;

// This is the Artifact Card type that gives it's user certain amount of gold
public class IngredientArtifactCard extends ArtifactCard implements Effect {
  private int ingredientAmount;
  private Board board;

  public IngredientArtifactCard(Board board, String name, int ingAmount, int goldPrice) {
      super(name, goldPrice);
      this.ingredientAmount = ingAmount;
      this.board = board;
  }

  @Override
  public void applyEffect(Token token) {
      board.addXamountIngredientToToken(token,ingredientAmount);
      System.out.println("applyEffect for goldArtifactCards work correctly");
  }
}
