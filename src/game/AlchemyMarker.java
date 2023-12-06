package game;

public class AlchemyMarker {

    private Aspect aspect1;
    private Aspect aspect2;
    private Aspect aspect3;

    private String imagePath;

    public AlchemyMarker(Aspect aspect1, Aspect aspect2, Aspect aspect3, String imagePath) {
        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.aspect3 = aspect3;

        this.imagePath = imagePath;
    }

    public boolean isOnIngredient(Ingredient ingredient) {

        return false;
    }

    public String getPath() {
        return imagePath;
    }

    public Aspect getAspect1() {
        return aspect1;
    }

    public Aspect getAspect2() {
        return aspect2;
    }

    public Aspect getAspect3() {
        return aspect3;
    }
}
