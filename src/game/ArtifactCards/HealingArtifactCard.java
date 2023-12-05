package game.ArtifactCards;

import game.Effect;
import game.Token;

// This is the Artifact Card type that heals the user a certain amount
public class HealingArtifactCard extends ArtifactCard implements Effect {
  private int amount;

  public HealingArtifactCard(String name,int amount) {
      super(name);
      this.amount = amount;
  }

  @Override
  public void applyEffect(Token token) {
      token.decreaseSickness(amount);
  }
}
