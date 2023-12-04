package game;

public class Theory {
    private String theoryName;
    private Ingredient theoryIngredient;
    private AlchemyMarker alchemyMarker;
    private Token theoryOwner;

    public Theory(Ingredient ingredient, AlchemyMarker alchemyMarker, Token token) {
        this.theoryIngredient = ingredient;
        this.alchemyMarker = alchemyMarker;
        this.theoryOwner = token;
        this.theoryName = String.format("Theory on %s", ingredient.getName());
    }

    // Returns true if the theory is about the given ingredient
    public boolean isAboutIngredient(Ingredient ingredient) {
        return this.getTheoryIngredient().equals(ingredient);
    }

    // Returns true if the theory is marked with that Alchemical Marker
    public boolean hasAlchemyMarker(AlchemyMarker alchemyMarker) {
        return this.getAlchemyMarker().equals(alchemyMarker);
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
