package gui.swing.view.controler;

import gui.swing.view.MindMapView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MindMapMouseListener extends MouseAdapter {

    MindMapView mindMapView;

    public MindMapMouseListener(MindMapView mindMapView){
        this.mindMapView = mindMapView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mindMapView.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mindMapView.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mindMapView.mouseDragged(e);
    }
}
