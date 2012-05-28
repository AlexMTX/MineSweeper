package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Frame(){
        super("Сапер");
        setLayout(new BorderLayout());
        
        JButton button = new JButton("Новая игра");
        button.setBackground(Color.BLUE);
        button.setRolloverEnabled(false);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                button.setText("Приплыли");
//            }
//        });
        
        button.addMouseListener(new ButtonMouseListener(button));
        
        
        add(button, BorderLayout.NORTH);
        
//        JPanel panel = createBorard();
//        add(panel, BorderLayout.CENTER);
    }

//    private JPanel createBorard() {
//        // TODO Auto-generated method stub
//        return null;
//    }

}
