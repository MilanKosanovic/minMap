package gui.swing.command.implementation;

import gui.swing.command.AbstractCommand;
import gui.swing.view.ElementView;
import gui.swing.view.MindMapView;
import gui.swing.view.PojamView;
import gui.swing.view.VezaView;
import repository.Implementation.MindMap;
import repository.Implementation.Veza;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class MoveElementCommand extends AbstractCommand {

    MindMap mindMap;
    List<Point> pojamNew;
    List<Point> pojamOld;
    List<Point> vezaStartNew;
    List<Point> vezaStartOld;
    List<Point> vezaEndNew;
    List<Point> vezaEndOld;

    public MoveElementCommand(MindMap mindMap, List<Point> pojamNew, List<Point> pojamOld, List<Point> vezaStartNew, List<Point> vezaStartOld, List<Point> vezaEndNew, List<Point> vezaEndOld) {
        this.mindMap = mindMap;
        this.pojamNew = pojamNew;
        this.pojamOld = pojamOld;
        this.vezaStartNew = vezaStartNew;
        this.vezaStartOld = vezaStartOld;
        this.vezaEndNew = vezaEndNew;
        this.vezaEndOld = vezaEndOld;
    }

    @Override
    public void doCommand() {

    }

    @Override
    public void undoCommand() {

    }
}
