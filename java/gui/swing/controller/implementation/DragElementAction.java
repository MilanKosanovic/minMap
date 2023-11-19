package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DragElementAction extends AbstractActionGeruMap {

    public DragElementAction() {
        putValue(SMALL_ICON,loadIcon("/images/move.png"));
        putValue(NAME,"Drag Element");
        putValue(SHORT_DESCRIPTION,"Drag Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startDragState();
    }
}
