package gui.swing.controller.implementation;

import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomElementAction extends AbstractActionGeruMap {

    public ZoomElementAction() {
        //putValue(SMALL_ICON,loadIcon("/images/infoIcon.png"));
        putValue(NAME,"Zoom");
        putValue(SHORT_DESCRIPTION,"Zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startZoomState();
    }
}
