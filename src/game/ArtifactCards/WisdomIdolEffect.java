package game.ArtifactCards;

import javax.swing.JOptionPane;

import game.Board;
import game.Game;
import game.Token;

public class WisdomIdolEffect implements EffectStrategy {

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    ArtifactCard card = token.getArtifactCardByName("Wisdom Idol");
    Game.openWisdomIdolConfirmationDialog(card);
  }
}
