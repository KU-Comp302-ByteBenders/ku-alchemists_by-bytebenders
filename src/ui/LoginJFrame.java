package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;

public class LoginJFrame extends JFrame{
    public LoginJFrame() {
        // Set the layout manager to null for absolute positioning
        this.setLayout(null);

        // Set the size of the frame
        this.setSize(1280, 720);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create text fields
        JTextField textField1 = new JTextField(20);
        textField1.setText("Enter Player 1 name");

        JTextField textField2 = new JTextField(20);
        textField2.setText("Enter Player 2 name");

        // Create a drop-down menu
        String[] options1 = {"avatar_1", "avatar_2", "avatar_3", "avatar_4"};
        JComboBox<String> comboBox1 = new JComboBox<>(options1);

        String[] options2 = {"avatar_1", "avatar_2", "avatar_3", "avatar_4"};
        JComboBox<String> comboBox2 = new JComboBox<>(options2);

        // Create a "Start the Game" button
        JButton startButton = new JButton("Start the Game");

        // Create ImageIcons
        ImageIcon icon1 = new ImageIcon("src/ui/utils/avatar_1.png");
        ImageIcon icon2 = new ImageIcon("src/ui/utils/avatar_2.png");
        ImageIcon icon3 = new ImageIcon("src/ui/utils/avatar_3.png");
        ImageIcon icon4 = new ImageIcon("src/ui/utils/avatar_4.png");

        // Create buttons with ImageIcons
        JButton button1 = new JButton(icon1);
        JButton button2 = new JButton(icon2);
        JButton button3 = new JButton(icon3);
        JButton button4 = new JButton(icon4);

        // Create avatar labels
        JLabel label1 = new JLabel("avatar_1");
        JLabel label2 = new JLabel("avatar_2");
        JLabel label3 = new JLabel("avatar_3");
        JLabel label4 = new JLabel("avatar_4");       
        
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
        gbc.gridy = 1;
        panel.add(button1, gbc);

        // Add label2 and button2 to the panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(label2, gbc);
        gbc.gridy = 1;
        panel.add(button2, gbc);

        // Add label3 and button3 to the panel
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(label3, gbc);
        gbc.gridy = 1;
        panel.add(button3, gbc);

        // Add label4 and button4 to the panel
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(label4, gbc);
        gbc.gridy = 1;
        panel.add(button4, gbc);
        
        // Add an action listener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return the game instance
                System.out.println("Start the game");
                LoginJFrame.this.setVisible(false);
                Game game = Game.getInstance();
                game.startGame(textField1.getText(), textField2.getText(), comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString());
                // Do something with the game instance
            }
        });  

        // Add action listeners to the avatar buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button1 click
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button2 click
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button3 click
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button4 click
            }
        });

        // Add text fields and drop-down menu, and the avatar panel to the frame
        this.add(textField1);
        this.add(textField2);
        this.add(panel);
        this.add(comboBox1);
        this.add(comboBox2);
        this.add(startButton);

        // Set the position and size of the components
        textField1.setBounds(50, 50, 150, 20);
        textField2.setBounds(50, 80, 150, 20);
        comboBox1.setBounds(50, 110, 150, 20);
        comboBox2.setBounds(50, 140, 150, 20);
        startButton.setBounds(50, 170, 150, 20);
        panel.setBounds(250, 50, 750, 175);
    }
}
