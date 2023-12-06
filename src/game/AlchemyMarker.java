package game;

import java.util.ArrayList;

public class AlchemyMarker {

    private Aspect aspect1;
    private Aspect aspect2;
    private Aspect aspect3;
    private ArrayList<Aspect> aspectList = new ArrayList<Aspect>();

    private String imagePath;

    public AlchemyMarker(Aspect aspect1, Aspect aspect2, Aspect aspect3, String imagePath) {
        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.aspect3 = aspect3;
        this.aspectList.add(aspect1);
        this.aspectList.add(aspect2);
        this.aspectList.add(aspect3);

        this.imagePath = imagePath;
    }

    public boolean isOnIngredient(Ingredient ingredient) {

        return false;
    }
    
}

