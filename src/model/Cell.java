package model;

public class Cell {
    
    private int x;
    private int y;
    private boolean isMined;
    private int minesAround;
    private boolean isOpened;
    private boolean isFlagged;
    private boolean isQuestioned;
    
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean isMined() {
        return isMined;
    }
    public void setMined(boolean isMined) {
        this.isMined = isMined;
    }
    public int getMinesAround() {
        return minesAround;
    }
    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }
    public boolean isOpened() {
        return isOpened;
    }
    public void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }
    public boolean isQuestioned() {
        return isQuestioned;
    }
    public void setQuestioned(boolean isQuestioned) {
        this.isQuestioned = isQuestioned;
    }
    
    

}
