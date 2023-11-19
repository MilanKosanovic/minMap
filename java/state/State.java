package state;

import gui.swing.view.MindMapView;

import java.awt.event.MouseEvent;

public abstract class State {
    public void misKliknut(MouseEvent e, MindMapView mindMapView){};
    public void misPovucen(MouseEvent e, MindMapView mindMapView){};
    public void misOtpusten(MouseEvent e, MindMapView mindMapView){};
}
