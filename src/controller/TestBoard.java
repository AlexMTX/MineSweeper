package controller;

import java.util.*;

public class TestBoard {

    int x; //ширина
    int y; //высота
    int n; // количество мин
    
    String[][] boardModel;

//    List<Obj> boardList = new ArrayList<Obj>();
    List<String> boardList = new ArrayList<String>(); // для шаффла

    // List<ArrayList<String>> boardCol = new ArrayList<ArrayList<String>>();

    TestBoard(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
        genMines();
    }

    private void genMines() {
        for (int i = 0; i < n; i++) {
//            Mine m = new Mine();
            boardList.add("+");
        }
        for (int i = 0; i < x * y - n; i++) {
//            Pointer p = new Pointer();
            boardList.add("-");
        }

        Collections.shuffle(boardList);
        
        
        Iterator<String> boardListIterator = boardList.iterator();
        boardModel = new String[x][y];
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boardModel[i][j] = boardListIterator.next();
            }
        }
        
        
//        for (int i = 0; i < boardList.size(); i++) {
//            if (boardList.get(i) == "+"){
//                String left = boardList.get(i-1);
//                String right = boardList.get(i+1);
//                String upLeft= boardList.get(i-i%x - ((x/i-1)*y));
//                String downLeft = boardList.get(i-1);
//                
//            }
//        }
    }

    public void consOut() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
//                System.out.print(boardList.get(i * y + j)+" ");
                
                System.out.print(boardModel[i][j]+" ");
//                System.out.print("    ");
                
                
//                if (boardList.get(i * y + j).equals()) {
//                    System.out.print("* ");
//                } else {
//                    System.out.print("- ");
//                }

            }
            System.out.println();
        }
        
//        System.out.println();
//        
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                System.out.print(boardModel[i][j]+" ");
//            }
//            System.out.println();
//        }

    }

}
