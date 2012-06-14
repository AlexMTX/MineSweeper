package view;

import javax.swing.JButton;

public class CellButton extends JButton {

    /**
     * 
     */
    private static final long serialVersionUID = -7975332038775556291L;
    private int x;
    private int y;

    public CellButton(int x, int y) {
        this.x = x;
        this.y = y;
//        setText(x+", "+y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
