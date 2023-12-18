package game;

public class Potion {

  private Ingredient ing1;
  private Ingredient ing2;
  private String name;
  private String effect;
  String potionColor;

  public Potion(String potionColor, Ingredient ing1, Ingredient ing2, String name) {
    this.potionColor = potionColor;
    this.ing1 = ing1;
    this.ing2 = ing2;
    this.name = name;
  }

  public String getEffect() {
    return effect;
  }

  public String getPotionColor() {
    return potionColor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEffect(String effect) {
    this.effect = effect;
  }
}
