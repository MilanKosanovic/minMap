package gui.swing.view;

import javax.swing.*;

public class ProjectToolBar extends JToolBar {

    public ProjectToolBar() {
        initializeComponents();
    }

    private void initializeComponents(){

        setFloatable(false);

        this.add(MainFrame.getInstance().getActionManager().getAddElementAction());
        this.add(MainFrame.getInstance().getActionManager().getRemoveElementAction());
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getConnectElementAction());
        this.add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        this.add(MainFrame.getInstance().getActionManager().getDragElementAction());
        this.add(MainFrame.getInstance().getActionManager().getEditElementAction());
        this.add(MainFrame.getInstance().getActionManager().getCenterAction());
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getLoadTemplateAction());
        this.add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
    }
}
