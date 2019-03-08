/**
 * Author : S.Alireza  Moazeni
 * Student Number : 9423110
 * Project 1 : Proxy Server
 * Web Programming winter_spring 1397_1398
 */

import GUI.MainFrame;

import java.io.IOException;

/**
 * this class will create a thread and run gui of application
 */
public class RunnbleGUI {
    public RunnbleGUI(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainFrame().showGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

