package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PublishTheoryJFrame extends JFrame {
    private JButton[] ingredientButtons = new JButton[8];
    private JButton[] alchemyButtons = new JButton[8];
    private JButton confirmButton = new JButton("Confirm");

    public PublishTheoryJFrame() {
        this.setSize(1280, 720);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(false);

        JPanel ingredientPanel = new JPanel(new GridLayout(1, 8));
        JPanel alchemyPanel = new JPanel(new GridLayout(1, 8));
        JPanel confirmPanel = new JPanel();

        ButtonGroup ingredientGroup = new ButtonGroup();
        ButtonGroup alchemyGroup = new ButtonGroup();

        for (int i = 0; i < 8; i++) {
            ingredientButtons[i] = new JButton();
            ingredientButtons[i].setIcon(new ImageIcon("src/ui/utils/ingredient_" + (i + 1) + ".jpg"));
            ingredientGroup.add(ingredientButtons[i]);
            ingredientPanel.add(ingredientButtons[i]);

            alchemyButtons[i] = new JButton("Alchemy Marker" + (i + 1));
            //alchemyButtons[i].setIcon(new ImageIcon("path_to_alchemy_image_" + (i + 1)));
            alchemyGroup.add(alchemyButtons[i]);
            alchemyPanel.add(alchemyButtons[i]);
        }

        confirmPanel.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle confirmation here
            }
        });

        this.add(ingredientPanel);
        this.add(alchemyPanel);
        this.add(confirmPanel);
        this.pack();
        this.setVisible(true);
    }
}
