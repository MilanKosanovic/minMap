package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;
import repository.Implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenAction extends AbstractActionGeruMap {
    public OpenAction() {
        //putValue(SMALL_ICON,loadIcon("/images/icons8-new-48.png"));
        putValue(NAME,"Open");
        putValue(SHORT_DESCRIPTION,"Open");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getMapTree().loadProject(p);

            } catch (Exception s) {
                s.printStackTrace();
            }
        }
    }

}
