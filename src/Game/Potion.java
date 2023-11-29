public class Potion {
    
  private int pointVal;
  private Ingredient ing1;
  private Ingredient ing2;
  private String name;
  private Effect effect;
  
  public Potion(Integer pointVal, Ingredient ing1, Ingredient ing2, String name, Effect effect) {
    this.pointVal = pointVal;
    this.ing1 = ing1;
    this.ing2 = ing2;
    this.name = name;
    this.effect = effect;
  }

  
}
