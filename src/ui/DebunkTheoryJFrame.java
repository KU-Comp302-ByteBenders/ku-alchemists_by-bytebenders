package ui;

import game.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ui.interfaces.ConfirmationListener;

public class DebunkTheoryJFrame extends JFrame {

  public DebunkTheoryJFrame(BoardJFrame boardJFrame, Board board, State state, Token token1) {
    this.setSize(1280, 720);
    this.setResizable(false);
    JPanel gridPanel = new JPanel(new GridLayout(2, 4));

    for (int i = 0; i < 8; i++) {
      JPanel cellPanel = new JPanel(new BorderLayout());

      Theory currentTheory = null;

      Ingredient ingredient = board.getStaticIngredients().get(i);
      String imagePath = ingredient.getImagePath();
      String theoryOwner = "No theory";
      String theoryMarkerImage = null;

      for (Theory theory : board.getTheories()) {
        if (theory.isAboutIngredient(ingredient)) {
          currentTheory = theory;
          theoryOwner = currentTheory.getTheoryOwner().getUsername();
          theoryMarkerImage = currentTheory.getAlchemyMarker().getPath();
        }
      }

      JLabel textLabel = new JLabel(theoryOwner);
      cellPanel.add(textLabel, BorderLayout.NORTH);

      ImageIcon ingredientIcon = new ImageIcon(imagePath);
      JLabel ingredientLabel = new JLabel(ingredientIcon);
      cellPanel.add(ingredientLabel, BorderLayout.CENTER);

      if (theoryMarkerImage != null) {
        ImageIcon markerIcon = new ImageIcon(theoryMarkerImage);
        JLabel markerLabel = new JLabel(markerIcon);

        String[] options = { "Select an Aspect", "Red Aspect", "Blue Aspect", "Yellow Aspect" };
        JComboBox<String> debunkComboBox = new JComboBox<>(options);
        final Theory finalCurrentTheory = currentTheory;
        debunkComboBox.addItemListener(
          new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
              if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() != "Select an Aspect") {
                ConfirmationListener listener = new ConfirmationListener() {
                  @Override
                  public void onConfirmed() throws Exception {
                    int aspectIndex = debunkComboBox.getSelectedIndex() - 1;
                    Aspect selectedAspect = ingredient.getAlchemyMarker().getAspectList().get(aspectIndex);
                    boolean success = token1.debunkTheory(board, finalCurrentTheory, selectedAspect);
                    boardJFrame.updateTokensGoldLabel();
                    boardJFrame.updateTokensReputationLabel();
                    ImageIcon imageIcon = new ImageIcon(ingredient.getImagePath());
                    JLabel imageLabel = new JLabel(imageIcon);

                    if (success) {
                      JOptionPane.showMessageDialog(null, imageLabel, "Success!!!", JOptionPane.INFORMATION_MESSAGE);
                      Game.controlRoundAction(boardJFrame, state, true);
                    } else {
                      JOptionPane.showMessageDialog(null, imageLabel, "Failure!!!", JOptionPane.INFORMATION_MESSAGE);
                      Game.controlRoundAction(boardJFrame, state, true);
                    }
                  }
                };
                ConfirmJFrame confirmJFrame = new ConfirmJFrame((String) e.getItem(), ingredient.getName(), listener);
              }
            }
          }
        );
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(markerLabel, BorderLayout.NORTH);
        southPanel.add(debunkComboBox, BorderLayout.SOUTH);

        cellPanel.add(southPanel, BorderLayout.SOUTH);
      }

      gridPanel.add(cellPanel);
    }

    this.add(gridPanel);
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
