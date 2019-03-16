package GUI;

import Models.Backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddNewUrlPanel extends JFrame implements MouseListener {

    private JComboBox<String>  categoryCombo;
    private JTextField urlTxt;
    private JButton okBtn;
    private JButton cancelBtn;
    private JLabel urlLbl;
    private JLabel categoryLbl;
    private SpringLayout sp;
    private Backend backend;

    public AddNewUrlPanel(Backend back) {
        super("Add New URL");
        sp = new SpringLayout();
        setLayout(sp);
        backend = back;
        setPreferredSize(new Dimension(400, 200));
        initComponent();
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponent() {
        okBtn = new JButton(new ImageIcon("./icons/ok.png"));
        cancelBtn = new JButton(new ImageIcon("./icons/cancel.png"));
        urlTxt = new JTextField("http://");
        urlLbl = new JLabel();
        categoryLbl = new JLabel();
        categoryCombo = new JComboBox();

        //
        add(okBtn);
        add(cancelBtn);
        add(urlTxt);
        add(urlLbl);
        add(categoryLbl);
        add(categoryCombo);

        okBtn.setBorderPainted(false);
        cancelBtn.setBorderPainted(false);
        okBtn.setFocusPainted(false);
        cancelBtn.setFocusPainted(false);


        okBtn.setPreferredSize(new Dimension(64, 64));
        cancelBtn.setPreferredSize(new Dimension(64, 64));
        urlLbl.setPreferredSize(new Dimension(32, 32));
        categoryLbl.setPreferredSize(new Dimension(32, 32));
        urlTxt.setPreferredSize(new Dimension(300, 20));
        categoryCombo.setPreferredSize(new Dimension(100, categoryCombo.getPreferredSize().height));
        okBtn.addMouseListener(this);
        cancelBtn.addMouseListener(this);

        okBtn.setContentAreaFilled(false);
        cancelBtn.setContentAreaFilled(false);

        //
        urlLbl.setIcon(new ImageIcon("./icons/www.png"));
        categoryLbl.setIcon(new ImageIcon("./icons/list.png"));

        //
        for (int i = 0; i < backend.categories.size(); ++i)
            categoryCombo.addItem(((categoryItem)(backend.categories.get(i))).getCategoryName());

        //
        sp.putConstraint(SpringLayout.NORTH, okBtn, -1 *okBtn.getPreferredSize().height - 35, SpringLayout.SOUTH, this);
        sp.putConstraint(SpringLayout.NORTH, cancelBtn, -35 - cancelBtn.getPreferredSize().height, SpringLayout.SOUTH, this);
        sp.putConstraint(SpringLayout.WEST, cancelBtn, -10 - cancelBtn.getPreferredSize().width, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.WEST, okBtn, -10 - okBtn.getPreferredSize().width, SpringLayout.WEST, cancelBtn);

        sp.putConstraint(SpringLayout.NORTH, urlLbl, 30, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.NORTH, urlTxt, 7, SpringLayout.NORTH, urlLbl);
        sp.putConstraint(SpringLayout.WEST, urlLbl, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, urlTxt, 10, SpringLayout.EAST, urlLbl);

        sp.putConstraint(SpringLayout.NORTH, categoryLbl, 30, SpringLayout.SOUTH, urlLbl);
        sp.putConstraint(SpringLayout.NORTH, categoryCombo, 6, SpringLayout.NORTH, categoryLbl);
        sp.putConstraint(SpringLayout.WEST, categoryLbl, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, categoryCombo, 10, SpringLayout.EAST, categoryLbl);
    }

    public void checkEnteredURL(){
        boolean hasErr = false;
        String text = urlTxt.getText();
        if(text.equals(""))
            hasErr = true;
        if(!hasErr){
            if(text.contains("http://")){
                if (text.length() <= 7)
                    hasErr = true;
            }
            else{
                hasErr = true;
            }
        }
        if(hasErr){
            JOptionPane.showMessageDialog(null, "invalid url", "alert", JOptionPane.ERROR_MESSAGE);
        }
        else{
            backend.addNewUrl(text, categoryCombo.getSelectedIndex());
            this.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(cancelBtn)){
            this.dispose();
        }
        else if(e.getSource().equals(okBtn)){
            checkEnteredURL();
        }
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