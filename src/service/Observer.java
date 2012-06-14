package service;

public interface Observer {
    //контроллер должен знать последнюю нажатую кнопку и координаты. Наблюдает View.
    public void updateModel (int x, int y, int button);
    public void resGame();
    public void newGame();
}
