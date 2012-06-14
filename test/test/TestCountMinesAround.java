package test;

import junit.framework.Assert;
import model.Cell;
import model.Model;

import org.junit.Test;

import service.Service;

public class TestCountMinesAround {
    Model model;
    Service service;
    
    /* Посчитать мины вокруг данной ячейки. countMinesAround(Cell c, Model model)
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
    public void countMinesAroundCell() {
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
        model = new Model(5, 5, 1);
        model.setField(field);
        model.setFieldGenerated(true);
        
        Cell c = model.getField()[1][1];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[1][2];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[1][3];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[1][4];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(0, c.getMinesAround());
        
        c = model.getField()[2][1];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[2][3];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(2, c.getMinesAround());
        
        c = model.getField()[2][4];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[3][1];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(2, c.getMinesAround());
        
        c = model.getField()[3][2];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(3, c.getMinesAround());
        
        c = model.getField()[3][4];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[4][1];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
        
        c = model.getField()[4][3];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(2, c.getMinesAround());
        
        c = model.getField()[4][4];
        c.setMinesAround(service.countMinesAround(c, model));
        Assert.assertEquals(1, c.getMinesAround());
    }

}
