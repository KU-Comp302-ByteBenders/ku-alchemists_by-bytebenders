package ui;

import java.awt.Color;
import javax.swing.*;

public class LoginJFrame extends JFrame{
    public LoginJFrame() {
        // Create text fields
        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        
        // Set the size of the frame
        this.setSize(1280, 720);

        // Create a drop-down menu
        String[] options = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Add text fields and drop-down menu to the frame
        this.add(textField1);
        this.add(textField2);
        this.add(comboBox);

        // Set the layout manager to null for absolute positioning
        this.setLayout(null);

        // Set the position and size of the components
        textField1.setBounds(50, 50, 150, 20);
        textField2.setBounds(50, 80, 150, 20);
        comboBox.setBounds(50, 110, 150, 20);
    }
}
