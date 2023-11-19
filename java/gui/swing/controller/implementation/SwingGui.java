package gui.swing.controller.implementation;

import core.Gui;
import gui.swing.view.MainFrame;
import messageGenerator.Message;
import observer.ObserverMessage;
import observer.Subscriber;

import javax.swing.*;

public class SwingGui implements Gui, Subscriber {

    MainFrame mainFrame;
    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    @Override
    public void update(Object notification, ObserverMessage message) {
        if(message.equals(ObserverMessage.ERROR_MESSAGE)){
            Message poruka = (Message)notification;
            JOptionPane.showMessageDialog(mainFrame, poruka.getContext() + "\n" + poruka.getLocalDateTime().toString());
        }
    }
}
