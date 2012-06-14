package test;

import junit.framework.Assert;
import model.Model;

import org.junit.Test;

import service.Service;


public class TestGenerateField {
    
    Model model;
    Service service;
    
    /* Генерация поля. generateField(int xOpened, int yOpened, Model model)
     * Открытая ячейка должна быть не миной.
     * Размеры и количетсво мин должны совпадать с заданными в модели.
     * Каждой ячейке должны быть присвоены координаты x, y по положению в массиве.
     * После генерации в модели должен быть установлен флажок isFieldGenerated на true.
     */
    @Test
    public void generateField() {
        model = new Model(3, 4, 2);
        service = new Service();
        
        service.generateField(0, 0, model);
        
        Assert.assertEquals(false, model.getField()[0][0].isMined());
        
        Assert.assertEquals(3, model.getHeight());
        Assert.assertEquals(4, model.getWidth());
        Assert.assertEquals(2, model.getMinesNumber());
        
        Assert.assertEquals(0, model.getField()[0][1].getX());
        Assert.assertEquals(1, model.getField()[0][1].getY());
        
        Assert.assertEquals(true, model.isFieldGenerated());
    }
}
