package test;

import junit.framework.Assert;

import org.junit.Test;

import model.Cell;
import model.Model;
import service.Service;

public class TestMark {

    Model model;
    Service service;
    
    /* Поставить флаг. setFlag(int x, int y, Model model)
     * Флаг на открытую ячейку. Ничего не должно произойти.
     * Флаг на закрытую пустую ячейку. Поставится.
     * Флаг на закрытую с флагом. Снимется.
     * Флаг на закрытую с вопросом. Поставится флаг, снимется вопрос.
     * открытая закрытая флаг вопрос
     *    _        #      P      ?
     */
    @Test
    public void setFlag() {
        Cell[][] field = new Cell[1][4];
        
        for (int i = 0; i < 4; i++) {
                Cell c = new Cell();
                c.setX(0);
                c.setY(i);
                field[0][i] = c;
        }
        
        service = new Service();
        
        model = new Model(1, 4, 0);
        model.setField(field);
        model.setFieldGenerated(true);
        
        field[0][0].setOpened(true);
        field[0][2].setFlagged(true);
        field[0][3].setQuestioned(true);
        
        for (int i = 0; i < 4; i++) {
            service.setFlag(0, i, model);
        }
        
        Assert.assertEquals(true, field[0][0].isOpened());
        Assert.assertEquals(false, field[0][0].isFlagged());
        Assert.assertEquals(false, field[0][0].isQuestioned());
        
        Assert.assertEquals(false, field[0][1].isOpened());
        Assert.assertEquals(true, field[0][1].isFlagged());
        Assert.assertEquals(false, field[0][1].isQuestioned());
        
        Assert.assertEquals(false, field[0][2].isOpened());
        Assert.assertEquals(false, field[0][2].isFlagged());
        Assert.assertEquals(false, field[0][2].isQuestioned());
        
        Assert.assertEquals(false, field[0][3].isOpened());
        Assert.assertEquals(true, field[0][3].isFlagged());
        Assert.assertEquals(false, field[0][3].isQuestioned());
    }
    
    /* Поставить вопрос. setQuestion(int x, int y, Model model)
     * Логика аналогична setFlag.
     */
    @Test
    public void setQuestion() {
        Cell[][] field = new Cell[1][4];
        
        for (int i = 0; i < 4; i++) {
                Cell c = new Cell();
                c.setX(0);
                c.setY(i);
                field[0][i] = c;
        }
        
        service = new Service();
        
        model = new Model(1, 4, 0);
        model.setField(field);
        model.setFieldGenerated(true);
        
        field[0][0].setOpened(true);
        field[0][2].setFlagged(true);
        field[0][3].setQuestioned(true);
        
        for (int i = 0; i < 4; i++) {
            service.setQuestion(0, i, model);
        }
        
        Assert.assertEquals(true, field[0][0].isOpened());
        Assert.assertEquals(false, field[0][0].isFlagged());
        Assert.assertEquals(false, field[0][0].isQuestioned());
        
        Assert.assertEquals(false, field[0][1].isOpened());
        Assert.assertEquals(false, field[0][1].isFlagged());
        Assert.assertEquals(true, field[0][1].isQuestioned());
        
        Assert.assertEquals(false, field[0][2].isOpened());
        Assert.assertEquals(false, field[0][2].isFlagged());
        Assert.assertEquals(true, field[0][2].isQuestioned());
        
        Assert.assertEquals(false, field[0][3].isOpened());
        Assert.assertEquals(false, field[0][3].isFlagged());
        Assert.assertEquals(false, field[0][3].isQuestioned());
    }
    
}
