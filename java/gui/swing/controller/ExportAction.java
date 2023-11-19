package gui.swing.controller;

import gui.swing.command.AbstractCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;

import java.awt.event.ActionEvent;

public class ExportAction extends AbstractActionGeruMap {
    public ExportAction() {
        //putValue(SMALL_ICON,loadIcon("/images/line.png"));
        putValue(NAME,"Export As Image");
        putValue(SHORT_DESCRIPTION,"Export As Image");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MindMapView mindMapView =  MainFrame.getInstance().getProjectView().getActiveMindMap();
        mindMapView.saveImage(mindMapView.getMindMap().getName(), "png");
    }
}
