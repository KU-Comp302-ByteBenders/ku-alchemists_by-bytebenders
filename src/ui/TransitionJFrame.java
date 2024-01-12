package ui;

import game.Board;
import game.State;
import game.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransitionJFrame extends JFrame {
    Token token;
    Board board;
    State state;

    ArrayList<Token> tokens;

    public TransitionJFrame(Token token, Board board, State state, ArrayList<Token> tokens) {
        this.board = board;
        this.token = token;
        this.state = state;
        this.tokens = tokens;
        this.setLayout(null);

        // Set the size of the frame
        this.setSize(1280, 720);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel(token.getUsername() + " will contiune the game!");
        JButton startButton = new JButton("Continue the Game");

        // Create a GridBagLayout and GridBagConstraints
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

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
                new OfflineBoardJFrame(token, board, state, tokens);
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
}
