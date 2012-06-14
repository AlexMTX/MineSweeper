package test;

import junit.framework.Assert;

import org.junit.Test;

import model.Cell;
import model.Const;
import model.Model;
import service.Service;

public class TestRecursiveOpen {
    Model model;
    Service service;
    
    /* Открыть заминированню ячейку.
     * В поле размером 2х2 в первую строку положим 2 мины, нижние 2 ячейки пусты.
     * Откроем мину в ячейке [0][0].
     * При открытии мины состояние игры (gameStatus) в модели должно стать "проигранным".
     * Обе мины должны стать открытыми (isOpened) для отображения игроку всех ячеек 
     * с минами -  тест explode(Model model).
     * Две нижние мины остаются закрытыми.
     */
    @Test
    public void openMinedCell() {
        
        Cell[][] field = new Cell[2][2];
        
        for (int i = 0; i < 2; i++) {
            Cell c = new Cell();
            c.setX(0);
            c.setY(i);
            c.setMined(true);
            field[0][i]=c;
        }
        
        for (int i = 0; i < 2; i++) {
            Cell c = new Cell();
            c.setX(1);
            c.setY(i);
            field[1][i]=c;
        }
        
        model = new Model(2, 2, 1);
        model.setField(field);
        model.setFieldGenerated(true);
        
        service = new Service();
        service.recursiveOpen(0, 0, model);
        
        Assert.assertEquals(Const.LOSE_GAME, model.gameStatus());
        Assert.assertEquals(true, model.getField()[0][0].isOpened());
        Assert.assertEquals(true, model.getField()[0][1].isOpened());
        Assert.assertEquals(false, model.getField()[1][0].isOpened());
        Assert.assertEquals(false, model.getField()[1][1].isOpened());
    }
    
    
    /* Открыть ячейку, вокруг которой есть мина.
     * Должна открыться только эта ячейка. Для нее должно быть рассчитано число мин вокруг.
     * На примере:       
     * x\y| 0 1 2 3 4 
     *  _____________
     *  0 |           
     *  1 |   1 1 1   
     *  2 |   1 * 2 1 
     *  3 |   2 3 * 1 
     *  4 |   1 * 2 1 
     */
    @Test
    public void openCellNearMinedCell() {
        Cell[][] field = new Cell[5][5];
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Cell c = new Cell();
                c.setX(i);
                c.setY(j);
                field[i][j] = c;
            }
        }
        
        service = new Service();

        field[2][2].setMined(true);
        field[3][3].setMined(true);
        field[4][2].setMined(true);
        model = new Model(5, 5, 3);
        model.setField(field);
        model.setFieldGenerated(true);
        
        service.recursiveOpen(3, 1, model);
        service.recursiveOpen(3, 4, model);
        service.recursiveOpen(4, 4, model);
        
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals(false, field[0][i].isOpened());
            }
        }
        Assert.assertEquals(false, field[3][0].isOpened());
        Assert.assertEquals(true, field[3][1].isOpened());
        Assert.assertEquals(false, field[3][3].isOpened());
        Assert.assertEquals(true, field[3][4].isOpened());
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(false, field[4][i].isOpened());
        }
        Assert.assertEquals(true, field[4][4].isOpened());
    }
    
    /* Открыть пустую ячейку, вокруг которой нет мин.
     * Должны рекурсивно открыться все пустые ячейки и ячейки, 
     * вокруг которых есть мины, граничащие с пустыми ячейками.
     * x\y| 0 1 2 3 4 
     *  _____________
     *  0 |           
     *  1 |           
     *  2 |     1 1 1 
     *  3 |   1 2 * 1 
     *  4 |   1 * 2 1  
     */
    @Test
    public void openEmptyCell() {
        Cell[][] field = new Cell[5][5];
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Cell c = new Cell();
                c.setX(i);
                c.setY(j);
                field[i][j] = c;
            }
        }
        
        service = new Service();

        field[3][3].setMined(true);
        field[4][2].setMined(true);
        model = new Model(5, 5, 2);
        model.setField(field);
        model.setFieldGenerated(true);
        
        service.recursiveOpen(1, 1, model);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                Assert.assertEquals(true, field[i][j].isOpened());
            }
        }
        Assert.assertEquals(true, field[3][0].isOpened());
        Assert.assertEquals(true, field[3][1].isOpened());
        Assert.assertEquals(true, field[3][2].isOpened());
        
        Assert.assertEquals(false, field[3][3].isOpened());
        Assert.assertEquals(false, field[3][4].isOpened());
        
        Assert.assertEquals(true, field[4][0].isOpened());
        Assert.assertEquals(true, field[4][1].isOpened());
        
        Assert.assertEquals(false, field[4][2].isOpened());
        Assert.assertEquals(false, field[4][3].isOpened());
        Assert.assertEquals(false, field[4][4].isOpened());
    }
}
