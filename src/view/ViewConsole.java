package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.Timer;

import model.Cell;

public class ViewConsole extends ViewAbstract {

    public ViewConsole(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
    }
    
    int isOver = 0;
    
    Timer timer;
    int time = 0;
    
    @Override
    public void initialise() {
        
        Scanner sc = new Scanner(System.in);
        boolean continiueLoop = true;
        
//        time = 0;
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                time++;
//                System.out.println("console timer " + timer);
            }
        });
//        System.out.println("console timer inited " + timer);
        timer.start();
        
//        do {
        while (continiueLoop && isOver == 0) {
//            System.out.println("1 - Открыть; 2 - Пометить; 3 - Новая игра; 4 - Перезапустить игру:");
//            System.out.println("1 - Open; 2 - Mark; 3 - New game; 4 - Restart game:");
            
            System.out.println("1 - Open; 2 - Mark; 3 - Interrupt the game:");
            int choice = sc.nextInt();
            switch (choice) {
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
                x = sc.nextInt()-1;
                System.out.println("y:");
                y = sc.nextInt()-1;
                button=3;
//                m.setFlag(x - 1, y - 1);
//                v.draw(m.getField());
                break;

            case 3:
                continiueLoop = false;
                break;
//            case 3:
////                System.out.println("Новая игра");
//                newGame();
////                System.out.println("x:");
////                x = sc.nextInt()-1;
////                System.out.println("y:");
////                y = sc.nextInt()-1;
////                button=3;
//////                m.setQuestion(x - 1, y - 1);
//////                v.draw(m.getField());
//                break;
//                
//            case 4:
////                System.out.println("Перезапустить игру");
//                timer.stop();
//                isOver = 0;
//                time = 0;
//                resGame();
//                break;

            default:
                continiueLoop = false;
                break;
            }
            notifyObserver();
        }
//        } while (continiueLoop && isOver == 0);
        
        timer.stop();
        
        if (isOver == -1) {
//            System.out.println("Вы проиграли!");
            System.out.println("You lose!");
        }
        if (isOver == 1){
//            System.out.println("Вы выиграли!");
            System.out.println("You win!");
        }
//        System.out.println("Время: "+time);
        System.out.println("Time: "+time);
        
//        System.out.println("1 - Новая игра; 2 - Перезапустить игру");
        System.out.println("1 - New game; 2 - Restart game");
        switch (sc.nextInt()) {
        case 1:
//            System.out.println("Новая игра");
            newGame();
            break;
            
        case 2:
//            System.out.println("Перезапустить игру");
            isOver = 0;
            time = 0;
            resGame();
            break;
            
        default:
            break;
        }
    }

    
    @Override
    public void draw(Cell[][] field, int isOver) {

        this.isOver = isOver;
        
        System.out.println("Time: "+time);
        
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
