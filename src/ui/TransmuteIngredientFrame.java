package ui;

import game.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TransmuteIngredientFrame extends JFrame {

    private ArrayList<Ingredient> displayedIngredients;
    private Board mainBoard;
    private BoardJFrame boardFrame;

    public TransmuteIngredientFrame(ArrayList<Ingredient> displayedIngredients, Board mainBoard, BoardJFrame boardFrame) {
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

        transformButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected ingredient from the combo box
                String selectedIngredient = (String) ingredientComboBox.getSelectedItem();
                handleTransformButtonClick(selectedIngredient);
            }
        });

        add(ingredientsPanel, BorderLayout.CENTER);
        add(transformButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleTransformButtonClick(String selectedIngredient) {
        if (selectedIngredient != null) {
            String ingredientName = selectedIngredient;
            boardFrame.removeIngredientFromBoardByName(ingredientName);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an ingredient to transform.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}