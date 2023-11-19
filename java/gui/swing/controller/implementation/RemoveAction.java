package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.tree.model.MapTreeItem;
import gui.swing.view.MainFrame;
import messageGenerator.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveAction extends AbstractActionGeruMap {



    public RemoveAction() {
        putValue(SMALL_ICON,loadIcon("/images/exitIcon.png"));
        putValue(NAME,"Remove");
        putValue(SHORT_DESCRIPTION,"Remove");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NOTHING_SELECTED);
            return;
        }
        MainFrame.getInstance().getMapTree().removeChild(selected);
    }
}
