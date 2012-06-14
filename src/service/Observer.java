package service;

public interface Observer {
    //Service должен знать последнюю нажатую кнопку и координаты. Наблюдает View.
    public void clickModelCell (int x, int y, int button);
    public void resGame();
    public void newGame();
}
