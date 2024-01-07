package ui;

import game.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class MainMenuJFrame extends JFrame implements ChangeableVisibility {

  public MainMenuJFrame() {
    super("KU Alchemists");
    this.setSize(960, 720);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setResizable(false);

    // Load the image
    ImageIcon imageIcon = new ImageIcon("src/ui/utils/google_chateau.png");

    // Scale the image to fit the frame
    Image image = imageIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
    imageIcon = new ImageIcon(image);

    // Add the background image
    JLabel background = new JLabel(imageIcon);
    background.setBounds(0, 0, this.getWidth(), this.getHeight());
    this.add(background);

    JLabel titleLabel = new JLabel("KU ALCHEMISTS");
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
    titleLabel.setBounds(470, 50, 500, 100);

    JLabel versionLabel = new JLabel("by ByteBenders");
    versionLabel.setForeground(Color.WHITE);
    versionLabel.setFont(new Font("Arial", Font.BOLD, 20));
    versionLabel.setBounds(600, 135, 200, 30);

    JButton newGameButton = new JButton("NEW GAME");
    newGameButton.setBounds(580, 260, 200, 50);

    // Add ActionListener to the "NEW GAME" button
    newGameButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
          game.openGameMode(MainMenuJFrame.this);
        }
      }
    );

    JButton helpButton = new JButton("HELP");
    helpButton.setBounds(580, 320, 200, 50);

    // Add ActionListener to the "HELP" button
    helpButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.openHelpScreen();
        }
      }
    );

    JButton exitButton = new JButton("EXIT");
    exitButton.setBounds(580, 380, 200, 50);

    exitButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      }
    );
      /* 
    this.add(titleLabel);
    this.add(versionLabel);
    this.add(newGameButton);
    this.add(helpButton);
    this.add(exitButton);
      */
      
    background.add(titleLabel);
    background.add(versionLabel);
    background.add(newGameButton);
    background.add(helpButton);
    background.add(exitButton);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}


