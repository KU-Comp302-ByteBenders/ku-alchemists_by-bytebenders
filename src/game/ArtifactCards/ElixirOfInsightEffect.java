package game.ArtifactCards;

import game.Board;
import game.Token;
import ui.ElixirJFrame;

public class ElixirOfInsightEffect implements EffectStrategy {
  
  @Override
  public void applyEffect(Token token, Board Board) {
    ElixirJFrame elixirJFrame = new ElixirJFrame(Board);
  }


    
}
