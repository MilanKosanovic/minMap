package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectElementAction extends AbstractActionGeruMap {

    public SelectElementAction() {
        putValue(SMALL_ICON,loadIcon("/images/select.png"));
        putValue(NAME,"Select Element");
        putValue(SHORT_DESCRIPTION,"Select Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectState();
    }
}
