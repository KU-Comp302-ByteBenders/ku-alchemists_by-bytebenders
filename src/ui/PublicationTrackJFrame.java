package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Board;
import game.Ingredient;
import game.Theory;

public class PublicationTrackJFrame extends JFrame{
    public PublicationTrackJFrame(Board board) {
        this.setSize(1280, 720);
        this.setResizable(false);
        JPanel gridPanel = new JPanel(new GridLayout(1, 8));

        for (int i = 0; i < 8; i++) {
            JPanel cellPanel = new JPanel(new BorderLayout());

            Ingredient ingredient = board.getStaticIngredients().get(i);
            String imagePath = ingredient.getImagePath();
            String theoryOwner = "No one";
            String theoryMarkerImage = "None";
            
            for (Theory theory : board.getTheories()) {
                if (theory.isAboutIngredient(ingredient)) {
                    theoryOwner = theory.getTheoryOwner().getUsername();
                    //theoryMarkerImage = theory.getAlchemyMarker().getPath();
                }
            }

            ImageIcon imageIcon = new ImageIcon(imagePath);
            JLabel imageLabel = new JLabel(imageIcon);
            cellPanel.add(imageLabel, BorderLayout.CENTER);

            JLabel textLabel = new JLabel("Text " + (i + 1));
            cellPanel.add(textLabel, BorderLayout.SOUTH);

            gridPanel.add(cellPanel);
        }

        this.add(gridPanel);
        this.pack();
        this.setVisible(true);
    }
}
