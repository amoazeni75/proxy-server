/**
 * Author : S.Alireza  Moazeni
 * Student Number : 9423110
 * Project 1 : Proxy Server
 * Web Programming winter_spring 1397_1398
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ListItem extends JPanel implements MouseListener {

    protected Dimension panelDim;
    protected SpringLayout sp;

    public ListItem(){
        this(new Dimension(100,50));
    }
    public ListItem(Dimension dim){
        super();
        this.setPreferredSize(dim);
        panelDim = dim;
        sp = new SpringLayout();
        setLayout(sp);
        addMouseListener(this);
        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
    }

    private void setupComponent(){}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
