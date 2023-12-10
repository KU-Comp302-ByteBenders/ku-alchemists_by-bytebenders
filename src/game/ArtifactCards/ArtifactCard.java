package game.ArtifactCards;


//We used the strategy pattern while creating the artifact cards. We created an effect interface and
//made the different artifact card classes have different implementations of the applyEffect method of
//the effect class.
public abstract class ArtifactCard {

  private String name;
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
