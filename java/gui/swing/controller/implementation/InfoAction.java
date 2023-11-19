package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoAction extends AbstractActionGeruMap {


    public InfoAction() {
        putValue(SMALL_ICON,loadIcon("/images/infoIcon.png"));
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(),"Stevan Dabizljevic 3920rn\nMilan Kosanovic 5820rn");
    }
}
