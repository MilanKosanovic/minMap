package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ConnectElementAction extends AbstractActionGeruMap {

    public ConnectElementAction() {
        putValue(SMALL_ICON,loadIcon("/images/line.png"));
        putValue(NAME,"Connect Element");
        putValue(SHORT_DESCRIPTION,"Connect Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startConnectState();
    }
}
