package controller;

import java.util.Scanner;

import view.ViewAbstract;
import view.ViewConsole;
import view.ViewInterface;
import view.ViewWindow;

import model.Model;

public class Controller implements Observer {
    
//    private int x;
//    private int y;
//    private int button;
    
    private Model m;
    private ViewAbstract v;

//    public Controller(){
//        this.v=v;
//        v.registerObserver(this);
//    }
//    
//    private register(){
    
    public void newGame() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Высота поля:");
        int height = sc.nextInt();

        System.out.println("Ширина поля:");
        int width = sc.nextInt();

        System.out.println("Количество мин:");
        int minesNumber = sc.nextInt();

        System.out.println("Тип отображения: 1 - Консоль; 2 - Окно");
        int viewType = sc.nextInt();

        m = new Model(height, width, minesNumber);

        switch (viewType) {
        case 1:
            v = new ViewConsole(height, width, minesNumber);
            break;

        case 2:
            v = new ViewWindow(height, width, minesNumber);
            break;
        }
        
        v.registerObserver(this);

        /*
        while (m.isOver() == 0) {
            System.out
                    .println("1 - Открыть; 2 - Поставить флаг; 3 - Поставить вопрос");
            int x;
            int y;
            switch (sc.nextInt()) {
            case 1:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                m.openCell(x - 1, y - 1);
                v.draw(m.getField());
                break;

            case 2:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                m.setFlag(x - 1, y - 1);
                v.draw(m.getField());
                break;

            case 3:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                m.setQuestion(x - 1, y - 1);
                v.draw(m.getField());
                break;

            default:
                break;
            }
        }
        */

    }

    //View update'ид последнюю нажатую кнопку, координаты.
    //запускает обработку модели?
    @Override
    public void update(int x, int y, int button) {
//        this.x = x;
//        this.y = y;
//        this.button = button;
        
        switch (button) {
        case 1:
            m.openCell(x, y);
            break;
        case 2:
            //средняя кнопка
            break;
            
        case 3: 
            if (m.getField()[x][y].isFlagged()) {
                m.setQuestion(x, y);
            }
            if (m.getField()[x][y].isQuestioned()) {
                m.setQuestion(x, y);
            }
            if (!m.getField()[x][y].isFlagged()&&!m.getField()[x][y].isQuestioned()) {
                m.setFlag(x, y);
            }
            break;
        default:
            break;
        }
        
        v.draw(m.getField());

    }

}
