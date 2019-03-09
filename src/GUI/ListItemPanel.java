package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ListItemPanel extends JPanel {

    private SpringLayout sp;
    private Border emptyBorder;
    private Border selectBorder;
    private int currentSelectedPanel;
    private Dimension screenSize;
    private int itemH;

    public ListItemPanel(Dimension dim){
        super();
        sp = new SpringLayout();
        screenSize = dim;
        setLayout(sp);
        setPreferredSize(dim);
        setOpaque(false);
        currentSelectedPanel = -1;
        emptyBorder = BorderFactory.createEmptyBorder(1,1,1,1);
        selectBorder = BorderFactory.createLineBorder(Color.blue);
        itemH = 50;
        addTestComponent();
    }
    private void addTestComponent(){
        ArrayList<ListItem> mm = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            ListItem li = new ListItem(new Dimension(screenSize.width, itemH));

            add(li);
            if(i == 0)
                sp.putConstraint(SpringLayout.NORTH, li, 2, SpringLayout.NORTH, this);
            else
                sp.putConstraint(SpringLayout.NORTH, li, 2, SpringLayout.SOUTH, mm.get(mm.size() - 1));
            sp.putConstraint(SpringLayout.WEST, li, 0, SpringLayout.WEST, this);
            li.setUrlText(Integer.toString(i));
            mm.add(li);
        }
        if(mm.size() * itemH > screenSize.height){
            setPreferredSize(new Dimension(screenSize.width, mm.size() * (itemH + 2) + 5));
        }
    }

}
