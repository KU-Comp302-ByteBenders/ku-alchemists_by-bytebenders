package game.ArtifactCards;

import game.Effect;
import game.Token;

// This is the Artifact Card type that gives it's user certain amount of reputation points
public class ReputationArtifactCard extends ArtifactCard implements Effect {
  private int points;

  public ReputationArtifactCard(String name, int points, int goldPrice) {
      super(name, goldPrice);
      this.points = points;
  }

  @Override
  public void applyEffect(Token token) {
      token.addReputation(points);
      System.out.println("applyEffect for reputationArtifactCards work correctly");
      System.out.println(token.getReputation()); 
  }
}
