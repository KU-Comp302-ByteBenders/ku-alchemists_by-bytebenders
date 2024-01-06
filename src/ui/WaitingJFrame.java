package ui;

import game.Game;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ui.interfaces.ChangeableVisibility;

public class WaitingJFrame extends JFrame implements ChangeableVisibility {

  public WaitingJFrame(String ip, String username, String avatar) {
    super("Waiting");
    this.setSize(1280, 720);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setResizable(false);

    JLabel titleLabel = new JLabel("Waiting for other players...");
    JLabel ipAddress = new JLabel("Your ip address: " + ip);
    JLabel usernameLabel = new JLabel("Your username: " + username);
    JButton startButton = new JButton("Start the game");

    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
        }
      }
    );

    this.add(titleLabel);
    this.add(ipAddress);
    this.add(usernameLabel);
    this.add(startButton);
    this.setLayout(new GridLayout(9, 1));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;
    this.setLocation(x, y);
    this.setVisible(true);
  }

  public void addPlayer(String username, String avatar) {
    this.add(new JLabel(username));
    System.out.println("girdi mi????");
    this.setVisible(false);
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
