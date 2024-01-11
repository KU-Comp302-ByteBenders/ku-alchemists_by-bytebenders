package ui.interfaces;

import java.util.ArrayList;

import game.Board;
import game.Ingredient;
import game.Potion;
import game.State;
import game.Token;
import game.ArtifactCards.ArtifactCard;
import ui.BoardJFrame;

public interface BoardFrame {

    void removeMiniPotionImage(String img);
    void removeIngredientFromBoardByName(String ingredientName);
    void activateTransmuteIngredientFrame(ArrayList<Ingredient> displayedIngredients, Board mainBoard, State state);
    void controlRoundActions(Boolean endTurnFlag, State state);
    void updateTokensGoldLabel();
    void updateTokensReputationLabel();
    String findImagePathByIngredient(String string);
    void removeIngredient(String string);
    void addIngredient(Ingredient ingredient);
    void addMiniPotionImage(Potion potion);
    void createArtifactUseButton(ArtifactCard artifactCard, Token token);

    
}
