package ui;

import game.Board;
import game.State;
import game.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitionStarterJFrame extends JFrame {
    Token token;
    Board board;
    State state;


    public TransitionStarterJFrame(Token token, Board board, State state) {
        this.board = board;
        this.token = token;
        this.state = state;

        // Set the layout manager to null for absolute positioning
        this.setLayout(null);

        // Set the size of the frame
        this.setSize(1280, 720);

        this.setResizable(false);

        JLabel label1 = new JLabel(token.getUsername() + " will start the game!");
        JButton startButton = new JButton("Start the Game");


        // Create a GridBagLayout and GridBagConstraints
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a panel and add the avatar labels and buttons to it
        JPanel panel = new JPanel();
        panel.setLayout(layout);

        // Add label1 and button1 to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(startButton, gbc);

        startButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OfflineBoardJFrame(token, board, state, state.getTokens());
                closeFrame();
            }
        });

        this.add(panel);
        panel.setBounds(250, 50, 750, 175);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        // Set the frame location
        this.setLocation(x, y);

        // Make the frame visible
        this.setVisible(true);
    }

    public void closeFrame() {
        this.dispose();
    }
}
