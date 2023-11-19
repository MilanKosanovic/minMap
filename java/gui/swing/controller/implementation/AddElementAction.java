package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddElementAction extends AbstractActionGeruMap {

    public AddElementAction() {
        putValue(SMALL_ICON,loadIcon("/images/newElement.png"));
        putValue(NAME,"Add Element");
        putValue(SHORT_DESCRIPTION,"Add Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startAddState();
    }
}
