package model;

public class Model {
    private int height;
    private int width;
    private int minesNumber;

    private int gameStatus = Const.PLAYING_GAME;
    private boolean fieldGenerated = false;

    private Cell[][] field;

    public Model(int height, int width, int minesNumber) {
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

    public int gameStatus() {
        return gameStatus;
    }

    public void setIsOver(int gameStatus) {
        this.gameStatus = gameStatus;
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
