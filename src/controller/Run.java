package controller;

import java.util.Scanner;

import javax.swing.JFrame;

import model.Model;

import view.Frame;

public class Run {

    /**
     * @param args
     */
    public static void main(String[] args) {

        JFrame frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        // frame.setVisible(true);

        // System.out.println("- - - 1 * 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");
        // System.out.println("- - - 1 1 1 - - -");

        // TestBoard b = new TestBoard(9, 9, 10);
        // b.consOut();

        // //проверка generateField, дб public
        // Model m = new Model(9,9,10);
        // m.generateField(2, 2);
        // m.openCell(2, 2);

        Scanner sc = new Scanner(System.in);

        System.out.println("Высота поля:");
        int height = sc.nextInt();

        System.out.println("Ширина поля:");
        int width = sc.nextInt();

        System.out.println("Количество мин:");
        int minesNumber = sc.nextInt();

        // System.out.println("Тип отображения: 1 - Консоль; 2 - Окно");
        // int view = sc.nextInt();

        Model m = new Model(height, width, minesNumber);

        while (!m.isOver()) {
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
                m.consOut();
                break;

            case 2:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                m.setFlag(x - 1, y - 1);
                m.consOut();
                break;

            case 3:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                m.setQuestion(x - 1, y - 1);
                m.consOut();
                break;

            default:
                break;
            }
        }

    }

}
