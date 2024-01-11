package game.ArtifactCards;

import java.io.Serializable;

import javax.swing.JOptionPane;

import game.Board;
import game.Game;
import game.Token;

public class WisdomIdolEffect implements EffectStrategy, Serializable {

  private static final long serialVersionUID = 73L;

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    ArtifactCard card = token.getArtifactCardByName("Wisdom Idol");
    Game.openWisdomIdolConfirmationDialog(card);
  }
}
