package view;

import model.Cell;

public interface ViewInterface {
    
//    public void setWidth(int width);
//    public void setHeight(int height);
//    public void setMinesNumber(int minesNumber);
    
    public void draw(Cell[][] field);
}
