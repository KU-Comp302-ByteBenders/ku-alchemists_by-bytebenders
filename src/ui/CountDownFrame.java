package ui;

import game.Board;
import game.Game;
import game.Token;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class CountDownFrame extends JFrame implements ChangeableVisibility {

  private JLabel countdownLabel;

  public CountDownFrame(Token token, Board board) {
    // Set up JFrame
    setTitle("Countdown Frame");
    setSize(300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Create a JLabel to display the countdown
    countdownLabel = new JLabel("3", SwingConstants.CENTER);
    countdownLabel.setFont(countdownLabel.getFont().deriveFont(48.0f));
    add(countdownLabel);

    // Create a Timer to update the countdown
    Timer timer = new Timer(
      1000,
      new ActionListener() {
        private int countdown = 3;

        @Override
        public void actionPerformed(ActionEvent e) {
          countdownLabel.setText(Integer.toString(countdown));
          countdown--;

          if (countdown < 0) {
            Game game = Game.getInstance();
            game.openOnlineBoard(token, board, CountDownFrame.this);
            ((Timer) e.getSource()).stop();
          }
        }
      }
    );

    // Start the timer
    timer.start();
    this.setVisible(true);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
