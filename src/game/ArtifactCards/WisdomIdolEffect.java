package game.ArtifactCards;

import javax.swing.JOptionPane;

import game.Board;
import game.Token;
import ui.WisdomIdolJFrame;

public class WisdomIdolEffect implements EffectStrategy {

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    //WisdomIdolJFrame wisdomIdolJFrame = new WisdomIdolJFrame(token.getArtifactCardByName("Wisdom Idol"));
    ArtifactCard card = token.getArtifactCardByName("Wisdom Idol");

    // Add confirmation dialog
    int confirmed = JOptionPane.showConfirmDialog(null, 
      "Are you sure you want to apply the Wisdom Idol effect?", "Confirmation", 
      JOptionPane.YES_NO_OPTION);

    if (confirmed == JOptionPane.YES_OPTION) {
      // Apply the effect
      //wisdomIdolJFrame.setVisible(true);
      card.setToBeAppliedFlag(true);
    }
  }
}
