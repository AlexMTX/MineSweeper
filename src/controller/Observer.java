package controller;

public interface Observer {
    //контроллер должен знать последнюю нажатую кнопку и координаты. Наблюдает View.
    public void update (int x, int y, int button);
}
