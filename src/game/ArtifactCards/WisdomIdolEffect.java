package game.ArtifactCards;

import game.Board;
import game.Token;
import ui.WisdomIdolJFrame;

public class WisdomIdolEffect implements EffectStrategy {

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    WisdomIdolJFrame wisdomIdolJFrame = new WisdomIdolJFrame(token.getArtifactCardByName("Wisdom Idol"));
  }
}
