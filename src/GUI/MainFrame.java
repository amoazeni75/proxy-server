/**
 * Author : S.Alireza  Moazeni
 * Student Number : 9423110
 * Project 1 : Proxy Server
 * Web Programming winter_spring 1397_1398
 */
package GUI;

import Models.Backend;
import Models.SocketListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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
    private JLabel status;

    //panels
    public ScrollablePanel urlsPanel;
    public ScrollablePanel categoriesPanel;

    //backend
    private Backend backend;

    private SpringLayout sp;

    public boolean stopStartStatus;

    public MainFrame() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("JProxy Server");
        intSystemTry();
        setIconImage(new ImageIcon("./icons/appIcon.png").getImage());
        this.setPreferredSize(new Dimension(520, 600));
        sp = new SpringLayout();
        backend = new Backend(new Dimension(520, 600), 350, 150, this);
        this.setLayout(sp);
        this.setResizable(false);
        this.addWindowListener(this);
        initComponent();
        backend.loadDataFromFile();
    }

    /**
     * this method provide system Tray Ability that contains two button : stop/start and exit
     * when you press system Tray Icon, Application will be show
     * when you press exit,then Application will close
     *
     * @throws IOException
     */
    private void intSystemTry() throws IOException {
        if (SystemTray.isSupported()) {
            sysTray = SystemTray.getSystemTray();
            trayImageIcon = ImageIO.read(new FileInputStream(new File("./icons/sysTrayIcon.png")));

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
            } catch (AWTException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * setup buttons location
     */
    private void initComponent() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //set look and feel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        //init buttons
        startStopBTN = new JButton(new ImageIcon("./icons/stop.png"));
        addCategoryBTN = new JButton(new ImageIcon("./icons/category-add-button.png"));
        addURLBTN = new JButton(new ImageIcon("./icons/addURL.png"));
        exitBTN = new JButton(new ImageIcon("./icons/exit.png"));
        status = new JLabel("<html> Status : OFF<BR> Port : <BR> IP : 127.0.0.1<BR>Select checkboxes to allow traffic</html>");

        //init buttons size
        int btnSize = 64;
        startStopBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        addCategoryBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        addURLBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        exitBTN.setPreferredSize(new Dimension(btnSize, btnSize));
        status.setPreferredSize(new Dimension(170, 70));

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
        add(status);

        //set focus
        startStopBTN.setFocusPainted(false);
        addCategoryBTN.setFocusPainted(false);
        addURLBTN.setFocusPainted(false);
        exitBTN.setFocusPainted(false);

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
        sp.putConstraint(SpringLayout.NORTH, status, 10, SpringLayout.NORTH, this);

        sp.putConstraint(SpringLayout.WEST, startStopBTN, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, addCategoryBTN, 10, SpringLayout.EAST, startStopBTN);
        sp.putConstraint(SpringLayout.WEST, addURLBTN, 10, SpringLayout.EAST, addCategoryBTN);
        sp.putConstraint(SpringLayout.WEST, exitBTN, -10 - btnSize, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.WEST, status, 10, SpringLayout.EAST, addURLBTN);
        //init panels
        urlsPanel = new URLsPanel(new Dimension(350, 485), backend);
        categoriesPanel = new CategoryPanel(new Dimension(150, 485), backend);

        add(urlsPanel);
        add(categoriesPanel);

        sp.putConstraint(SpringLayout.NORTH, urlsPanel, 10, SpringLayout.SOUTH, startStopBTN);
        sp.putConstraint(SpringLayout.NORTH, categoriesPanel, 10, SpringLayout.SOUTH, startStopBTN);

        sp.putConstraint(SpringLayout.WEST, categoriesPanel, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.WEST, urlsPanel, 5, SpringLayout.EAST, categoriesPanel);

    }

    public void showGUI() {
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void addNewCategory() {
        String inputValue = JOptionPane.showInputDialog("Please input the name of Category");
        if (inputValue != null && !inputValue.equals("")) {
            backend.addCategory(inputValue);
        }
    }

    private void saveAndExit() {
        try {
            backend.saveDataToFile();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        System.exit(0);
    }

    private void startStopProxyController() throws IOException {
        if (stopStartStatus) { // start -> stop
            backend.stopProxy();
            status.setText("<html> Status : OFF<BR> Port : <BR> IP : 127.0.0.1<BR>Select checkboxes to allow traffic</html>");
            startStopBTN.setIcon(new ImageIcon("./icons/stop.png"));
            stopStartStatus = false;
        } else { // stop -> start
            backend.startProxy();
            status.setText("<html> Status : ON<BR> Port : " + ((SocketListener) backend.r).serverPort + "<BR> IP : 127.0.0.1<BR>" +
                    "Select checkboxes to allow traffic </html>");
            startStopBTN.setIcon(new ImageIcon("./icons/start.png"));
            stopStartStatus = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: 2019-03-08 declear functionality of start and stop
        if (e.getSource() == sysTrayStartStop) {
            try {
                startStopProxyController();
            } catch (IOException e1) {
                System.err.println("can not stop server");
            }
            showGUI();
        } else if (e.getSource() == sysTrayExit) {
            saveAndExit();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == trayIcon && SwingUtilities.isLeftMouseButton(e)) {
            showGUI();
        } else if (e.getSource().equals(addCategoryBTN)) {
            addNewCategory();
        } else if (e.getSource().equals(addURLBTN)) {
            AddNewUrlPanel tmp = new AddNewUrlPanel(backend);
        } else if (e.getSource().equals(exitBTN)) {
            saveAndExit();
        } else if (e.getSource().equals(startStopBTN)) {
            try {
                startStopProxyController();
            } catch (IOException e1) {
                System.err.println("Can not stop server");
            }
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
        saveAndExit();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
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
