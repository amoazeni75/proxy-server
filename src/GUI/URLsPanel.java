package GUI;

import java.awt.*;


public class URLsPanel extends ScrollablePanel {
    public URLsPanel(Dimension dim) {
        super(dim);
        this.setBackground(Color.CYAN);
        //addTestComponent();
    }
//
//    private void addTestComponent(){
//        ArrayList<ListItem> mm = new ArrayList<>();
//        for (int i = 0; i < 100; i++){
//            ListItem li = new urlItem(new Dimension(screenSize.width, itemH));
//
//            itemsList.add(li);
//            if(i == 0)
//                sp.putConstraint(SpringLayout.NORTH, li, 2, SpringLayout.NORTH, itemsList);
//            else
//                sp.putConstraint(SpringLayout.NORTH, li, 2, SpringLayout.SOUTH, mm.get(mm.size() - 1));
//            sp.putConstraint(SpringLayout.WEST, li, 0, SpringLayout.WEST, itemsList);
//            ((urlItem)li).setUrlText(Integer.toString(i));
//            mm.add(li);
//        }
//        if(mm.size() * itemH > screenSize.height){
//            itemsList.setPreferredSize(new Dimension(screenSize.width, mm.size() * (itemH + 2) + 5));
//        }
//    }
}
