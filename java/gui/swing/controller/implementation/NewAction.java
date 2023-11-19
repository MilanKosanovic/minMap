package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.tree.model.MapTreeItem;
import gui.swing.view.MainFrame;
import messageGenerator.MessageType;

import javax.swing.*;
import java.awt.desktop.AppForegroundListener;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractActionGeruMap {

    public NewAction() {
        putValue(SMALL_ICON,loadIcon("/images/icons8-new-48.png"));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NOTHING_SELECTED);
            return;
        }
        MainFrame.getInstance().getMapTree().addChild(selected);
    }
}
