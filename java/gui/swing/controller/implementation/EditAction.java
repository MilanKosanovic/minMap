package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.tree.model.MapTreeItem;
import gui.swing.view.MainFrame;
import messageGenerator.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditAction extends AbstractActionGeruMap {

    public EditAction() {
        putValue(SMALL_ICON,loadIcon("/images/editIcon.png"));
        putValue(NAME,"Edit");
        putValue(SHORT_DESCRIPTION,"Edit");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NOTHING_SELECTED);
            return;
        }
        String newName = "";
        newName = JOptionPane.showInputDialog(MainFrame.getInstance(),"Unesite zeljeno ime");

        if(newName == null || newName.equals("")) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(MessageType.NAME_EMPTY);
            return;
        }
        selected.setName(newName);
    }
}
