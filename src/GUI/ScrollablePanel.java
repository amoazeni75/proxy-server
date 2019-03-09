package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScrollablePanel extends JPanel {
    protected JScrollPane pane;
    protected ListItemPanel itemsList;
    protected SpringLayout sp;
    protected Dimension screenSize;
    protected int itemH;

    public ScrollablePanel(){
        this(new Dimension(100,100));
    }

    public ScrollablePanel(Dimension dim){
        super(new BorderLayout());
        screenSize = dim;
        setPreferredSize(dim);
        itemsList = new ListItemPanel(dim);
        add(itemsList,BorderLayout.CENTER);
        pane = new JScrollPane(itemsList);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(pane,BorderLayout.CENTER);
        itemH = 50;
        sp = itemsList.sp;
    }
}
