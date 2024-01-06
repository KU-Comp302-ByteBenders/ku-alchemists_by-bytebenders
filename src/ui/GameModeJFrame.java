package ui;

import game.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class GameModeJFrame extends JFrame implements ChangeableVisibility {

  public GameModeJFrame() {
    this.setSize(1280, 720);
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

    this.setLayout(new GridLayout(4, 1));
    this.add(offlineButton);
    this.add(hostButton);
    this.add(joinButton);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;
    this.setLocation(x, y);
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
