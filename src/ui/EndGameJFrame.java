package ui;

import game.EndGamer;
import game.Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class EndGameJFrame extends JFrame implements ChangeableVisibility {

  JPanel winnerPanel;
  JPanel resultsPanel;
  JPanel controlPanel;

  public EndGameJFrame(EndGamer endGamer) {
    this.setLayout(new BorderLayout());
    this.setTitle("End Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1000, 1000);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    winnerPanel = new JPanel();
    winnerPanel.setPreferredSize(new Dimension(1000, 300));

    resultsPanel = new JPanel();
    resultsPanel.setPreferredSize(new Dimension(1000, 700));

    controlPanel = new JPanel();
    controlPanel.setPreferredSize(new Dimension(1000, 100));

    feedWinnerPanel(endGamer);
    feedResultsPanel(endGamer);
    feedControlPanel(endGamer);

    this.add(winnerPanel, BorderLayout.NORTH);
    this.add(resultsPanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public void feedWinnerPanel(EndGamer endGamer) {
    JLabel winnerLabel = new JLabel();
    if (endGamer.getWinner() == -1) {
      String buff = "Tie between: ";
      for (int i = 0; i < endGamer.getTiePlayersTies().size(); i++) {
        buff += endGamer.getTokens().get(endGamer.getTiePlayersTies().get(i)).getUsername() + " ";
      }
      winnerLabel.setText(buff);
    } else {
      winnerLabel.setText("The winner is: " + endGamer.getTokens().get(endGamer.getWinner()).getUsername());
    }
    winnerPanel.add(winnerLabel);
  }

  public void feedResultsPanel(EndGamer endGamer) {
    JPanel scoresPanel = new JPanel(new GridLayout(endGamer.getTokens().size(), 1));
    for (int i = 0; i < endGamer.getTokens().size(); i++) {
      JLabel scoreLabel = new JLabel();
      scoreLabel.setText(
        endGamer.getTokens().get(i).getUsername() +
        "-> score: " +
        endGamer.getTokenScores()[i] +
        ", reputation: " +
        endGamer.getTokens().get(i).getReputation() +
        ", number of artifact cards: " +
        endGamer.getTokens().get(i).getArtifactCards().size() +
        ", gold balance: " +
        endGamer.getTokens().get(i).getGold()
      );
      scoresPanel.add(scoreLabel);
    }
    resultsPanel.add(scoresPanel);
  }

  public void feedControlPanel(EndGamer endGamer) {
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      }
    );

    JButton playAgainButton = new JButton("Play Again");
    playAgainButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
          game.restartGame();
          game.openGameMode(EndGameJFrame.this);
        }
      }
    );
    controlPanel.add(playAgainButton);
    controlPanel.add(exitButton);
  }

  @Override
  public void changeVisible(Boolean visible) {
    this.setVisible(visible);
  }
}
