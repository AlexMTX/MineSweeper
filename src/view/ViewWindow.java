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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Cell;

public class ViewWindow extends ViewAbstract {

//    Timer timer;
//    int time = 0;
    
    private JButton[][] cellButtonField = new JButton[height][width];

    public ViewWindow(int height, int width, int minesNumber) {
        super(height, width, minesNumber);
    }

    @Override
    public void draw(Cell[][] field, int isOver) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell modelCell = field[i][j];
                // CellButton viewCell = cellButtonField[i][j];
                JButton viewCell = cellButtonField[i][j];

                if (modelCell.isOpened()) {
                    viewCell.setEnabled(false);
                    if (modelCell.isMined()) {
                        viewCell.setBackground(Color.RED);
                        viewCell.setText("*");
                    } else {
                        viewCell.setBackground(Color.WHITE);
                        if (modelCell.getMinesAround() != 0) {
                            viewCell.setText("" + field[i][j].getMinesAround());
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

        if (isOver != 0) {
            timer.stop();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    JButton viewCell = cellButtonField[i][j];
                    viewCell.setEnabled(false);
                    // при перезапуске этой же игры придется снова всем
                    // listner'ы приделывать.
                    // мб можно как-то приостановить и потом этот же снова
                    // активировать.
                    viewCell.removeMouseListener(viewCell.getMouseListeners()[1]);
                }
            }

            JFrame overFrame = new JFrame();
            overFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            overFrame.setSize(200, 100);
            overFrame.setLayout(new BorderLayout());
            
            JLabel overLable = new JLabel();
            overFrame.add(overLable,BorderLayout.CENTER);

            if (isOver == -1) {
                overFrame.setTitle("Game Over");
                overLable.setText("   Вы проиграли...    Время: "+time);
            }

            if (isOver == 1) {
                overFrame.setTitle("You win!");
                overLable.setText("   Вы выиграли!     Время: "+time);
            }

            overFrame.setVisible(true);
        }

    }

    @Override
    public void initialise() {
        final JFrame frame = new JFrame("Сапер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int oneCellSize = 45; // в константы
        int windowWidth = width * oneCellSize;
        int windowHeight = height * oneCellSize;
        frame.setSize(windowWidth > 300 ? windowWidth : 300,
                windowHeight > 300 ? windowHeight : 300);
        // frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(1, 3));
        frame.add(footer, BorderLayout.SOUTH);

        JButton newGameButton = createButton("Новая игра");
        newGameButton.addMouseListener(new MouseListenerAbstract() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
//                newGameButton.removeMouseListener(newGameButton.getMouseListeners()[1]);
//                timer.stop();
//                timer = null;
//                time = 0;
                frame.setVisible(false);
                newGame();
            }
        });
        footer.add(newGameButton);

        JButton resGameButton = createButton("Перезапустить игру");
        resGameButton.addMouseListener(new MouseListenerAbstract() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                timer.stop();
                time = 0;
                frame.setVisible(false);
                resGame();
            }
        });
        footer.add(resGameButton);

        final JButton timerButton = new JButton("0");
        timerButton.setBackground(Color.WHITE);
        timerButton.setEnabled(false);
        footer.add(timerButton);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                time++;
                timerButton.setText("" + time);
//                System.out.println("window timer "+timer);
            }
        });
        
//        System.out.println("window timer inited" + timer);
        
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);

        timer.start();
        frame.setVisible(true);

    }

    private JButton createButton(String title) {
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
                // final CellButton cellButton = new CellButton(i, j);
                JButton cellButton = new JButton();
                // cellButton.setBackground(new Color(0, 0, 255, 80));
                cellButton.setBackground(Color.BLUE);
                // cellButton.setRolloverEnabled(false);

                cellButton.addMouseListener(new MouseListenerAbstract() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        x = xButton;
                        y = yButton;
                        button = arg0.getButton();
                        notifyObserver();
                    }
                });
                /*
                // выпилить в абстрактный листнер
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
                        
                    }
                });
                */
                cellButtonField[i][j] = cellButton;
                panel.add(cellButton);
            }
        }
        return panel;
    }

}
