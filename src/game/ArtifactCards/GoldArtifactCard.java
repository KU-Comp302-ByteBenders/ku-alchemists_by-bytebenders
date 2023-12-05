package game.ArtifactCards;

import game.Effect;
import game.Token;

// This is the Artifact Card type that gives it's user certain amount of gold
public class GoldArtifactCard extends ArtifactCard implements Effect {
  private int goldAmount;

  public GoldArtifactCard(String name, int goldAmount) {
      super(name);
      this.goldAmount = goldAmount;
  }

  @Override
  public void applyEffect(Token token) {
      token.addGold(goldAmount);
  }
}
