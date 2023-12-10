package game.ArtifactCards;

import game.Board;
import game.Effect;
import game.Token;
import ui.ElixirJFrame;

// This is the Artifact Card type that heals the user a certain amount
public class ElixirArtifactCard extends ArtifactCard implements Effect {

  Board myBoard;

  public ElixirArtifactCard(String name, int goldPrice, Board board) {
    super(name, goldPrice);
    myBoard = board;
  }

  @Override
  public void applyEffect(Token token) {
    ElixirJFrame elixirJFrame = new ElixirJFrame(myBoard);
  }
}
