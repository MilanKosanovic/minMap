package state.implementation;

import gui.swing.command.implementation.AddElementCommand;
import gui.swing.command.implementation.MoveElementCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import gui.swing.view.PojamView;
import gui.swing.view.VezaView;
import repository.Implementation.Veza;
import state.State;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DragState extends State {
    int xStartDrag;
    int yStartDrag;

    @Override
    public void misKliknut(MouseEvent e, MindMapView mindMapView) {
        xStartDrag = e.getX();
        yStartDrag = e.getY();
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapView mindMapView) {

        List<VezaView> vezaViewList = new ArrayList<VezaView>(mindMapView.getVezaViewList());

        for(PojamView pojamView : mindMapView.getSelectedPojamList()){
            pojamView.getPojam().setX(pojamView.getPojam().getX() - (xStartDrag - e.getX()));
            pojamView.getPojam().setY(pojamView.getPojam().getY() -(yStartDrag - e.getY()));

            for(VezaView vezaView : vezaViewList){
                if(vezaView.getVeza().getElement1().equals(pojamView.getPojam())){
                    vezaView.getVeza().setxStart(vezaView.getVeza().getXStart() - (xStartDrag - e.getX()));
                    vezaView.getVeza().setyStart(vezaView.getVeza().getYStart() - (yStartDrag - e.getY()));
                }
                if(vezaView.getVeza().getElement2().equals(pojamView.getPojam())) {
                    vezaView.getVeza().setxEnd(vezaView.getVeza().getXEnd() - (xStartDrag - e.getX()));
                    vezaView.getVeza().setyEnd(vezaView.getVeza().getYEnd() - (yStartDrag - e.getY()));
                }
            }
        }
        xStartDrag = e.getX();
        yStartDrag = e.getY();

    }

    @Override
    public void misOtpusten(MouseEvent e, MindMapView mindMapView) {
        /*MainFrame.getInstance().getCommandManager().addCommand(
            new MoveElementCommand()
        );*/
    }

    public int[] bothElements(MindMapView mindMapView, VezaView vezaView){
        Veza veza = vezaView.getVeza();
        int[] counter = {0,0};
        for(PojamView pojamView : mindMapView.getSelectedPojamList()){
            if(pojamView.getPojam().equals(veza.getElement1()))
                counter[0] = 1;
            if(pojamView.getPojam().equals(veza.getElement2()))
                counter[1] = 1;
        }
        return counter;
    }

}
