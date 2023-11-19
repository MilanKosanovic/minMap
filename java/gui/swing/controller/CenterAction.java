package gui.swing.controller;

import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import repository.Implementation.Element;
import repository.Implementation.MindMap;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;
import repository.PojamWrapper;
import repository.composite.MapNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CenterAction extends AbstractActionGeruMap{
    public CenterAction() {
        putValue(SMALL_ICON,loadIcon("/images/center.png"));
        putValue(NAME,"Center Element");
        putValue(SHORT_DESCRIPTION,"Center Element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MindMapView mindMapView = MainFrame.getInstance().getProjectView().getActiveMindMap();
        mindMapView.getMindMap().setCentralniPojam(mindMapView.getSelectedPojamList().get(0).getPojam());

        List<Veza> veze = new ArrayList<>();
        List<Pojam> pojmovi = new ArrayList<>();
        List<PojamWrapper> wrappers = new ArrayList<>();

        for(MapNode mapNode : mindMapView.getMindMap().getChildren()){
            if(mapNode instanceof Pojam){
                pojmovi.add((Pojam) mapNode);
            }
            if(mapNode instanceof Veza){
                veze.add((Veza)mapNode);
            }
        }

        for(Pojam pojam : pojmovi){
            wrappers.add(new PojamWrapper(pojam, veze));
        }

        Dimension dimension =  MainFrame.getInstance().getProjectView().getActiveMindMap().getSize();

    }
}
