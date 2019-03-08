package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ListItemPanel extends JPanel {

    private SpringLayout sp;
    private Border emptyBorder;
    private Border selectBorder;
    private int currentSelectedPanel;

    public ListItemPanel(Dimension dim){
        super();
        sp = new SpringLayout();
        setLayout(sp);
        setPreferredSize(dim);
        setOpaque(false);
        currentSelectedPanel = -1;
        emptyBorder = BorderFactory.createEmptyBorder(1,1,1,1);
        selectBorder = BorderFactory.createLineBorder(Color.blue);
    }

}
