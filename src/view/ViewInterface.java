package view;

import service.Observer;
import model.Cell;

public interface ViewInterface {
    
//    public void setWidth(int width);
//    public void setHeight(int height);
//    public void setMinesNumber(int minesNumber);
    
    public void registerObserver(Observer o);
    public void notifyViewCellClicked();
    
    public void notifyResGame();
    public void notifyNewGame();
    
    public void initialise();
    
    public void draw(Cell[][] field, int gameStatus);
}
