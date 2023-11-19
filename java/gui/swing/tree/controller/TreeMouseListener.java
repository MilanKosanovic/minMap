package gui.swing.tree.controller;


import gui.swing.tree.model.MapTreeItem;
import gui.swing.view.MainFrame;
import repository.Implementation.Project;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreeMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        MapTreeItem mapTreeItem = MainFrame.getInstance().getMapTree().getSelectedNode();

        if(e.getClickCount() == 2 && mapTreeItem.getMapNode() instanceof Project){
            MainFrame.getInstance().getProjectView().setProject((Project) mapTreeItem.getMapNode());
            MainFrame.getInstance().getMapTree().expand();
        }
    }
}
