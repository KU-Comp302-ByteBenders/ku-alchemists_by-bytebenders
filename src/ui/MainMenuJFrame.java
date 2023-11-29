package ui;

import java.awt.Color;
import javax.swing.*;

public class MainMenuJFrame extends JFrame{
    public MainMenuJFrame() {
        JFrame frame = new JFrame("KU ALCHEMIST");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("KU ALCHEMIST");
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(550, 150, 150, 30);

        JLabel versionLabel = new JLabel("by ByteBenders");
        versionLabel.setForeground(Color.BLACK);
        versionLabel.setBounds(550, 180, 150, 30);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(550, 220, 150, 30);

        JButton helpButton = new JButton("HELP");
        helpButton.setBounds(550, 260, 150, 30);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(550, 300, 150, 30);

        frame.add(titleLabel);
        frame.add(versionLabel);
        frame.add(newGameButton);
        frame.add(helpButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }
}
