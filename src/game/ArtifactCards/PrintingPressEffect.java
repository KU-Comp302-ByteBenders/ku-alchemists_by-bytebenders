package game.ArtifactCards;

import game.Board;
import game.Token;

public class PrintingPressEffect implements EffectStrategy {

  private static final long serialVersionUID = 72L;

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    token.addGold(1);
  }
}
