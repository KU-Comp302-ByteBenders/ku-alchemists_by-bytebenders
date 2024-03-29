package ui;

import game.Board;
import game.Game;
import game.Token;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TriangleBoardJFrame extends JFrame { // this class for selecting and arranging out deduction board. When tokens click the button on the deduction board,

  // we open this. Our tokens can select the different options like colors, sign.
  Token token;
  int index;
  Board board;

  public TriangleBoardJFrame(JButton pressedbutton, Token token, int index, Board board){
    this.board = board;
    this.token = token;
    this.index = index;
    // Set the layout manager to null for absolute positioning
    this.setLayout(null);

    // Set the size of the frame
    this.setSize(400, 400);

    this.setResizable(false);

    // Create text fields
    JLabel label = new JLabel("Choose a sign");

    JRadioButton option1 = new JRadioButton("∅");
    JRadioButton option2 = new JRadioButton("+");
    JRadioButton option3 = new JRadioButton("-");

    ButtonGroup group = new ButtonGroup();
    group.add(option1);
    group.add(option2);
    group.add(option3);

    // Create a "Start the Game" button
    JButton applyButton = new JButton("Mark to triangle");
    JButton closeButton = new JButton("Close");

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    // Create a panel and add the avatar labels and buttons to it
    JPanel panel = new JPanel();
    panel.setLayout(layout);

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(label, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(option1, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(option2, gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    panel.add(option3, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(applyButton, gbc);

    gbc.gridx = 2;
    gbc.gridy = 4;
    panel.add(closeButton, gbc);

    // Add an action listener to the button
    closeButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          closeTheFrame();
        }
      }
    );

    applyButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Clear any previous warnings or components

          if (option1.isSelected()) {
            pressedbutton.setText("∅");
            pressedbutton.setBackground(null);
            closeTheFrame();
            setTokenButton("∅", "");
          } else if (!option1.isSelected() && !option2.isSelected() && !option3.isSelected()) {
            JOptionPane.showMessageDialog(panel, "You didn't select a sign");
          } else {
            String[] colors = { "Red", "Blue", "Yellow" };
            JComboBox<String> comboBox1 = new JComboBox<>(colors);
            comboBox1.setBounds(50, 110, 150, 120);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(comboBox1, gbc);

            if (option2.isSelected()) {
              comboBox1.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                    String selectedColor = comboBox1.getSelectedItem().toString();
                    if (selectedColor.equals("Red")) {
                      pressedbutton.setBackground(Color.RED);
                      pressedbutton.setText("+");
                      setTokenButton("+", "Red");
                      closeTheFrame();
                    }
                    if (selectedColor.equals("Blue")) {
                      pressedbutton.setBackground(Color.BLUE);
                      pressedbutton.setText("+");
                      setTokenButton("+", "Blue");
                      closeTheFrame();
                    }
                    if (selectedColor.equals("Yellow")) {
                      pressedbutton.setBackground(Color.YELLOW);
                      pressedbutton.setText("+");
                        setTokenButton("+", "Yellow");
                      closeTheFrame();
                    }
                  }
                }
              );
            } else if (option3.isSelected()) {
              comboBox1.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                    String selectedColor = comboBox1.getSelectedItem().toString();
                    if (selectedColor.equals("Red")) {
                      pressedbutton.setBackground(Color.RED);
                      pressedbutton.setText("-");
                      setTokenButton("-", "Red");
                      closeTheFrame();
                    }
                    if (selectedColor.equals("Blue")) {
                      pressedbutton.setBackground(Color.BLUE);
                      pressedbutton.setText("-");
                      setTokenButton("-", "Blue");
                      closeTheFrame();
                    }
                    if (selectedColor.equals("Yellow")) {
                      pressedbutton.setBackground(Color.YELLOW);
                      pressedbutton.setText("-");
                      setTokenButton("-", "Yellow");
                      closeTheFrame();
                    }
                  }
                }
              );
            }
          }

          // Refresh the panel
          panel.revalidate();
          panel.repaint();
        }
      }
    );

    this.add(panel);

    // Set the position and size of the components
    panel.setBounds(10, -120, 400, 350);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - this.getWidth()) / 2;
    int y = (screenSize.height - this.getHeight()) / 2;

    // Set the frame location
    this.setLocation(x, y);

    // Make the frame visible
    this.setVisible(true);
  }

  public void closeTheFrame() {
    Game game = Game.getInstance();
    if(game.isOffline()){
      this.setVisible(false);
    }
    else{
      game.publishAction("Action " + board.getTokens().indexOf(token) + " DeductionBoard");
      this.setVisible(false);
    }
    
    
  }
  public void setTokenButton(String sign, String color) {
    token.setTriangle(index, sign, color);
  }
}
