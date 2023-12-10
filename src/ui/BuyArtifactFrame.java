package ui;

import game.Board;
import game.Token;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BuyArtifactFrame extends JFrame {

  Board board;

  public BuyArtifactFrame(BoardJFrame boardJFrame, Board board, Token token1, Token token2) {
    this.board = board;
    setTitle("Buy Artifact Cards");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Add buttons for each type of artifact card
    JButton healingDraughtButton = new JButton("Healing Draught");
    JButton elixirOfVitalityButton = new JButton("Elixir of Vitality");
    JButton celestialMendButton = new JButton("Celestial Mend");
    JButton kindlyGestureButton = new JButton("Kindly Gesture");
    JButton respectedDeedButton = new JButton("Respected Deed");
    JButton virtueBadgeButton = new JButton("Virtue Badge");
    JButton healingButton = new JButton("Small Fortune Pouch");
    JButton goldButton = new JButton("Treasure Trove");
    JButton reputationButton = new JButton("Elixir of Insight");

    // Add action listeners to handle button clicks
    //BUNLARA TURN MANTIĞI EKLEMEK LAZIM. KİMİN SIRASIYSA ONUN ARTİFACT DESTESİNE EKLESİN ALDIĞI KARTI

    healingButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(0).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(0));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(0), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(6));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    goldButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(1).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(1));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(1), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(7));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    reputationButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(2).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(2));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(2), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(8));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    healingDraughtButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(3).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(3));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(3), token1);

            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(0));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    elixirOfVitalityButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(4).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(4));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(4), token1);

            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(1));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    celestialMendButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(5).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(5));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(5), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(2));

            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    kindlyGestureButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(6).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(6));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(6), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(3));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    respectedDeedButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(7).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(7));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(7), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(4));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    virtueBadgeButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (token1.getGoldBalance() < board.getArtifactCards().get(8).getGoldPrice()) {
            System.err.println("You do not have enough gold to buy the artifact card!");
          } else {
            Board.giveArtifactCardtoToken(token1, board.getArtifactCards().get(8));
            boardJFrame.createArtifactUseButton(board.getArtifactCards().get(8), token1);
            //boardJFrame.refreshArtifactCardsArea(board.getArtifactCards().get(5));
            closeArtifactBuyScreen();
            boardJFrame.updateTokensGoldLabel();
          }
        }
      }
    );

    // Create a layout (e.g., GridLayout) and add buttons to it
    JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
    buttonPanel.add(healingButton);
    buttonPanel.add(goldButton);
    buttonPanel.add(reputationButton);
    buttonPanel.add(healingDraughtButton);
    buttonPanel.add(elixirOfVitalityButton);
    buttonPanel.add(celestialMendButton);
    buttonPanel.add(kindlyGestureButton);
    buttonPanel.add(respectedDeedButton);
    buttonPanel.add(virtueBadgeButton);

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
