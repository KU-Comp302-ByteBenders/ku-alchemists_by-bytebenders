package game.ArtifactCards;

import java.io.Serializable;

import game.Board;
import game.Ingredient;
import game.Token;
import ui.ElixirJFrame;

public class ElixirOfInsightEffect implements EffectStrategy, Serializable {

  private static final long serialVersionUID = 70L;
  
  @Override
  public void applyEffect(Token token, Board Board, String ing) {
    ElixirJFrame elixirJFrame = new ElixirJFrame(Board);
  }


    
}
