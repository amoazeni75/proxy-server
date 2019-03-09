package GUI;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanel extends JPanel {
    private JScrollPane pane;
    private ListItemPanel itemsList;

    public ScrollablePanel(){
        this(new Dimension(100,100));
    }
    public ScrollablePanel(Dimension dim){
        super(new BorderLayout());
        setPreferredSize(dim);
        itemsList = new ListItemPanel(dim);
        add(itemsList,BorderLayout.CENTER);
        pane = new JScrollPane(itemsList);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(pane,BorderLayout.CENTER);
    }

}
