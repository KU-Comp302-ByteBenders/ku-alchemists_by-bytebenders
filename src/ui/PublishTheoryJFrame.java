package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PublishTheoryJFrame extends JFrame {
    private JButton[] ingredientButtons = new JButton[8];
    private JButton[] alchemyButtons = new JButton[8];
    private JButton confirmButton = new JButton("Confirm");

    public PublishTheoryJFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 8));
        ButtonGroup ingredientGroup = new ButtonGroup();
        ButtonGroup alchemyGroup = new ButtonGroup();

        for (int i = 0; i < 8; i++) {
            ingredientButtons[i] = new JButton("Ingredient " + (i + 1));
            ingredientGroup.add(ingredientButtons[i]);
            panel.add(ingredientButtons[i]);

            alchemyButtons[i] = new JButton("Alchemy " + (i + 1));
            alchemyGroup.add(alchemyButtons[i]);
            panel.add(alchemyButtons[i]);
        }

        panel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle confirmation here
            }
        });

        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}
