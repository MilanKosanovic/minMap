package gui.swing.controller;

import gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractActionGeruMap{
    public RedoAction() {
        putValue(SMALL_ICON,loadIcon("/images/redo.png"));
        putValue(NAME,"Save");
        putValue(SHORT_DESCRIPTION,"Save");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }
}
