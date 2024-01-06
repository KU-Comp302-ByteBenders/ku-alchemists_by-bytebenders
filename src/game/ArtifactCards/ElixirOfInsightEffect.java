package game.ArtifactCards;

import game.Board;
import game.Ingredient;
import game.Token;
import ui.ElixirJFrame;

public class ElixirOfInsightEffect implements EffectStrategy {
  
  @Override
  public void applyEffect(Token token, Board Board, String ing) {
    ElixirJFrame elixirJFrame = new ElixirJFrame(Board);
  }


    
}
