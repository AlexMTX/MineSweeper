package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Model {
    private final int height;
    private final int width;
    private final int minesNumber;

    private int isOver = 0;
    private boolean fieldGenerated = false;

    private Cell[][] field;

    public Model(int height, int width, int minesNumber) {
        this.height = height;
        this.width = width;
        this.minesNumber = minesNumber;

    }

    /* генерирует минное поле. клетка, на которую нажали всегда будет не миной */
    // private
    public void generateField(int xWhereOpened, int yWhereOpened) {

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

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell c = field[i][j];
                if (!c.isMined())
                    c.setMinesAround(countMinesAround(c));
            }
        }

        fieldGenerated = true;

        /*
         * // -------------------- test for (int i = 0; i < height; i++) { for
         * (int j = 0; j < width; j++) { if (field[i][j].isMined()) {
         * System.out.print("* "); } else {
         * System.out.print(field[i][j].getMinesAround() + " "); } }
         * System.out.println(); } System.out.println(); // --------------------
         */
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

    public void openCell(int x, int y) {
        if (fieldGenerated) {

            Cell c = field[x][y];
            if (!c.isFlagged()) {

                if (c.isMined() == true) {
                    explode();
                } else {
                    c.setOpened(true);
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
                                        openCell(xNeighbour, yNeighbour);
                                }
                            }
                        }
                        // ---------------
                    }
                }

                // проверка на конец игры. если количество закрытых = количеству
                // мин
                int closedCellsNumber = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (field[i][j].isOpened() == false)
                            closedCellsNumber++;
                    }
                }
                if (closedCellsNumber == minesNumber){
                    isOver = 1;
                    System.out.println("You win!");
                }
                   
                // ----------------------------------------------------------------

            }

        } else {
            generateField(x, y);
            openCell(x, y);
        }
        /*
         * // -------------------- test for (int i = 0; i < height; i++) { for
         * (int j = 0; j < width; j++) { if (field[i][j].isOpened()) {
         * System.out.print(field[i][j].getMinesAround() + " "); } else {
         * System.out.print("# "); } } System.out.println(); }
         * System.out.println(); // --------------------
         */
    }

    private void explode() {
        System.out.println("exploded");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].isMined()) {
                    field[i][j].setOpened(true);
                }
            }
        }

        isOver = -1;

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

}
