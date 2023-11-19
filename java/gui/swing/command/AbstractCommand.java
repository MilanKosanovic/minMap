package gui.swing.command;

import gui.swing.view.MindMapView;

import java.awt.event.MouseEvent;

public abstract class AbstractCommand {
    public abstract void doCommand();
    public abstract void undoCommand();
}
