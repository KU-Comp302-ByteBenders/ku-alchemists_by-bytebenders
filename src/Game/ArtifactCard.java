package game;

public class ArtifactCard {
    private String name;
    private Token owner = null; // ArtifactCard has no owner by default

    public ArtifactCard(String name) {
        this.name = name;
    }

}
