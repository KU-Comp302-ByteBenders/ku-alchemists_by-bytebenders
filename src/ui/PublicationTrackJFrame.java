package ui;

import game.Board;
import game.Ingredient;
import game.Theory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PublicationTrackJFrame extends JFrame {

  public PublicationTrackJFrame(Board board) {
    this.setSize(1280, 720);
    this.setResizable(false);
    JPanel gridPanel = new JPanel(new GridLayout(1, 8));

    for (int i = 0; i < 8; i++) {
      JPanel cellPanel = new JPanel(new BorderLayout());

      Ingredient ingredient = board.getStaticIngredients().get(i);
      String imagePath = ingredient.getImagePath();
      String theoryOwner = "No theory";
      String theoryMarkerImage = null;

      for (Theory theory : board.getTheories()) {
        if (theory.isAboutIngredient(ingredient)) {
          theoryOwner = theory.getTheoryOwner().getUsername();
          theoryMarkerImage = theory.getAlchemyMarker().getPath();
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
        cellPanel.add(markerLabel, BorderLayout.SOUTH);
      }

      gridPanel.add(cellPanel);
    }

    this.add(gridPanel);
    this.pack();
    this.setVisible(true);
  }
}
