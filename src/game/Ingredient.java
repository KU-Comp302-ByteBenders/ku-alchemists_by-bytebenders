package game;

public class Ingredient {
  
  private String name;
  private int id;
  private AlchemyMarker alchemyMarker;
  
  public Ingredient(String name, int id, AlchemyMarker alchemyMarker) {
    this.name = name;
    this.id = id;
    this.alchemyMarker = alchemyMarker;
  }

  public String getName(){
    return name;
  }
  public int getID(){
    return id;
  }
  public AlchemyMarker getAlchemyMarker(){
    return alchemyMarker;
  }
  
}
