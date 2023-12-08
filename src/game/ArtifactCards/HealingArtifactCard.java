package game.ArtifactCards;

import game.Effect;
import game.Token;

// This is the Artifact Card type that heals the user a certain amount
public class HealingArtifactCard extends ArtifactCard implements Effect {

  private int amount;

  public HealingArtifactCard(String name, int amount, int goldPrice) {
    super(name, goldPrice);
    this.amount = amount;
  }

  @Override
  public void applyEffect(Token token) {
    token.decreaseSickness(amount);
    System.out.println("applyEffect for healingArtifactCards work correctly");
    System.out.println(token.getSicknessLevel());
  }
}
