package game;

import java.io.Serializable;

public class Aspect implements Serializable {

  private String color;
  private String size;
  private String sign;
  private String imagePath;
  private static final long serialVersionUID = 3L;

  public Aspect(String color, String size, String sign, String imagePath) {
    this.color = color;
    this.size = size;
    this.sign = sign;
    this.imagePath = imagePath;
  }

  public String getColor() {
    return color;
  }

  public String getSize() {
    return size;
  }

  public String getSign() {
    return sign;
  }

  public String getImagePath() {
    return imagePath;
  }
}
