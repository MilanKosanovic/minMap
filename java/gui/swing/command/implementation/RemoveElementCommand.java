package gui.swing.command.implementation;

import gui.swing.command.AbstractCommand;
import gui.swing.view.*;
import repository.Implementation.Element;
import repository.Implementation.MindMap;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;
import repository.composite.MapNode;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoveElementCommand extends AbstractCommand {
    MindMap mindMap;
    List<PojamView> selectedPojamView;
    List<VezaView> selectedVezaView;
    List<Element> obrisano;

    public RemoveElementCommand(MindMap mindMap, List<PojamView> selectedPojamview, List<VezaView> selectedVezaView) {
        this.mindMap = mindMap;
        this.selectedPojamView = new ArrayList<>(selectedPojamview);
        this.selectedVezaView = new ArrayList<>(selectedVezaView);
    }

    @Override
    public void doCommand() {
        obrisano = MainFrame.getInstance().getMapTree().removeElement(mindMap, selectedPojamView, selectedVezaView);
    }

    @Override
    public void undoCommand() {
        for(Element element : obrisano){
            MainFrame.getInstance().getMapTree().addElement(element, mindMap);
        }
    }
}
