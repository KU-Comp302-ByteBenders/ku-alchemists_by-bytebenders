package Game;

public abstract class ArtifactCard {
    private String name;
    private Token owner = null; // ArtifactCard has no owner by default

    public ArtifactCard(String name) {
        this.name = name;
    }

    // Our design had this as an Abstract method
    // But every ArtifactCard will be implemented as a separate interface.
    public abstract void useEffect();
}
