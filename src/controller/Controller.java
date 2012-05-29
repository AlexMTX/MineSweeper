package controller;

import java.util.Scanner;

import view.ViewAbstract;
import view.ViewConsole;
import view.ViewInterface;
import view.ViewWindow;

import model.Model;

public class Controller {

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

        Model m = new Model(height, width, minesNumber);
        ViewInterface v = null;
        
        switch (viewType) {
        case 1:
            v = new ViewConsole(height,width,minesNumber);
//            m.setView(new ViewConsole(height,width,minesNumber));
            break;

        case 2:
            v = new ViewWindow(height, width, minesNumber);
//            m.setView(new ViewWindow(height,width,minesNumber));
            break;
        }
        

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
        
    }

}
