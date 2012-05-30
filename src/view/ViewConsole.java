package view;

import java.util.Scanner;

import model.Cell;

public class ViewConsole extends ViewAbstract {

    public ViewConsole(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
        // TODO Auto-generated constructor stub

        /*
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Открыть; 2 - Поставить флаг; 3 - Поставить вопрос");
            switch (sc.nextInt()) {
            case 1:
                System.out.println("x:");
                x = sc.nextInt()-1;
                System.out.println("y:");
                y = sc.nextInt()-1;
                button=1;
//                m.openCell(x - 1, y - 1);
//                v.draw(m.getField());
                break;

            case 2:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                button=3;
//                m.setFlag(x - 1, y - 1);
//                v.draw(m.getField());
                break;

            case 3:
                System.out.println("x:");
                x = sc.nextInt();
                System.out.println("y:");
                y = sc.nextInt();
                button=3;
//                m.setQuestion(x - 1, y - 1);
//                v.draw(m.getField());
                break;

            default:
                break;
            }
            notifyObserver();
        }
        */

    }

    // notifyObserver();?

    @Override
    public void draw(Cell[][] field) {

        // окаймление вдоль
        System.out.print("x\\y| ");
        for (int i = 0; i < width; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.print("\n ");
        for (int i = 0; i < width * 2 + 3; i++) {
            System.out.print("_");
        }
        System.out.println();
        // ------------------

        for (int i = 0; i < height; i++) {
            System.out.print(" " + (i + 1) + " | ");
            for (int j = 0; j < width; j++) {
                if (field[i][j].isOpened()) {
                    if (field[i][j].isMined()) {
                        System.out.print("* ");
                    } else {
                        if (field[i][j].getMinesAround() == 0) {
                            System.out.print("  ");
                        } else {
                            System.out
                                    .print(field[i][j].getMinesAround() + " ");
                        }

                    }
                } else {
                    if (field[i][j].isFlagged()) {
                        System.out.print("& ");
                    }
                    if (field[i][j].isQuestioned()) {
                        System.out.print("? ");
                    }
                    if (!field[i][j].isFlagged() && !field[i][j].isQuestioned()) {
                        System.out.print("# ");
                    }
                }

            }
            // if ((i+1)%5==0) {
            // System.out.println();
            // for (int k = 0; k < width+2; k++) {
            // System.out.print("- ");
            // }
            // }
            System.out.println();
        }
        System.out.println();

    }

}
