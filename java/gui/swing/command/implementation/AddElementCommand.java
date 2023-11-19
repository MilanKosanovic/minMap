package gui.swing.command.implementation;

import gui.swing.command.AbstractCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import repository.Implementation.Element;
import repository.Implementation.Pojam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddElementCommand extends AbstractCommand {

    public AddElementCommand(Pojam pojam, MindMapView mindMapView) {
        this.mindMapView = mindMapView;
        this.pojam = pojam;
    }

    MindMapView mindMapView;
    Pojam pojam;
    @Override
    public void doCommand() {
        MainFrame.getInstance().getMapTree().addElement(this.pojam, mindMapView.getMindMap());
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getMapTree().removeSingleElement(mindMapView.getMindMap(), pojam);
    }
}
