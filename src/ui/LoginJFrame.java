package ui;

import game.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class LoginJFrame extends JFrame {

    private int playerCount = 2;
    private JButton addButton;
    private ArrayList<JTextField> playerTextFields = new ArrayList<>();
    private ArrayList<JComboBox<String>> playerComboBoxes = new ArrayList<>();

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

        // Create a drop-down menu
        String[] options1 = { "avatar_1", "avatar_2", "avatar_3", "avatar_4" };
        JComboBox<String> comboBox1 = new JComboBox<>(options1);

        playerComboBoxes.add(comboBox1);
        playerTextFields.add(textField1);

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

        panel.setBounds(450, 50, 750, 175);

        // Add an action listener to the button
        startButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (playerCount < 3) {
                            JOptionPane.showMessageDialog(LoginJFrame.this, "Add at least 1 more player before starting the game.", "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                        else {
                            Game game = Game.getInstance();
                
                            // Add player information
                            ArrayList<String> playerNames = new ArrayList<>();
                            ArrayList<String> playerAvatars = new ArrayList<>();
                
                            for (int i = 0; i < playerCount; i++) {
                                if (i < playerTextFields.size()) {
                                    playerNames.add(playerTextFields.get(i).getText());
                                }
                            
                                if (i < playerComboBoxes.size()) {
                                    playerAvatars.add(playerComboBoxes.get(i).getSelectedItem().toString());
                                }
                            }
                
                            // Pass the information to the startGame method
                            game.startGame(playerNames, playerAvatars, LoginJFrame.this);
                        }
                    }
                }
        );

        // Add action listeners to the avatar buttons
        button1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button1 click
                    }
                }
        );
        button2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button2 click
                    }
                }
        );
        button3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button3 click
                    }
                }
        );
        button4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button4 click
                    }
                }
        );

        // Add text fields and drop-down menu, and the avatar panel to the frame
        this.add(textField1);
        this.add(comboBox1);
        this.add(panel);
        this.add(startButton);

        // Set the position and size of the components
        textField1.setBounds(50, 90, 150, 20);
        comboBox1.setBounds(210, 90, 150, 20);
        startButton.setBounds(50, 570, 150, 20);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        // Set the frame location
        this.setLocation(x, y);

        // Make the frame visible
        this.setVisible(true);

        // Create a "Add Player" button
        addButton = new JButton("Add Player");
        addButton.setBounds(50, 600, 150, 20);
        this.add(addButton);

        // Add an action listener to the "Add Player" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerField();
            }
        });
    }

    private void addPlayerField() {
        if(playerCount == 4) {
            addButton.setEnabled(false);
        }
    
        // Create text field
        JTextField textField = new JTextField(20);
        textField.setText("Enter Player " + playerCount + " name");
        textField.setBounds(50, 50 + playerCount * 40, 150, 20);
        this.add(textField);
        playerTextFields.add(textField); // Add the text field to the list
    
        // Create a drop-down menu
        String[] options = {"avatar_1", "avatar_2", "avatar_3", "avatar_4"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(210, 50 + playerCount * 40, 150, 20);
        this.add(comboBox);
        playerComboBoxes.add(comboBox); // Add the combo box to the list

        revalidate();
        repaint();
    
        playerCount++;
    }
}
