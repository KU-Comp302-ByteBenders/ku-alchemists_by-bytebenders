package ui;

import game.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class GameModeJFrame extends JFrame implements ChangeableVisibility {

    public GameModeJFrame() {
        // Set the layout manager to null for absolute positioning
        this.setLayout(null);

        this.setSize(1024, 1024);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JButton offlineButton = new JButton("Start an offline game");
        JButton hostButton = new JButton("Host a game");
        JButton joinButton = new JButton("Join a game");

        offlineButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game game = Game.getInstance();
                        game.openLogin(GameModeJFrame.this);
                    }
                }
        );

        hostButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game game = Game.getInstance();
                        game.openLoginSingle(GameModeJFrame.this, false);
                    }
                }
        );

        joinButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Game game = Game.getInstance();
                        game.openLoginSingle(GameModeJFrame.this, true);
                    }
                }
        );

        // Set the position and size of the components
        offlineButton.setBounds(400, 200, 200, 40);
        hostButton.setBounds(400, 300, 200, 40);
        joinButton.setBounds(400, 400, 200, 40);

        this.add(offlineButton);
        this.add(hostButton);
        this.add(joinButton);

        // Load and set the background image
        setBackgroundImage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setVisible(true);
    }

    private void setBackgroundImage() {
        // Load the image
        ImageIcon imageIcon = new ImageIcon("src/ui/utils/background.png");

        // Scale the image to fit the frame
        Image image = imageIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);

        // Add the background image
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(background);
    }

    @Override
    public void changeVisible(Boolean visible) {
        this.setVisible(visible);
    }
}
