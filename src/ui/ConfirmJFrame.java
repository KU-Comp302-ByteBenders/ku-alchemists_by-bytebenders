package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ConfirmJFrame extends JFrame {
    private static final String messageString1 = "You've selected the ";
    private static final String messageString2 = ". Are you sure you want to debunk the theory on ";

    public ConfirmJFrame(String aspectColor, String ingredientName, ConfirmationListener listener) {
        super("Confirm"); // Set the title of the frame
        setSize(600, 400); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit

        // Create a JLabel with the message and add it to the frame
        String finalMessageString = messageString1 + aspectColor + messageString2 + ingredientName + "?";
        JLabel messageLabel = new JLabel(finalMessageString, SwingConstants.CENTER);
        add(messageLabel, BorderLayout.CENTER);

        // Create a JButton with the text "Yes" and add it to the frame
        // When this button is clicked, set isConfirmed to true and close the frame
        // BoardJFrame will take this message and proceed with the Debunk Theory action
        
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    try {
                        dispose();
                        listener.onConfirmed();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
            }
          });
        add(yesButton, BorderLayout.SOUTH);

        setVisible(true); // Make the frame visible
    }
}
