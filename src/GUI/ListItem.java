package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListItem extends JPanel implements MouseListener {
    private JCheckBox selectChk;
    private JTextField urlText;
    private int textWidth;
    private JButton deleteBTN;
    private Dimension panelDim;
    private SpringLayout sp;

    public ListItem(Dimension dim){
        super();
        this.setPreferredSize(dim);
        panelDim = dim;
        textWidth = (int)(dim.width * 0.75);
        sp = new SpringLayout();
        setLayout(sp);
        addMouseListener(this);
        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
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
