package ui;

import game.*;
import ui.interfaces.BoardFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PublishTheoryJFrame extends JFrame {

  private JToggleButton[] ingredientButtons = new JToggleButton[8];
  private JToggleButton[] alchemyButtons = new JToggleButton[8];
  private JButton confirmButton = new JButton("Confirm");

  private Ingredient selectedIngredient = null;
  private AlchemyMarker selectedAlchemyMarker = null;
  private int selectedIngredientIndex = -1;
  private int selectedMarkerIndex = -1;

  public PublishTheoryJFrame(BoardFrame boardFrame, Board board, State state, Token token1) {
    this.setSize(1280, 720);
    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.setResizable(false);

    JPanel ingredientPanel = new JPanel(new GridLayout(1, 8));
    JPanel alchemyPanel = new JPanel(new GridLayout(1, 8));
    JPanel confirmPanel = new JPanel();
    JPanel bottomPanel = new JPanel(new GridLayout(2, 1)); // Create a grid to display selected ingredient and alchemy marker.

    // Create fields to display selected ingredient on bottom left.
    JTextField selectedIngredientField = new JTextField();
    selectedIngredientField.setEditable(false); // Make the text field non-editable
    bottomPanel.add(selectedIngredientField, BorderLayout.WEST);

    // Create fields to display selected alchemy marker on bottom left.
    JTextField selectedMarkerField = new JTextField();
    selectedMarkerField.setEditable(false); // Make the text field non-editable
    bottomPanel.add(selectedMarkerField, BorderLayout.WEST);

    ButtonGroup ingredientGroup = new ButtonGroup();
    ButtonGroup alchemyGroup = new ButtonGroup();

    for (int i = 0; i < 8; i++) {
      // Get the path of the ingredient image and add it to the button
      String ingredientImagePath = board.getStaticIngredients().get(i).getImagePath();
      ingredientButtons[i] = new JToggleButton();
      ingredientButtons[i].setIcon(new ImageIcon(ingredientImagePath));
      ingredientGroup.add(ingredientButtons[i]);
      ingredientPanel.add(ingredientButtons[i]);

      final int index = i; // Create a final copy of i for use in the listener
      ingredientButtons[i].addItemListener(
          new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
              if (e.getStateChange() == ItemEvent.SELECTED) {
                // Get the selected ingredient from board
                selectedIngredient = board.getStaticIngredients().get(index);
                selectedIngredientIndex = index;
                selectedIngredientField.setText("Selected Ingredient: " + selectedIngredient.getName());
              }
            }
          }
        );

      // Get the path of the alchemy marker image and add it to the button
      String alchemyMarkerImagePath = board.getStaticAlchemyMarkers().get(i).getImagePath();
      alchemyButtons[i] = new JToggleButton();
      alchemyButtons[i].setIcon(new ImageIcon(alchemyMarkerImagePath));
      alchemyButtons[i].addItemListener(
          new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
              if (e.getStateChange() == ItemEvent.SELECTED) {
                // Get the selected marker from board
                selectedAlchemyMarker = board.getStaticAlchemyMarkers().get(index);
                selectedMarkerIndex = index;
                selectedMarkerField.setText("Selected Marker: " + (index + 1));
              }
            }
          }
        );

      alchemyGroup.add(alchemyButtons[i]);
      alchemyPanel.add(alchemyButtons[i]);
    }

    confirmPanel.add(confirmButton);
    confirmButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          // Publish the theory
          try {
            token1.publishTheory(board, selectedIngredient, selectedAlchemyMarker);
          } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
            return;
          }
          boardFrame.updateTokensGoldLabel();
          boardFrame.updateTokensReputationLabel();
          // Close the window
          dispose();
          Game game = Game.getInstance();
          game.publishAction("Action " + board.getTokens().indexOf(token1)  + " PublishTheory " + selectedIngredientIndex + " " + selectedMarkerIndex);
          Game.controlRoundAction(boardFrame, state, true);
        }
      }
    );

    this.add(ingredientPanel);
    this.add(alchemyPanel);
    this.add(confirmPanel);
    this.add(bottomPanel);
    this.pack();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }
}
