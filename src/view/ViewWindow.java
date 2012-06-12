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

    private CellButton[][] cellButtonField = new CellButton[height][width];

    public ViewWindow(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
    }

    @Override
    public void draw(Cell[][] field) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell modelCell = field[i][j];
                CellButton viewCell = cellButtonField[i][j];
                
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

        frame.setVisible(true);

        JButton newGameButton = new JButton("Новая игра");
        // newGameButton.setBackground(Color.WHITE);
        newGameButton.setRolloverEnabled(false);
        newGameButton.addMouseListener(new ButtonMouseListener(newGameButton));
        frame.add(newGameButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                final CellButton cellButton = new CellButton(i, j);
                
//                 cellButton.setBackground(new Color(0,0,255,80));
                cellButton.setBackground(Color.BLUE);
                cellButton.setRolloverEnabled(false);

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
//                        System.out.println("x: " + cellButton.getX() + " y: "
//                                + cellButton.getY() + " b: " + arg0.getButton());
                        // cellButton.setVisible(false);
                        
                        x = cellButton.getX();
                        y = cellButton.getY();
                        button = arg0.getButton();
                        notifyObserver();
                    }
                });
                
                cellButtonField[i][j] = cellButton;
                cellButton.setVisible(true);
                panel.add(cellButton, BorderLayout.CENTER);
            }
        }

        frame.add(panel, BorderLayout.CENTER);

    }

}
