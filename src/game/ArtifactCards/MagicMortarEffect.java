package game.ArtifactCards;

import game.Board;
import game.Token;

public class MagicMortarEffect implements EffectStrategy {
  
  @Override
  public void applyEffect(Token token, Board board) {
    token.addGold(5);
  }


}
