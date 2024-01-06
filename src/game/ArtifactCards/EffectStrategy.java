package game.ArtifactCards;

import game.Board;
import game.Ingredient;
import game.Token;

public interface EffectStrategy {
  void applyEffect(Token token, Board board, String ing);
}
