package ui;

import game.Board;
import game.Game;
import game.State;
import game.Token;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BuyArtifactFrame extends JFrame {

  Board board;

  public BuyArtifactFrame(BoardJFrame boardJFrame, Board board, Token token, State state) {
    this.board = board;
    setTitle("Buy Artifact Cards");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Add buttons for each type of artifact card
    JButton MagicMortarButton = new JButton("Magic Mortar");
    JButton elixirOfInsightButton = new JButton("Elixir of Insight");
    JButton PrintingPressButton = new JButton("Printing Press");
    JButton WisdomIdolButton = new JButton("Wisdom Idol");

    // Add action listeners to handle button clicks
    //BUNLARA TURN MANTIĞI EKLEMEK LAZIM. KİMİN SIRASIYSA ONUN ARTİFACT DESTESİNE EKLESİN ALDIĞI KART

        elixirOfInsightButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token.getGoldBalance() < board.getArtifactCards().get(0).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Game.controlRoundAction(boardJFrame, state, true);
            Board.giveArtifactCardtoToken(token, board.getArtifactCards().get(0));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(0), token);

            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(1));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    MagicMortarButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token.getGoldBalance() < board.getArtifactCards().get(1).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Game.controlRoundAction(boardJFrame, state, true);
            Board.giveArtifactCardtoToken(token, board.getArtifactCards().get(1));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(1), token);

            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(0));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    PrintingPressButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token.getGoldBalance() < board.getArtifactCards().get(2).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Game.controlRoundAction(boardJFrame, state, true);
            Board.giveArtifactCardtoToken(token, board.getArtifactCards().get(2));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(2), token);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(2));

            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    WisdomIdolButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token.getGoldBalance() < board.getArtifactCards().get(3).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Game.controlRoundAction(boardJFrame, state, true);
            Board.giveArtifactCardtoToken(token, board.getArtifactCards().get(3));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(3), token);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(3));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    // Create a layout (e.g., GridLayout) and add buttons to it
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
    buttonPanel.add(elixirOfInsightButton);
    buttonPanel.add(MagicMortarButton);
    buttonPanel.add(PrintingPressButton);
    buttonPanel.add(WisdomIdolButton);

    // Set the layout of the main frame
    setLayout(new BorderLayout());
    add(buttonPanel, BorderLayout.CENTER);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }

  public void closeArtifactBuyScreen() {
    this.setVisible(false);
  }
}
