package GUI;

import javax.swing.*;
import java.awt.*;

public class urlItem extends ListItem {
    private JCheckBox selectChk;
    private JTextField urlText;
    private int textWidth;
    private JButton deleteBTN;

    public urlItem(Dimension dim) {
        super(dim);
        textWidth = (int)(dim.width * 0.75);
        setupComponent();
    }

    private void setupComponent(){
        //init component
        selectChk = new JCheckBox();
        urlText = new JTextField();
        deleteBTN = new JButton(new ImageIcon("./icons/delete.png"));

        //add to layout
        add(selectChk);
        add(urlText);
        add(deleteBTN);

        //define size
        deleteBTN.setPreferredSize(new Dimension(24,24));
        urlText.setPreferredSize(new Dimension(textWidth, urlText.getPreferredSize().height));

        deleteBTN.setContentAreaFilled(false);
        deleteBTN.setBorderPainted(false);
        deleteBTN.setOpaque(false);
        deleteBTN.addMouseListener(this);

        int yChk = (panelDim.height - selectChk.getPreferredSize().height) / 2;
        int yText = (panelDim.height - urlText.getPreferredSize().height) / 2;
        int yDelete = (panelDim.height - deleteBTN.getPreferredSize().height) / 2;

        sp.putConstraint(SpringLayout.NORTH, selectChk, yChk, SpringLayout.NORTH,this);
        sp.putConstraint(SpringLayout.NORTH, urlText, yText, SpringLayout.NORTH,this);
        sp.putConstraint(SpringLayout.NORTH, deleteBTN, yDelete, SpringLayout.NORTH,this);

        sp.putConstraint(SpringLayout.WEST, selectChk, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, urlText, 5, SpringLayout.EAST,selectChk);
        sp.putConstraint(SpringLayout.WEST, deleteBTN, -30 - deleteBTN.getPreferredSize().width
                , SpringLayout.EAST,this);

    }

    public void setUrlText(String txt){
        urlText.setText(txt);
    }
}
