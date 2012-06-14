package view;

import javax.swing.Timer;

import service.Observer;

public abstract class ViewAbstract implements ViewInterface {

    protected Timer timer;
    protected int time = 0;

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
    public void notifyViewCellClicked() {
        observer.clickModelCell(x, y, button);
    }

    @Override
    public void notifyResGame() {
        observer.resGame();
    }

    @Override
    public void notifyNewGame() {
        observer.newGame();
    }

}
