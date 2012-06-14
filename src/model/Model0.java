package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//import view.ViewInterface;

public class Model0 {
    private int height;
    private int width;
    private int minesNumber;

    private int isOver = 0;
    private boolean fieldGenerated = false;
    
//    private ViewInterface view;

    private Cell[][] field;

//    public Model(int height, int width, int minesNumber) {
//        this.height = height;
//        this.width = width;
//        this.minesNumber = minesNumber;
//
//    }

    /* генерирует минное поле. клетка, на которую нажали всегда будет не миной */
    // private
    private void generateField(int xWhereOpened, int yWhereOpened) {

        List<Cell> fieldList = new ArrayList<Cell>(); // для шаффла

        for (int i = 0; i < minesNumber; i++) { // мины
            Cell c = new Cell();
            c.setMined(true);
            fieldList.add(c);
        }

        /*
         * пустые клетки, -1 чтобы в нужную позицию (xWhereOpened, yWhereOpened)
         * положить заведомо незаминированную клетку
         */
        for (int i = 0; i < height * width - minesNumber - 1; i++) {
            Cell c = new Cell();
            c.setMined(false);
            fieldList.add(c);
        }

        Collections.shuffle(fieldList);

        Iterator<Cell> fieldListIterator = fieldList.iterator();
        field = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell c;
                if (i == xWhereOpened && j == yWhereOpened) {
                    c = new Cell();
                    c.setMined(false);
                } else {
                    c = fieldListIterator.next();
                }
                c.setX(i);
                c.setY(j);
                field[i][j] = c;
            }
        }

        //расчет мин вокруг для всех ячеек. Перенесено в recursiveOpen для расчета во время нажатия.
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                Cell c = field[i][j];
//                if (!c.isMined())
//                    c.setMinesAround(countMinesAround(c));
//            }
//        }

        fieldGenerated = true;

    }

    private int countMinesAround(Cell c) {
        int minesNumber = 0;
        int xInitial = c.getX();
        int yInitial = c.getY();

        // одно лишнее действие, когда 0 0
        for (int lengthwise = -1; lengthwise <= 1; lengthwise++) {
            for (int crosswise = -1; crosswise <= 1; crosswise++) {
                int xNeighbour = xInitial + crosswise;
                int yNeighbour = yInitial + lengthwise;
                if (xNeighbour >= 0 && xNeighbour < height && yNeighbour >= 0
                        && yNeighbour < width) {
                    Cell neighbourCell = field[xNeighbour][yNeighbour];
                    if (neighbourCell.isMined() == true) {
                        minesNumber++;
                    }
                }
            }
        }

        return minesNumber;
    }

    private void recursiveOpen(int x, int y) {
        Cell c = field[x][y];
        if (!c.isFlagged()) {

            if (c.isMined() == true) {
                explode();
            } else {
                c.setOpened(true);
                
                //расчет мин вокруг только во время нажатия
                c.setMinesAround(countMinesAround(c));
                
                if (c.getMinesAround() == 0) {

                    // ------------мб можно выпилить
                    int xInitial = c.getX();
                    int yInitial = c.getY();
                    for (int lengthwise = -1; lengthwise <= 1; lengthwise++) {
                        for (int crosswise = -1; crosswise <= 1; crosswise++) {
                            int xNeighbour = xInitial + crosswise;
                            int yNeighbour = yInitial + lengthwise;
                            if (xNeighbour >= 0 && xNeighbour < height
                                    && yNeighbour >= 0
                                    && yNeighbour < width) {
                                if (!field[xNeighbour][yNeighbour]
                                        .isOpened())
                                    recursiveOpen(xNeighbour, yNeighbour);
                            }
                        }
                    }
                    // ---------------
                }
            }

            // проверка на конец игры. если количество закрытых = количеству
            // мин
            if (isOver == 0) {
                int closedCellsNumber = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (field[i][j].isOpened() == false)
                            closedCellsNumber++;
                    }
                }
                if (closedCellsNumber == minesNumber){
                    isOver = 1;
//                    System.out.println("You win!");
                }
            }
            
            // ----------------------------------------------------------------

        }

    }
    
    public void openCell(int x, int y) {
        if (fieldGenerated) {
            recursiveOpen(x, y);
            
            
        } else {
            generateField(x, y);
            recursiveOpen(x, y);
        }
//        view.draw(field);
            
    }

    private void explode() {
//        System.out.println("exploded");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].isMined()) {
                    field[i][j].setOpened(true);
                }
            }
        }

        isOver = -1;
//        view.draw(field);

    }

    public void setFlag(int x, int y) {
        Cell c = field[x][y];
        if (!c.isOpened()) {
            if (c.isFlagged()) {
                c.setFlagged(false);
            } else {
                c.setFlagged(true);
            }
            if (c.isQuestioned())
                c.setQuestioned(false);
        }
//        view.draw(field);
    }

    public void setQuestion(int x, int y) {
        Cell c = field[x][y];
        if (!c.isOpened()) {
            if (c.isQuestioned()) {
                c.setQuestioned(false);
            } else {
                c.setQuestioned(true);
            }
            if (c.isFlagged()) {
                c.setFlagged(false);
            }
        }
//        view.draw(field);
    }

    public int isOver() {
        return isOver;
    }

    // убрать, когда появится полноценный контроллер
    public void consOut() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].isOpened()) {
                    System.out.print(field[i][j].getMinesAround() + " ");
                } else {
                    if (field[i][j].isFlagged()) {
                        System.out.print("& ");
                    }
                    if (field[i][j].isQuestioned()) {
                        System.out.print("? ");
                    }
                    if (!field[i][j].isFlagged()&&!field[i][j].isQuestioned()) {
                        System.out.print("# ");
                    }

                }
            }
            System.out.println();
        }
        System.out.println();
    }

//    public void setView(ViewInterface view) {
//        this.view = view;
//    }

    public Cell[][] getField() {
        return field;
    }

    public void resField() {
        isOver=0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell c = field[i][j];
                c.setFlagged(false);
                c.setQuestioned(false);
                c.setOpened(false);
            }
        }
    }

    public void setNewField(int height, int width, int minesNumber) {
        this.height = height;
        this.width = width;
        this.minesNumber = minesNumber;
        fieldGenerated = false;
        isOver=0;
    }

}
