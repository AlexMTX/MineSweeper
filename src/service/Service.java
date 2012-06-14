package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//import model.Model;
import model.Cell;
import  model.Const;
import model.Model;

import view.ViewAbstract;
import view.ViewConsole;
import view.ViewWindow;

public class Service implements Observer{
    
    private Model model;
    private ViewAbstract view;

    @Override
    public void clickModelCell(int x, int y, int button) {
        
        switch (button) {
        case 1:
            openCell(x, y, model);
            break;
            
        case 2:
            //средняя кнопка
            break;
            
        case 3: 
            Cell modelCell = model.getField()[x][y];
            if (modelCell.isFlagged()) {
                setQuestion(x, y, model);
                break;
            }
            if (modelCell.isQuestioned()) {
                setQuestion(x, y, model);
                break;
            }
            if (!modelCell.isFlagged()&&!modelCell.isQuestioned()) {
                setFlag(x, y, model);
                break;
            }
            
        default:
            break;
        }
        
        view.draw(model.getField(), model.gameStatus());
        
    }

    @Override
    public void resGame() {
        resField(model);
        view.initialise();
    }

    @Override
    public void newGame() {
        Scanner sc = new Scanner(System.in);

//      System.out.println("Высота поля:");
      System.out.println("Field's height:");
      int height = sc.nextInt();

//      System.out.println("Ширина поля:");
      System.out.println("Field's width:");
      int width = sc.nextInt();

//      System.out.println("Количество мин:");
      System.out.println("Mines number:");
      int minesNumber = sc.nextInt();

//      System.out.println("Тип отображения: 1 - Консоль; 2 - Окно");
      System.out.println("View in: 1 - Console; 2 - Window");
      int viewType = sc.nextInt();

      model = new Model(height, width, minesNumber);

      switch (viewType) {
      case 1:
          view = new ViewConsole(height, width, minesNumber);
          break;

      case 2:
          view = new ViewWindow(height, width, minesNumber);
          break;
      }
      
      view.registerObserver(this);
      view.initialise();
    }

    //---------------------------------- Логика работы с моделью -----------------------------------------------
    
    public void generateField(int xOpened, int yOpened, Model model) {
        
        List<Cell> fieldList = new ArrayList<Cell>();
        for (int i = 0; i < model.getMinesNumber(); i++) {
            Cell c = new Cell();
            c.setMined(true);
            fieldList.add(c);
        }
        
        for (int i = 0; i < model.getHeight() * model.getWidth() - model.getMinesNumber() - 1; i++) {
            Cell c = new Cell();
            c.setMined(false);
            fieldList.add(c);
        }
        
        Collections.shuffle(fieldList);
        
        Iterator<Cell> fieldListIterator = fieldList.iterator();
        model.setField(new Cell[model.getHeight()][model.getWidth()]);
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                Cell c;
                if (i == xOpened && j == yOpened) {
                    c = new Cell();
                    c.setMined(false);
                } else {
                    c = fieldListIterator.next();
                }
                c.setX(i);
                c.setY(j);
                model.getField()[i][j] = c;
            }
        }
        model.setFieldGenerated(true);
    }
    
    public int countMinesAround(Cell c, Model model) {
        int minesNumber = 0;
        int xInitial = c.getX();
        int yInitial = c.getY();
        for (int lengthwise = -1; lengthwise <= 1; lengthwise++) {
            for (int crosswise = -1; crosswise <= 1; crosswise++) {
                int xNeighbour = xInitial + crosswise;
                int yNeighbour = yInitial + lengthwise;
                if (xNeighbour >= 0 && xNeighbour < model.getHeight() && yNeighbour >= 0
                        && yNeighbour < model.getWidth()) {
                    Cell neighbourCell = model.getField()[xNeighbour][yNeighbour];
                    if (neighbourCell.isMined() == true) {
                        minesNumber++;
                    }
                }
            }
        }
        return minesNumber;
    }
    
    public void recursiveOpen(int x, int y, Model model) {
        Cell c = model.getField()[x][y];
        if (!c.isFlagged()) {
            if (c.isMined() == true) {
                explode(model);
            } else {
                c.setOpened(true);
                c.setMinesAround(countMinesAround(c, model));
                if (c.getMinesAround() == 0) {
                    int xInitial = c.getX();
                    int yInitial = c.getY();
                    for (int lengthwise = -1; lengthwise <= 1; lengthwise++) {
                        for (int crosswise = -1; crosswise <= 1; crosswise++) {
                            int xNeighbour = xInitial + crosswise;
                            int yNeighbour = yInitial + lengthwise;
                            if (xNeighbour >= 0 && xNeighbour < model.getHeight() && 
                                    yNeighbour >= 0 && yNeighbour < model.getWidth()) {
                                if (!model.getField()[xNeighbour][yNeighbour].isOpened())
                                    recursiveOpen(xNeighbour, yNeighbour, model);
                            }
                        }
                    }
                }
            }
            if (model.gameStatus() == Const.PLAYING_GAME) {
                int closedCellsNumber = 0;
                for (int i = 0; i < model.getHeight(); i++) {
                    for (int j = 0; j < model.getWidth(); j++) {
                        if (model.getField()[i][j].isOpened() == false)
                            closedCellsNumber++;
                    }
                }
                if (closedCellsNumber == model.getMinesNumber()){
                    model.setIsOver(Const.WIN_GAME);
                }
            }
        }
    }
    
    public void openCell(int x, int y, Model model) {
        if (model.isFieldGenerated()) {
            recursiveOpen(x, y, model);
        } else {
            generateField(x, y, model);
            recursiveOpen(x, y, model);
        }
    }
    
    public void explode(Model model) {
      for (int i = 0; i < model.getHeight(); i++) {
          for (int j = 0; j < model.getWidth(); j++) {
              Cell c = model.getField()[i][j];
              if (c.isMined()) {
                  c.setOpened(true);
              }
          }
      }
      model.setIsOver(Const.LOSE_GAME);
  }
    
    public void setFlag(int x, int y, Model model) {
        Cell c = model.getField()[x][y];
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
    
    public void setQuestion(int x, int y, Model model) {
        Cell c = model.getField()[x][y];
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
    
    public void resField(Model model) {
        model.setIsOver(Const.PLAYING_GAME);
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                Cell c = model.getField()[i][j];
                c.setFlagged(false);
                c.setQuestioned(false);
                c.setOpened(false);
            }
        }
    }
  //================================== Логика работы с моделью =================================================
}
