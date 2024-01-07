package ui;

import javax.swing.*;

import game.ArtifactCards.ArtifactCard;

import java.awt.event.*;

public class WisdomIdolJFrame extends JFrame {

  public WisdomIdolJFrame(ArtifactCard artifactCard) {

    // Create the label and buttons
    JLabel questionLabel = new JLabel("Do you want to use your Magic Mortar artifact card?");
    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");

    // Add empty action listeners to the buttons
    yesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        artifactCard.setToBeAppliedFlag(true);
        dispose();
      }
    });

    noButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        artifactCard.setToBeAppliedFlag(false);
        dispose();
      }
    });

    // Add the label and buttons to the frame
    this.add(questionLabel);
    this.add(yesButton);
    this.add(noButton);

    // Set the layout manager to arrange the components in a vertical line
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    this.pack();
    this.setVisible(true);
  }
}
