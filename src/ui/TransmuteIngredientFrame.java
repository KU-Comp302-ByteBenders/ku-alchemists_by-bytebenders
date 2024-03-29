package ui;

import game.*;
import ui.interfaces.BoardFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class TransmuteIngredientFrame extends JFrame {

  private ArrayList<Ingredient> displayedIngredients;
  private Board mainBoard;
  private BoardFrame boardFrame;
  private State state;
  private Boolean endTurnFlag;

  public TransmuteIngredientFrame(ArrayList<Ingredient> displayedIngredients, Board mainBoard, BoardFrame boardFrame, State state, Token token) {
    this.state = state;
    this.displayedIngredients = new ArrayList<>(displayedIngredients);
    this.mainBoard = mainBoard;
    this.boardFrame = boardFrame;

    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel ingredientsPanel = new JPanel();

    // Create a JComboBox for ingredient selection
    JComboBox<String> ingredientComboBox = new JComboBox<>();

    // Add ingredients to the combo box
    for (Ingredient ingredient : displayedIngredients) {
      ingredientComboBox.addItem(ingredient.getName());
    }

    ingredientsPanel.add(ingredientComboBox);

    JButton transformButton = new JButton("Transform");

    transformButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Get the selected ingredient from the combo box
          String selectedIngredient = (String) ingredientComboBox.getSelectedItem();
          handleTransformButtonClick(selectedIngredient, token);
        }
      }
    );

    add(ingredientsPanel, BorderLayout.CENTER);
    add(transformButton, BorderLayout.SOUTH);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    setVisible(true);
  }

  private void handleTransformButtonClick(String selectedIngredient, Token token) {
    if (selectedIngredient != null) {
      String ingredientName = selectedIngredient;
      boardFrame.removeIngredientFromBoardByName(ingredientName);
      Game.controlRoundAction(boardFrame, state, true);
      Game game = Game.getInstance();
      token.addGold(1);
      boardFrame.updateTokensGoldLabel();
      if (!game.isOffline()) {
        game.publishAction("Action " + mainBoard.getTokens().indexOf(token) + " TransmuteIngredient " + ingredientName);
      }
      this.setVisible(false);
    } else {
      JOptionPane.showMessageDialog(
        this,
        "Please select an ingredient to transform.",
        "Warning",
        JOptionPane.WARNING_MESSAGE
      );
    }
  }
}
