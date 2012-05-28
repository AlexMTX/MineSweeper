package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonMouseListener implements MouseListener {

    JButton button;

    public ButtonMouseListener(JButton button) {
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // button.setBackground(Color.WHITE);
        switch (arg0.getButton()) {
        case MouseEvent.BUTTON1:
            button.setBackground(Color.WHITE);
            break;
        case MouseEvent.BUTTON2:
            // System.out.println("2");
            break;
        case MouseEvent.BUTTON3:
            if (button.getBackground() == Color.GRAY) {
                button.setBackground(Color.WHITE);
                break;
            }
            button.setBackground(Color.GRAY);
            break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // System.out.println("Entered");
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // System.out.println("Exited");

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
