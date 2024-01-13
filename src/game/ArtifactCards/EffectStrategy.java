package game.ArtifactCards;

import game.Board;
import game.Token;
import java.io.Serializable;

public interface EffectStrategy extends Serializable {
  void applyEffect(Token token, Board board, String ing);
}
