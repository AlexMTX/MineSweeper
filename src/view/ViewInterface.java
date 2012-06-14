package view;

import service.Observer;
import model.Cell;

public interface ViewInterface {
    
//    public void setWidth(int width);
//    public void setHeight(int height);
//    public void setMinesNumber(int minesNumber);
    
    public void registerObserver(Observer o);
    public void notifyObserver();
    
    public void resGame();
    public void newGame();
    
    public void initialise();
    
    public void draw(Cell[][] field, int isOver);
}
