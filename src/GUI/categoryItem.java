package GUI;

import javax.swing.*;
import java.awt.*;

public class categoryItem extends ListItem {
    private JCheckBox selectChk;
    private JLabel categoryLBL;

    public categoryItem(Dimension dim) {
        super(dim);
        setupComponent();
    }
    private void setupComponent(){
        //init component
        selectChk = new JCheckBox();
        categoryLBL = new JLabel();

        //add to layout
        add(selectChk);
        add(categoryLBL);

        //define size
        categoryLBL.setPreferredSize(new Dimension((int)(panelDim.width * 0.9),((int)(panelDim.height * 0.9))));

        int yChk = (panelDim.height - selectChk.getPreferredSize().height) / 2;
        int yLbl = (panelDim.height - categoryLBL.getPreferredSize().height) / 2;

        sp.putConstraint(SpringLayout.NORTH, selectChk, yChk, SpringLayout.NORTH,this);
        sp.putConstraint(SpringLayout.NORTH, categoryLBL, yLbl, SpringLayout.NORTH,this);

        sp.putConstraint(SpringLayout.WEST, selectChk, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, categoryLBL, 5, SpringLayout.EAST,selectChk);

    }

    public void setLabelText(String s){
        categoryLBL.setText(s);
    }
}
