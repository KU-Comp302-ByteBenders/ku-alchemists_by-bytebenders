package game.ArtifactCards;

import game.Token;

//We used the strategy pattern while creating the artifact cards. We created an effect interface and
//made the different artifact card classes have different implementations of the applyEffect method of
//the effect class.
public class ArtifactCard {

  private String name;
  private Token owner = null; // ArtifactCard has no owner by default
  private int goldPrice;

  public ArtifactCard(String name, int goldPrice) {
    this.name = name;
    this.goldPrice = goldPrice;
  }

  public String getName() {
    return name;
  }

  public int getGoldPrice() {
    return goldPrice;
  }
}
