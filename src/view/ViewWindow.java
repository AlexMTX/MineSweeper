package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Cell;

public class ViewWindow extends ViewAbstract {

    private JButton[][] cellButtonField = new JButton[height][width];

    public ViewWindow(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
    }

    @Override
    public void draw(Cell[][] field) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell modelCell = field[i][j];
//                CellButton viewCell = cellButtonField[i][j];
                JButton viewCell = cellButtonField[i][j];
                
                if (modelCell.isOpened()) {
                    if (modelCell.isMined()) {
                        viewCell.setBackground(Color.RED);
                        viewCell.setText("*");
                    } else {
                        viewCell.setBackground(Color.WHITE);
                        if (modelCell.getMinesAround() != 0) {
                            viewCell.setText(""
                                    + field[i][j].getMinesAround());
                        }
                    }
                } else {
                    if (modelCell.isFlagged()) {
                        viewCell.setBackground(Color.RED);
                    }
                    if (modelCell.isQuestioned()) {
                        viewCell.setBackground(Color.GRAY);
                    }
                    if (!modelCell.isFlagged() && !modelCell.isQuestioned()) {
                        viewCell.setBackground(Color.BLUE);
                    }
                }

            }
        }

    }

    @Override
    public void initialise() {
        JFrame frame = new JFrame("Сапер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        

        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(1,2));
        frame.add(footer, BorderLayout.SOUTH);
        
        JButton newGameButton = createButton("Новая игра");
        newGameButton.addMouseListener(new ButtonMouseListener(newGameButton));
        footer.add(newGameButton);

        JButton resGameButton = createButton ("Перезапустить игру");
        resGameButton.addMouseListener(new ButtonMouseListener(resGameButton));
        footer.add(resGameButton);
        
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);
        
        frame.setVisible(true);

    }
    
    private JButton createButton(String title){
        JButton button = new JButton(title);
        button.setBackground(Color.WHITE);
        button.setRolloverEnabled(false);
        return button;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                final int xButton = i;
                final int yButton = j;
//                final CellButton cellButton = new CellButton(i, j);
                JButton cellButton = new JButton();
                 cellButton.setBackground(new Color(0,0,255,80));
                cellButton.setBackground(Color.BLUE);
//                cellButton.setRolloverEnabled(false);

                //выпилить в абстрактный листнер
                cellButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        x = xButton;
                        y = yButton;
                        button = arg0.getButton();
                        notifyObserver();
                    }
                });
                
                cellButtonField[i][j] = cellButton;
                panel.add(cellButton);
            }
        }
        return panel;
    }

}
