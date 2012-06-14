package model;

public class Model2 {
    private int height;
    private int width;
    private int minesNumber;

    private int isOver = 0;
    private boolean fieldGenerated = false;
    
    private Cell[][] field;

    
    public Model2(int height, int width, int minesNumber) {
        this.height = height;
        this.width = width;
        this.minesNumber = minesNumber;
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public int isOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }

    public boolean isFieldGenerated() {
        return fieldGenerated;
    }

    public void setFieldGenerated(boolean fieldGenerated) {
        this.fieldGenerated = fieldGenerated;
    }

    public Cell[][] getField() {
        return field;
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }
}