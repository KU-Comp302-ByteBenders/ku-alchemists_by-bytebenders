package game.ArtifactCards;

import game.Board;
import game.Token;
import ui.ElixirJFrame;

public class ElixirOfInsightEffect implements EffectStrategy {

  private static final long serialVersionUID = 70L;

  @Override
  public void applyEffect(Token token, Board Board, String ing) {
    ElixirJFrame elixirJFrame = new ElixirJFrame(Board);
  }
}
