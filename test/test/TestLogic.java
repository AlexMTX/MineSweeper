package test;

import static org.junit.Assert.*;
import junit.framework.Assert;
import model.Cell;
import model.Model;

import org.junit.Test;

import service.Service;


public class TestLogic {

    @Test
    public void openMinedCell() {
        
        Cell[][] field = new Cell[2][2];
        Cell c00 = new Cell();
        c00.setX(0);
        c00.setY(0);
        c00.setMined(true);
        field[0][0]=c00;
        
        Cell c01 = new Cell();
        c01.setX(0);
        c01.setY(1);
        field[0][1]=c01;
        
        for (int i = 0; i < 2; i++) {
            Cell c = new Cell();
            c.setX(1);
            c.setY(i);
            field[1][i]=c;
        }
        
        Model model = new Model(2, 2, 1);
        model.setField(field);
        model.setFieldGenerated(true);
        
        Service service = new Service();
        service.openCell(0, 0, model);
        
        Assert.assertEquals(-1, model.isOver());
        
    }
    
    
}
