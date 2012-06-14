package test;

import junit.framework.Assert;

import org.junit.Test;

import model.Cell;
import model.Model;
import service.Service;

public class TestResField {
    
    Model model;
    Service service;
    
    /* Сброс поля в исходное состояние, без изменения положения мин. resField(Model model)
     * Закрываем открытые ячейки, снимаем флажки и вопросы с закрытых.
     * 
     * x\y| 0 1 2 3 4 
     *  _____________
     *  0 |           
     *  1 |           
     *  2 |     1 1 1 
     *  3 |   1 2 * 1 
     *  4 |   1 * 2 1 
     */
    @Test
    public void resField() {
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
        model.getField()[3][3].setFlagged(true);
        model.getField()[3][4].setQuestioned(true);
        
        service.resField(model);
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assert.assertEquals(false, model.getField()[i][j].isOpened());
                Assert.assertEquals(false, model.getField()[i][j].isFlagged());
                Assert.assertEquals(false, model.getField()[i][j].isQuestioned());
            }
        }

    }

}
