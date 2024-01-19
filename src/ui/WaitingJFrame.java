package ui;

import game.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.interfaces.ChangeableVisibility;

public class WaitingJFrame extends JFrame implements ChangeableVisibility {

  JPanel usernamesPanel;
  JPanel startButtonPanel;
  JButton startButton;

  public WaitingJFrame(String ip, String username, String avatar) {
    super("Waiting");
    this.setSize(1280, 720);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setResizable(false);

    JLabel titleLabel = new JLabel("Waiting for other players...");
    JLabel ipAddress = new JLabel("Your ip address: " + ip);
    JLabel usernameLabel = new JLabel("Your username: " + username);
    startButton = new JButton("Start the game");

    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
          game.startGame(WaitingJFrame.this);
        }
      }
    );

    startButtonPanel = new JPanel(new FlowLayout());
    startButtonPanel.add(startButton);

    usernamesPanel = new JPanel(new GridLayout(8, 1));

    usernamesPanel.add(titleLabel);
    usernamesPanel.add(ipAddress);
    usernamesPanel.add(usernameLabel);
    startButtonPanel.add(startButton);
    this.add(usernamesPanel, BorderLayout.NORTH);
    this.add(startButtonPanel, BorderLayout.SOUTH);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;
    this.setLocation(x, y);
    this.setVisible(true);
  }

  public void addPlayer(String username, String avatar) {
    usernamesPanel.add(new JLabel("joined user: " + username));
    startButtonPanel.add(startButton);
    this.setVisible(false);
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
