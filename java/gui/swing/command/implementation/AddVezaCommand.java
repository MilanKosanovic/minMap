package gui.swing.command.implementation;

import gui.swing.command.AbstractCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;

public class AddVezaCommand extends AbstractCommand {
    MindMapView mindMapView;
    Veza veza;
    public AddVezaCommand(Veza veza, MindMapView mindMapView) {
        this.mindMapView = mindMapView;
        this.veza = veza;
    }

    @Override
    public void doCommand() {
        MainFrame.getInstance().getMapTree().addElement(this.veza, mindMapView.getMindMap());
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getMapTree().removeSingleElement(mindMapView.getMindMap(), veza);
    }
}
