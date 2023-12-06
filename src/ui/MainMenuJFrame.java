package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.*;

public class MainMenuJFrame extends JFrame {

    public MainMenuJFrame() {
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JLabel titleLabel = new JLabel("KU ALCHEMIST");
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(550, 150, 150, 30);

        JLabel versionLabel = new JLabel("by ByteBenders");
        versionLabel.setForeground(Color.BLACK);
        versionLabel.setBounds(550, 180, 150, 30);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(550, 220, 150, 30);

        // Add ActionListener to the "NEW GAME" button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = Game.getInstance();
                game.openLogin(MainMenuJFrame.this);
            }
        });

        JButton helpButton = new JButton("HELP");
        helpButton.setBounds(550, 260, 150, 30);

        // Add ActionListener to the "HELP" button
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.openHelpScreen();
            }
        });

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(550, 300, 150, 30);

        this.add(titleLabel);
        this.add(versionLabel);
        this.add(newGameButton);
        this.add(helpButton);
        this.add(exitButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        // Set the frame location
        this.setLocation(x, y);

        // Make the frame visible
        this.setVisible(true);
    }
}
