package service;

import java.util.Scanner;

import javax.swing.Timer;




import view.ViewAbstract;
import view.ViewConsole;
import view.ViewInterface;
import view.ViewWindow;

import model.Model;

public class Service implements Observer {
    
//    private int x;
//    private int y;
//    private int button;
    
    private Model model;
    private ViewAbstract view;
    
    private Timer timer;

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

        model = new Model(height, width, minesNumber);

        switch (viewType) {
        case 1:
            view = new ViewConsole(height, width, minesNumber);
            break;

        case 2:
            view = new ViewWindow(height, width, minesNumber);
            break;
        }
        
        view.registerObserver(this);
        
//        timer = new Timer();
//        timer.
        
        view.initialise();

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
            model.openCell(x, y);
            break;
        case 2:
            //средняя кнопка
            break;
            
        case 3: 
            if (model.getField()[x][y].isFlagged()) {
                model.setQuestion(x, y);
                break;
            }
            if (model.getField()[x][y].isQuestioned()) {
                model.setQuestion(x, y);
                break;
            }
            if (!model.getField()[x][y].isFlagged()&&!model.getField()[x][y].isQuestioned()) {
                model.setFlag(x, y);
                break;
            }
        default:
            break;
        }
        
        view.draw(model.getField());

    }

}
