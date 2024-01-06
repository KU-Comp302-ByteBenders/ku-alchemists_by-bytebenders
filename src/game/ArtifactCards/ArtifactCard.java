package game.ArtifactCards;

import game.Board;
import game.Token;

//We used the strategy pattern while creating the artifact cards. We created an effect interface and
//made the different artifact card classes have different implementations of the applyEffect method of
//the effect class.
public class ArtifactCard implements EffectStrategy {

  private String name;
  private int goldPrice;
  private EffectStrategy effect;

  public ArtifactCard(String name, int goldPrice, EffectStrategy effect) {
    this.name = name;
    this.goldPrice = goldPrice;
    this.effect = effect;
  }

  public String getName() {
    return name;
  }

  public int getGoldPrice() {
    return goldPrice;
  }

  public void setEffect(EffectStrategy effect) {
    this.effect = effect;
  }

  @Override
  public void applyEffect(Token token, Board board, String ing) {
    effect.applyEffect(token, board, ing);
  }

  
}
