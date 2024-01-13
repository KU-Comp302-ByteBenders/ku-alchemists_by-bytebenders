package game.ArtifactCards;

import game.Board;
import game.Ingredient;
import game.Token;
import java.io.Serializable;

public class MagicMortarEffect implements EffectStrategy {

  private static final long serialVersionUID = 71L;

  @Override
  public void applyEffect(Token token, Board board, String ingName1) {
    Ingredient mortarIng = token.findIngredientByName(ingName1);
    token.addIngredient(mortarIng);
  }
}
