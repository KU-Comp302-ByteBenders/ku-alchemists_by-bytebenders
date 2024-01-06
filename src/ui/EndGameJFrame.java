package ui;

import game.EndGamer;
import game.Game;
import game.Token;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ui.interfaces.ChangeableVisibility;

public class EndGameJFrame extends JFrame implements ChangeableVisibility {

  JPanel northPanel;
  JPanel southPanel;
  JPanel westPanel;
  JPanel eastPanel;
  JPanel centerPanel;

  JPanel token1Panel;
  JPanel token2Panel;

  JLabel winnerLabel;

  JLabel token1Label;
  JLabel token1GoldLabel;
  JLabel token1ArtifactLabel;
  JLabel token1ReputationLabel;
  JLabel token1ScoreLabel;

  JLabel token2Label;
  JLabel token2GoldLabel;
  JLabel token2ArtifactLabel;
  JLabel token2ReputationLabel;
  JLabel token2ScoreLabel;

  JLabel endGameIconLabel;

  JButton playAgainButton;
  JButton exitButton;

  ImageIcon endGameIcon;

  public EndGameJFrame(EndGamer endGamer) {
    super("End Game");
    this.setSize(1280, 720);
    setLayout(new BorderLayout());

    // initiate the panels and labels
    northPanel = new JPanel(new FlowLayout());
    southPanel = new JPanel(new FlowLayout());
    westPanel = new JPanel(new FlowLayout());
    eastPanel = new JPanel(new FlowLayout());
    centerPanel = new JPanel(new FlowLayout());

    token1Panel = new JPanel();
    token2Panel = new JPanel();

    token1Panel.setLayout(new BoxLayout(token1Panel, BoxLayout.Y_AXIS));
    token2Panel.setLayout(new BoxLayout(token2Panel, BoxLayout.Y_AXIS));

    winnerLabel = new JLabel();

    token1Label = new JLabel();
    token1GoldLabel = new JLabel();
    token1ArtifactLabel = new JLabel();
    token1ReputationLabel = new JLabel();
    token1ScoreLabel = new JLabel();

    token2Label = new JLabel();
    token2GoldLabel = new JLabel();
    token2ArtifactLabel = new JLabel();
    token2ReputationLabel = new JLabel();
    token2ScoreLabel = new JLabel();

    playAgainButton = new JButton("Play Again");
    exitButton = new JButton("Exit");

    endGameIcon = new ImageIcon("src/ui/utils/end-game.jpg");

    endGameIconLabel = new JLabel(endGameIcon);

    // set the sizes of the panels
    northPanel.setPreferredSize(new Dimension(1280, 200));
    southPanel.setPreferredSize(new Dimension(1280, 100));
    westPanel.setPreferredSize(new Dimension(300, 420));
    eastPanel.setPreferredSize(new Dimension(300, 420));

    // set the font of the labels
    winnerLabel.setFont(new Font("Serif", Font.BOLD, 30));
    token1Label.setFont(new Font("Serif", Font.BOLD, 20));
    token1GoldLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token1ArtifactLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token1ReputationLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token1ScoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token2Label.setFont(new Font("Serif", Font.BOLD, 20));
    token2GoldLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token2ArtifactLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token2ReputationLabel.setFont(new Font("Serif", Font.BOLD, 20));
    token2ScoreLabel.setFont(new Font("Serif", Font.BOLD, 20));

    Token token1 = endGamer.getTokens().get(0);
    Token token2 = endGamer.getTokens().get(1);

    // set the text of the labels
    if (endGamer.getWinner() == -1) {
      winnerLabel.setText("Draw");
    } else if (endGamer.getWinner() == 0) {
      winnerLabel.setText("Winner: " + token1.getUsername());
    } else {
      winnerLabel.setText("Winner: " + token2.getUsername());
    }

    token1Label.setText("Records of " + token1.getUsername());
    token1GoldLabel.setText("Gold: " + token1.getGoldBalance());
    token1ArtifactLabel.setText("Artifact Cards: " + token1.getArtifactCards().size());
    token1ReputationLabel.setText("Reputation: " + token1.getReputation());
    token1ScoreLabel.setText("Score: " + endGamer.getToken1Score());

    token2Label.setText("Records of " + token2.getUsername());
    token2GoldLabel.setText("Gold: " + token2.getGoldBalance());
    token2ArtifactLabel.setText("Artifact Cards: " + token2.getArtifactCards().size());
    token2ReputationLabel.setText("Reputation: " + token2.getReputation());
    token2ScoreLabel.setText("Score: " + endGamer.getToken2Score());

    // add the labels to the panels
    northPanel.add(winnerLabel);
    token1Panel.add(token1Label);
    token1Panel.add(token1GoldLabel);
    token1Panel.add(token1ArtifactLabel);
    token1Panel.add(token1ReputationLabel);
    token1Panel.add(token1ScoreLabel);
    token2Panel.add(token2Label);
    token2Panel.add(token2GoldLabel);
    token2Panel.add(token2ArtifactLabel);
    token2Panel.add(token2ReputationLabel);
    token2Panel.add(token2ScoreLabel);
    eastPanel.add(token1Panel);
    westPanel.add(token2Panel);
    southPanel.add(playAgainButton);
    southPanel.add(exitButton);
    centerPanel.add(endGameIconLabel);

    // add the panels to the frame
    add(northPanel, BorderLayout.NORTH);
    add(southPanel, BorderLayout.SOUTH);
    add(westPanel, BorderLayout.WEST);
    add(eastPanel, BorderLayout.EAST);
    add(centerPanel, BorderLayout.CENTER);

    // Set the action listeners

    // Add ActionListener to the "Play Again" button
    playAgainButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();
          game.openLogin(EndGameJFrame.this);
        }
      }
    );

    // Add ActionListener to the "Exit" button
    exitButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      }
    );

    // Set the frame location
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
