package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

import model.Cell;
import model.Const;

public class ViewConsole extends ViewAbstract {

    public ViewConsole(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
    }

    int gameStatus = Const.PLAYING_GAME;

    @Override
    public void initialise() {

        Scanner sc = new Scanner(System.in);
        boolean continiueLoop = true;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                time++;
            }
        });
        timer.start();

        while (continiueLoop && gameStatus == Const.PLAYING_GAME) {
            System.out.println("1 - Открыть; 2 - Пометить; 3 - Прервать игру:");
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                System.out.println("x:");
                x = sc.nextInt() - 1;
                System.out.println("y:");
                y = sc.nextInt() - 1;
                button = 1;
                break;

            case 2:
                System.out.println("x:");
                x = sc.nextInt() - 1;
                System.out.println("y:");
                y = sc.nextInt() - 1;
                button = 3;
                break;

            case 3:
                continiueLoop = false;
                break;

            default:
                continiueLoop = false;
                break;
            }
            notifyViewCellClicked();
        }

        timer.stop();

        if (gameStatus == Const.LOSE_GAME) {
            System.out.println("Вы проиграли!");
        }
        if (gameStatus == Const.WIN_GAME) {
            System.out.println("Вы выиграли!");
        }
        System.out.println("Время: " + time);

        System.out.println("1 - Новая игра; 2 - Перезапустить игру");
        switch (sc.nextInt()) {
        case 1:
            notifyNewGame();
            break;

        case 2:
            gameStatus = Const.PLAYING_GAME;
            time = 0;
            notifyResGame();
            break;

        default:
            break;
        }
    }

    @Override
    public void draw(Cell[][] field, int gameStatus) {

        this.gameStatus = gameStatus;

        System.out.println("Время: " + time);

        System.out.print("x\\y| ");
        for (int i = 0; i < width; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.print("\n ");
        for (int i = 0; i < width * 2 + 3; i++) {
            System.out.print("_");
        }
        System.out.println();

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
                            System.out.print(field[i][j].getMinesAround() + " ");
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
            System.out.println();
        }
        System.out.println();
    }

}
