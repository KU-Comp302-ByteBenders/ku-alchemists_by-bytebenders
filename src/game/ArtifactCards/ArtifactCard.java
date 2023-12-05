package game.ArtifactCards;

import game.Token;
//We used the strategy pattern while creating the artifact cards. We created an effect interface and 
//made the different artifact card classes have different implementations of the applyEffect method of 
//the effect class.
public class ArtifactCard {
    private String name;
    private Token owner = null; // ArtifactCard has no owner by default

    public ArtifactCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

