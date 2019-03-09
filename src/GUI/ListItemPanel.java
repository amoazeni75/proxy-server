package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListItemPanel extends JPanel {

    protected SpringLayout sp;
    protected Dimension screenSize;


    public ListItemPanel(Dimension dim){
        super();
        sp = new SpringLayout();
        screenSize = dim;
        setLayout(sp);
        setPreferredSize(dim);
        setOpaque(false);
    }
}
