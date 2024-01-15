package game;

import java.io.Serializable;

public class Ingredient implements Serializable{
  private static final long serialVersionUID = 712L;
  private String name;
  private int id;
  private AlchemyMarker alchemyMarker;
  private String imagePath;

  public Ingredient(String name, int id, AlchemyMarker alchemyMarker, String imagePath) {
    this.name = name;
    this.id = id;
    this.alchemyMarker = alchemyMarker;
    this.imagePath = imagePath;
  }

  public String getName() {
    return name;
  }

  public int getID() {
    return id;
  }

  public AlchemyMarker alchemyMarker() {
    return alchemyMarker;
  }

  public String getImagePath() {
    return imagePath;
  }

  public AlchemyMarker getAlchemyMarker() {
    return alchemyMarker;
  }
}
