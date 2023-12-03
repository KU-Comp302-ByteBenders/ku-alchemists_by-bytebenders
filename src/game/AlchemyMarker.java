package game;

public class AlchemyMarker {

    private Aspect aspect1;
    private Aspect aspect2;
    private Aspect aspect3;


    public AlchemyMarker(Aspect aspect1, Aspect aspect2, Aspect aspect3) {
        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.aspect3 = aspect3;
    }

    public boolean isOnIngredient(Ingredient ingredient) {

        return false;
    }
    
}
