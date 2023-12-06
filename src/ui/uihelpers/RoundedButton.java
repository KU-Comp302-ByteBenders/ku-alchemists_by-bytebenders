package ui.uihelpers;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    // This class extended from JButton, and purpose of this class is making the buttons on the deduction board circular shape.
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width + 1, getSize().height - 6);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width + 1, getSize().height - 6);
    }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y);
    }

    @Override
    // This method arrange the text inside of the buttons.
    public void setFont(Font font) {
        super.setFont(new Font(font.getName(), Font.PLAIN, font.getSize() + 8));
    }

}
