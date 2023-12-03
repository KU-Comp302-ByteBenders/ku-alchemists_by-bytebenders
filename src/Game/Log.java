package game;

public class Log {
    private Token[] tokens;
    private String[] texts;

    private static Log instance = null;

    public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}

    // no constructor as this is class is a Singleton

    /*
    private void updateText(String newText) {}

    private void displayText() {}

    private String logStart() {}

    private String logForageForIngredient() {}

    private String logTransmuteIngredient() {}

    private String logBuyArtifactCard(ArtifactCard artifactCard) {}

    private String logUseArtifactCard(ArtifactCard artifactCard) {}

    private String logMakeExperiment(Ingredient ing1, Ingredient ing2, String testSubject, String result) {}

    private String logSellPotion(Potion potion, int price) {}

    private String logPublishTheory(Ingredient ingredient, AlchemyMarker alchemyMarker) {}

    private String logDebunkTheory(Ingredient ingredient, AlchemyMarker alchemyMarker, Token publisher, boolean success) {} */
}
