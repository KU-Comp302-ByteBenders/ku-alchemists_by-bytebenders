package game.ArtifactCards;

import game.Effect;
import game.Token;

// This is the Artifact Card type that gives it's user certain amount of reputation points
public class ReputationArtifactCard extends ArtifactCard implements Effect {
  private int points;

  public ReputationArtifactCard(String name, int points) {
      super(name);
      this.points = points;
  }

  @Override
  public void applyEffect(Token token) {
      token.addReputation(points);
  }
}
