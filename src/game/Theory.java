package game;

import java.io.Serializable;

public class Theory implements Serializable {
  private static final long serialVersionUID = 11L;

  private String theoryName;
  private Ingredient theoryIngredient;
  private AlchemyMarker alchemyMarker;
  private Token theoryOwner;

  public Theory(Ingredient ingredient, AlchemyMarker alchemyMarker, Token token) {
    this.theoryIngredient = ingredient;
    this.alchemyMarker = alchemyMarker;
    this.theoryOwner = token;
    this.theoryName = String.format("Theory:%s", ingredient.getName());
  }

  // Returns true if the theory is about the given ingredient
  public boolean isAboutIngredient(Ingredient ingredient) {
    return this.getTheoryIngredient().equals(ingredient);
  }

  // Returns true if the theory is marked with that Alchemical Marker
  public boolean hasAlchemyMarker(AlchemyMarker alchemyMarker) {
    return this.getAlchemyMarker().equals(alchemyMarker);
  }

  // Returns true if the theory belongs to the token (Player).
  public boolean belongsToToken(Token token) {
    return this.getTheoryOwner().equals(token);
  }

  // Returns true if alchemyMarker matches the Ingredient of the Theory's alchemyMarker
  public boolean debunkSuccess(Aspect aspect) {
    if (aspect.getColor() == "Red") {
      Aspect theoryAspect = this.getAlchemyMarker().getAspect1();
      System.out.println(aspect.getSign());
      System.out.println(aspect.getSize());
      System.out.println(aspect.getColor());
      System.out.println(theoryAspect.getSign());
      System.out.println(theoryAspect.getSize());
      System.out.println(theoryAspect.getColor());
      return !(theoryAspect.getSign().equals(aspect.getSign()) && theoryAspect.getSize().equals(aspect.getSize()));
    }
    else if (aspect.getColor() == "Blue") {
      Aspect theoryAspect = this.getAlchemyMarker().getAspect2();
      return !(theoryAspect.getSign().equals(aspect.getSign()) && theoryAspect.getSize().equals(aspect.getSize()));
    }
    else { // Yellow 
      Aspect theoryAspect = this.getAlchemyMarker().getAspect3();
      return !(theoryAspect.getSign().equals(aspect.getSign()) && theoryAspect.getSize().equals(aspect.getSize()));
    }
  }

  public Token getTheoryOwner() {
    return theoryOwner;
  }

  public String getTheoryName() {
    return theoryName;
  }

  public Ingredient getTheoryIngredient() {
    return theoryIngredient;
  }

  public AlchemyMarker getAlchemyMarker() {
    return alchemyMarker;
  }
}
