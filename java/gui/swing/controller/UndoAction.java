package gui.swing.controller;

import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractActionGeruMap {
    public UndoAction() {
        putValue(SMALL_ICON,loadIcon("/images/undo.png"));
        putValue(NAME,"Save");
        putValue(SHORT_DESCRIPTION,"Save");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();
    }
}
