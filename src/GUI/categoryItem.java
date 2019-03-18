/**
 * Author : S.Alireza  Moazeni
 * Student Number : 9423110
 * Project 1 : Proxy Server
 * Web Programming winter_spring 1397_1398
 */
package GUI;

import javax.swing.*;
import java.awt.*;

public class categoryItem extends ListItem {
    public JCheckBox selectChk;
    private JLabel categoryLBL;
    private String categoryName;
    public JButton deleteButton;

    public categoryItem(Dimension dim, String catName) {
        super(dim);
        categoryName = catName;
        setupComponent();
    }
    private void setupComponent(){
        //init component
        selectChk = new JCheckBox();
        categoryLBL = new JLabel();
        deleteButton = new JButton(new ImageIcon("./icons/delete.png"));
        deleteButton.setFocusPainted(false);

        //add to layout
        add(selectChk);
        add(categoryLBL);
        add(deleteButton);

        //define size
        categoryLBL.setPreferredSize(new Dimension((int)(panelDim.width * 0.9),((int)(panelDim.height * 0.9))));
        deleteButton.setPreferredSize(new Dimension(24,24));


        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.addMouseListener(this);

        int yChk = (panelDim.height - selectChk.getPreferredSize().height) / 2;
        int yLbl = (panelDim.height - categoryLBL.getPreferredSize().height) / 2;
        int yDelete = (panelDim.height - deleteButton.getPreferredSize().height) / 2;


        sp.putConstraint(SpringLayout.NORTH, selectChk, yChk, SpringLayout.NORTH,this);
        sp.putConstraint(SpringLayout.NORTH, categoryLBL, yLbl, SpringLayout.NORTH,this);
        sp.putConstraint(SpringLayout.NORTH, deleteButton, yDelete, SpringLayout.NORTH,this);

        sp.putConstraint(SpringLayout.WEST, selectChk, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, categoryLBL, 5, SpringLayout.EAST,selectChk);
        sp.putConstraint(SpringLayout.WEST, deleteButton, -30 - deleteButton.getPreferredSize().width
                , SpringLayout.EAST,this);

        categoryLBL.setText(categoryName);
    }

    public void setLabelText(String s){
        categoryLBL.setText(s);
    }

    public  String getCategoryName(){
        return categoryName;
    }
}
