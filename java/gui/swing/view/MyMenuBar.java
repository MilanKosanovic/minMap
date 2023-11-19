package gui.swing.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        initializeComponents();
    }

    private void initializeComponents(){


        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        //JMenu menuInfo = new JMenu("Info");

        this.add(menuFile);
        this.add(menuHelp);
        //this.add(menuInfo);

        menuFile.add(MainFrame.getInstance().getActionManager().getNewAction());
        menuFile.add(MainFrame.getInstance().getActionManager().getSaveAction());
        menuFile.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        menuFile.add(MainFrame.getInstance().getActionManager().getOpenAction());
        menuFile.add(MainFrame.getInstance().getActionManager().getExportAction());

        menuHelp.add(MainFrame.getInstance().getActionManager().getEditAction());


    }



}
