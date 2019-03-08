package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener, MouseListener, WindowListener {
    //System Tray
    private Image trayImageIcon;    // icon for Application
    private TrayIcon trayIcon;      // icon for Application in System Tray
    private SystemTray sysTray;
    private PopupMenu sysTrayMenu;  //Menu for Application in System Tray
    private MenuItem sysTrayExit;   //Exit button in system tray
    private MenuItem sysTrayStartStop;   //start and stop button in system tray

    //Primary Buttons
    private JButton startStopBTN;
    private JButton addCategoryBTN;
    private JButton addURLBTN;
    private JButton exitBTN;

    private SpringLayout sp;
    public MainFrame() throws IOException {
        super("JProxy Server");


        intSystemTry();
        setIconImage(new ImageIcon("./icons/appIcon.png").getImage());
        this.setPreferredSize(new Dimension(500,600));
        sp = new SpringLayout();
        this.setLayout(sp);
        this.setResizable(false);
        this.addWindowListener(this);
        initComponent();
    }

    /**
     * this method provide system Tray Ability that contains two button : stop/start and exit
     * when you press system Tray Icon, Application will be show
     * when you press exit,then Application will close
     * @throws IOException
     */
    private void intSystemTry() throws IOException {
        if (SystemTray.isSupported()) {
            sysTray = SystemTray.getSystemTray();
            trayImageIcon  = ImageIO.read(new FileInputStream(new File("./icons/sysTrayIcon.png")));

            sysTrayMenu = new PopupMenu();

            sysTrayExit = new MenuItem("Exit");
            sysTrayStartStop = new MenuItem("Start/Stop");

            sysTrayStartStop.addActionListener(this);
            sysTrayExit.addActionListener(this);

            sysTrayMenu.add(sysTrayStartStop);
            sysTrayMenu.add(sysTrayExit);

            trayIcon = new TrayIcon(trayImageIcon, "JProxy Server", sysTrayMenu);
            trayIcon.addMouseListener(this);
            try {
                sysTray.add(trayIcon);
            }
            catch(AWTException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * setup buttons location
     */
    private void initComponent(){
        //init buttons
        startStopBTN = new JButton(new ImageIcon("./icons/stop.png"));
        addCategoryBTN = new JButton(new ImageIcon("./icons/category-add-button.png"));
        addURLBTN = new JButton(new ImageIcon("./icons/addURL.png"));
        exitBTN = new JButton(new ImageIcon("./icons/exit.png"));

        //init buttons size
        int btnSize = 64;
        startStopBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        addCategoryBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        addURLBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        exitBTN.setPreferredSize(new Dimension(btnSize, btnSize));

        //set buttons transparent
        startStopBTN.setContentAreaFilled(false);
        addCategoryBTN.setContentAreaFilled(false);
        addURLBTN.setContentAreaFilled(false);
        exitBTN.setContentAreaFilled(false);

        //set buttons listener
        startStopBTN.addMouseListener(this);
        addCategoryBTN.addMouseListener(this);
        addURLBTN.addMouseListener(this);
        exitBTN.addMouseListener(this);

        //add buttons to layout
        add(startStopBTN);
        add(addCategoryBTN);
        add(addURLBTN);
        add(exitBTN);

        //remove buttons border
        startStopBTN.setBorderPainted(false);
        addCategoryBTN.setBorderPainted(false);
        addURLBTN.setBorderPainted(false);
        exitBTN.setBorderPainted(false);

        //set buttons location
        sp.putConstraint(SpringLayout.NORTH, startStopBTN, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.NORTH, addCategoryBTN, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.NORTH, addURLBTN, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.NORTH, exitBTN, 10, SpringLayout.NORTH, this);

        sp.putConstraint(SpringLayout.WEST, startStopBTN, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, addCategoryBTN, 10, SpringLayout.EAST, startStopBTN);
        sp.putConstraint(SpringLayout.WEST, addURLBTN, 10, SpringLayout.EAST, addCategoryBTN);
        sp.putConstraint(SpringLayout.WEST, exitBTN, -10 -btnSize, SpringLayout.EAST, this);
    }

    public void showGUI(){
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: 2019-03-08 declear functionality of start and stop
        if(e.getSource() == sysTrayStartStop)
            showGUI();
        // TODO: 2019-03-08 declear functionality of exit
        else if(e.getSource() == sysTrayExit){
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == trayIcon && SwingUtilities.isLeftMouseButton(e)){
            showGUI();
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
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        setVisible(false);
        try { Thread.sleep(5000); }
        catch (InterruptedException ex) { }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {

    }
    @Override
    public void windowDeiconified(WindowEvent e) {

    }
    @Override
    public void windowActivated(WindowEvent e) {

    }
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
