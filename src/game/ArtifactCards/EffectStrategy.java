package game.ArtifactCards;

import game.Board;
import game.Token;

public interface EffectStrategy {
  void applyEffect(Token token, Board board);
}
