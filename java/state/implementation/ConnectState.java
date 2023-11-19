package state.implementation;

import gui.swing.command.AbstractCommand;
import gui.swing.command.implementation.AddElementCommand;
import gui.swing.command.implementation.AddVezaCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import gui.swing.view.PojamView;
import repository.Implementation.MindMap;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;
import state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ConnectState extends State {

    int xStart;
    int yStart;
    int xEnd;
    int yEnd;
    PojamView start;
    PojamView end;
    Veza veza;
    boolean izasao = false;
    boolean usao = true;


    public ConnectState() {

    }

    @Override
    public void misKliknut(MouseEvent e, MindMapView mindMapView) {
        xStart = e.getX();
        yStart = e.getY();
        MindMap mindMap = mindMapView.getMindMap();
        for(PojamView pojamView : mindMapView.getPojamViewList()){
            if(xStart >= pojamView.getX() && yStart >= pojamView.getY() && xStart <= pojamView.getX() + pojamView.getWidth() && yStart <= pojamView.getY() + pojamView.getHeight()){
                start = pojamView;
            }
        }
        if(start != null){
            veza = new Veza("Veza", mindMap, start.getPojam(), null,  xStart, yStart, xStart, yStart, Color.BLACK, 3);
            mindMapView.getMindMap().addChild(veza);
        }
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapView mindMapView) {

        if(start == null)return;
        if(izasao(e.getX(), e.getY())){
            izasao = true;
        }
        if(izasao){
            if(e.getX() >= start.getX() && e.getX() <= start.getX() + start.getWidth() && e.getY() <= start.getY()){
                veza.setxStart(e.getX());
                veza.setyStart(start.getY());
            }else if(e.getX() >= start.getX() && e.getX() <= start.getX() + start.getWidth() && e.getY() >= start.getY() + start.getHeight()){
                veza.setxStart(e.getX());
                veza.setyStart(start.getY() + start.getHeight());
            }else if(e.getY() >= start.getY() && e.getY() <= start.getY() + start.getHeight() && e.getX() <= start.getX()){
                veza.setxStart(start.getX());
                veza.setyStart(e.getY());
            }else if(e.getY() >= start.getY() && e.getY() <= start.getY() + start.getHeight() && e.getX() >= start.getX() + start.getWidth()){
                veza.setxStart(start.getX() + start.getWidth());
                veza.setyStart(e.getY());
            }
        }

        veza.setxEnd(e.getX());
        veza.setyEnd((e.getY()));

    }

    @Override
    public void misOtpusten(MouseEvent e, MindMapView mindMapView) {
        if(start == null)return;

        xEnd = e.getX();
        yEnd = e.getY();
        MindMap mindMap = mindMapView.getMindMap();
        for(PojamView pojamView : mindMapView.getPojamViewList()){
            if(xEnd >= pojamView.getX() && yEnd >= pojamView.getY() && xEnd <= pojamView.getX() + pojamView.getWidth() && yEnd <= pojamView.getY() + pojamView.getHeight()){
                end = pojamView;
            }
        }

        if(end == null){
            mindMap.removeChild(veza);
            return;
        }

        if(veza.getYStart() < end.getY()){
            veza.setyEnd(end.getY());
            veza.setxEnd(e.getX());
        }
        if(veza.getYStart() > end.getY() + end.getHeight()){
            veza.setyEnd(end.getY() + end.getHeight());
            veza.setxEnd(e.getX());
        }
        if(veza.getXStart() < end.getX()){
            veza.setxEnd(end.getX());
            veza.setyEnd(e.getY());
        }
        if(veza.getXStart() > end.getX() + end.getWidth()){
            veza.setxEnd(end.getX() + end.getWidth());
            veza.setyEnd(e.getY());
        }
        veza.setElement2(end.getPojam());



        mindMap.removeChild(veza);          //izbacujemo ga iz modela, prvo dodavanje u model se koristi da bi obzerver "vukao" liniju po ekranu
        AbstractCommand command = new AddVezaCommand(veza, mindMapView);
        MainFrame.getInstance().getCommandManager().addCommand(command);

        start = null;
        end = null;
        veza = null;
        usao = true;
        izasao = true;
    }

    private boolean izasao(int Xcor, int Ycor){
        if(Xcor >= start.getX() && Xcor <= (start.getX() + start.getWidth()) && Ycor >= start.getY() && Ycor <= (start.getY() + start.getHeight()))
            return false;
        return true;
    }

}
