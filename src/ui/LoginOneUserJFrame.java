package ui;

import game.Client;
import game.Game;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ui.interfaces.ChangeableVisibility;

public class LoginOneUserJFrame extends JFrame implements ChangeableVisibility {

  JPanel panel;
  JTextField textField1;
  JTextField textField2;
  JButton readyButton;

  public LoginOneUserJFrame(boolean isClient) {
    // Set the layout manager to null for absolute positioning
    this.setLayout(null);

    // Set the size of the frame
    this.setSize(1280, 720);

    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create text fields
    textField1 = new JTextField(20);
    textField1.setBounds(50, 50, 150, 20);
    textField1.setText("Enter Player 1 name");

    textField2 = new JTextField(20);
    textField2.setBounds(50, 110, 150, 20);
    textField2.setText("Enter the IP address of the host");

    // Create a drop-down menu
    String[] options1 = { "avatar_1", "avatar_2", "avatar_3", "avatar_4" };
    JComboBox<String> comboBox1 = new JComboBox<>(options1);

    readyButton = new JButton("Ready!!");

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

    // Create a GridBagLayout and GridBagConstraints
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    // Create a panel and add the avatar labels and buttons to it
    panel = new JPanel();
    panel.setLayout(layout);

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("CHOOSE YOUR AVATAR!!"), gbc);

    // Add label1 and button1 to the panel
    gbc.gridx = 4;
    gbc.gridy = 3;
    panel.add(button1, gbc);

    // Add label2 and button2 to the panel
    gbc.gridx = 5;
    gbc.gridy = 3;
    panel.add(button2, gbc);

    // Add label3 and button3 to the panel
    gbc.gridx = 6;
    gbc.gridy = 3;
    panel.add(button3, gbc);

    // Add label4 and button4 to the panel
    gbc.gridx = 7;
    gbc.gridy = 3;
    panel.add(button4, gbc);

    // Add an action listener to the button
    readyButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Game game = Game.getInstance();

          if (isClient) {
            LoginOneUserJFrame.this.remove(readyButton);
            LoginOneUserJFrame.this.remove(textField2);
            LoginOneUserJFrame.this.remove(textField1);
            LoginOneUserJFrame.this.remove(panel);

            // TODO: (FRONT) We can add a good background image here.
            LoginOneUserJFrame.this.setLayout(new GridLayout(1, 1));
            LoginOneUserJFrame.this.add(new JLabel("Waiting for the host to start the game..."));
            LoginOneUserJFrame.this.setVisible(false);
            LoginOneUserJFrame.this.setVisible(true);

            Client client = new Client();
            client.connect(
              textField2.getText(),
              textField1.getText(),
              comboBox1.getSelectedItem().toString(),
              LoginOneUserJFrame.this
            );
          } else {
            game.openWaitingRoom(LoginOneUserJFrame.this, textField1.getText(), comboBox1.getSelectedItem().toString());
          }
        }
      }
    );

    // Add action listeners to the avatar buttons
    button1.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          comboBox1.setSelectedItem("avatar_1");
        }
      }
    );
    button2.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          comboBox1.setSelectedItem("avatar_2");
        }
      }
    );
    button3.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          comboBox1.setSelectedItem("avatar_3");
        }
      }
    );
    button4.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          comboBox1.setSelectedItem("avatar_4");
        }
      }
    );

    // Add text fields and drop-down menu, and the avatar panel to the frame
    this.add(textField1);
    if (isClient) {
      this.add(textField2);
    }
    this.add(panel);
    // this.add(comboBox1);
    this.add(readyButton);

    // Set the position and size of the components
    textField1.setBounds(50, 50, 150, 20);
    comboBox1.setBounds(50, 110, 150, 20);
    readyButton.setBounds(50, 170, 150, 20);
    panel.setBounds(250, 50, 750, 175);

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
