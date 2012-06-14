package service;

public interface Observer {
    public void clickModelCell(int x, int y, int button);
    public void resGame();
    public void newGame();
}
