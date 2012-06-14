package test;

import junit.framework.Assert;
import model.Model;

import org.junit.Test;

import service.Service;

public class TestOpenCell {
    
    Model model;
    Service service;
    
    /* Открыть ячейку. openCell(int x, int y, Model model)
     * Если поле не сгенерировано, то сгенерировать, и дальше рекурсивно открыть ячейку.
     * Тест на генерацию перед открытием.
     */
    @Test
    public void openCell() {
        model = new Model (5,5,3);
        service = new Service();
        
        service.openCell(0, 1, model);
        
        Assert.assertEquals(true, model.isFieldGenerated());
    }
}
