package view;

import controller.Observer;

public abstract class ViewAbstract implements ViewInterface {

    protected int width;
    protected int height;
    protected int minesNumber;

    ViewAbstract(int height, int width, int minesNumber) {
        this.height = height;
        this.width = width;
        this.minesNumber = minesNumber;
    }

    
    protected Observer observer;

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    protected int x;
    protected int y;
    protected int button;
    
    @Override
    public void notifyObserver() {
        observer.update(x, y, button);
        
    }

    
    
    
    
    // @Override
    // public void setWidth(int width) {
    // this.width=width;
    //
    // }
    //
    // @Override
    // public void setHeight(int height) {
    // this.height = height;
    //
    // }
    //
    // @Override
    // public void setMinesNumber(int minesNumber) {
    // this.minesNumber = minesNumber;
    //
    // }

}
