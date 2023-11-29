package ui;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;

public class LoginJFrame extends JFrame{
    public LoginJFrame() {
        // Create text fields
        JTextField textField1 = new JTextField(20);
        textField1.setText("Enter Player 1 name");

        JTextField textField2 = new JTextField(20);
        textField2.setText("Enter Player 2 name");

        // Set the size of the frame
        this.setSize(1280, 720);

        // Create a drop-down menu
        String[] options1 = {"Avatar 1", "Avatar 2", "Avatar 3", "Avatar 4"};
        JComboBox<String> comboBox1 = new JComboBox<>(options1);

        String[] options2 = {"Avatar 1", "Avatar 2", "Avatar 3", "Avatar 4"};
        JComboBox<String> comboBox2 = new JComboBox<>(options2);

        // Create a "Start the Game" button
        JButton startButton = new JButton("Start the Game");


        
        // Add an action listener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return the game instance
                System.out.println("Start the game");
                LoginJFrame.this.setVisible(false);
                Game game = Game.getInstance();
                // Do something with the game instance
            }
        });  
        

        // Add text fields and drop-down menu to the frame
        this.add(textField1);
        this.add(textField2);
        this.add(comboBox1);
        this.add(comboBox2);
        this.add(startButton);

        // Set the layout manager to null for absolute positioning
        this.setLayout(null);

        // Set the position and size of the components
        textField1.setBounds(50, 50, 150, 20);
        textField2.setBounds(50, 80, 150, 20);
        comboBox1.setBounds(50, 110, 150, 20);
        comboBox2.setBounds(50, 140, 150, 20);
        startButton.setBounds(50, 170, 150, 20); // Set the position and size of the button
    }
}
