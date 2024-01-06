package ui;

import game.Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class PauseMenuJframe extends JFrame implements ChangeableVisibility {

  JFrame board;

  public PauseMenuJframe(JFrame board) {
    this.setSize(1280, 720);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setResizable(false);
    this.board = board;

    JLabel titleLabel = new JLabel("KU ALCHEMIST");
    titleLabel.setForeground(Color.RED);
    titleLabel.setBounds(50, 20, 200, 100);

    JLabel versionLabel = new JLabel("PAUSE MENU");
    versionLabel.setForeground(Color.BLUE);
    versionLabel.setBounds(600, 50, 200, 100);

    JButton helpButton = new JButton("HELP");
    helpButton.setBounds(350, 250, 150, 150);

    helpButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game.openHelpScreen();
        }
      }
    );

    JButton closePauseMenu = new JButton("Return the Game");
    closePauseMenu.setBounds(750, 250, 150, 150);

    closePauseMenu.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          closingMenu();
        }
      }
    );

    this.add(titleLabel);
    this.add(versionLabel);
    this.add(helpButton);
    this.add(closePauseMenu);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }

  public void closingMenu() {
    Game.closePauseMenu(this);
    Game.activateBoard((ChangeableVisibility) board);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
