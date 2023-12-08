package ui;

import game.AlchemyMarker;
import game.Board;
import game.Ingredient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PublishTheoryJFrame extends JFrame {

  private JToggleButton[] ingredientButtons = new JToggleButton[8];
  private JToggleButton[] alchemyButtons = new JToggleButton[8];
  private JButton confirmButton = new JButton("Confirm");

  private Board board;
  private Ingredient selectedIngredient = null;
  private AlchemyMarker selectedAlchemyMarker = null;

  public PublishTheoryJFrame(Board board) {
    this.setSize(1280, 720);
    this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.setResizable(false);

    this.board = board;

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

          // TODO: Add turn logic here
          try {
            board.publishTheory(selectedIngredient, selectedAlchemyMarker, board.getTokens().get(0));
          } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
            return;
          }
          // Close the window
          dispose();
        }
      }
    );

    this.add(ingredientPanel);
    this.add(alchemyPanel);
    this.add(confirmPanel);
    this.add(bottomPanel);
    this.pack();
    this.setVisible(true);
  }
}
