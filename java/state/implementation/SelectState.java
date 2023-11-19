package state.implementation;

import gui.swing.view.MindMapView;
import state.State;

import java.awt.event.MouseEvent;

public class SelectState extends State {

    int xStart;
    int yStart;
    int xEnd;
    int yEnd;

    @Override
    public void misKliknut(MouseEvent e, MindMapView mindMapView) {
        xStart = e.getX();
        yStart = e.getY();
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapView mindMapView) {
        xEnd = e.getX();
        yEnd = e.getY();

        mindMapView.select(xStart,yStart,xEnd,yEnd);
    }

    @Override
    public void misOtpusten(MouseEvent e, MindMapView mindMapView) {
        mindMapView.selected(xStart,yStart,xEnd,yEnd);
        xStart = 0;
        yStart = 0;
        xEnd = 0;
        yEnd = 0;
        mindMapView.select(xStart,yStart,xEnd,yEnd);
    }
}
