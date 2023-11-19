package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;
import repository.Implementation.MindMap;
import repository.Implementation.Project;
import repository.composite.MapNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadTemplateAction extends AbstractActionGeruMap {
    public LoadTemplateAction() {
        putValue(SMALL_ICON,loadIcon("/images/template.png"));
        putValue(NAME,"Load Template");
        putValue(SHORT_DESCRIPTION,"Load Template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        File filePath = new File("src/main/resources/templates");
        JFileChooser jfc = new JFileChooser(filePath.getAbsolutePath());
        File file;
        MindMap mindMap;
        Project project;

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                file = jfc.getSelectedFile();
            } catch (Exception s) {
                s.printStackTrace();
                return;
            }
        }else return;

        MapNode mapNode = MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        if(mapNode instanceof Project){
            project = (Project)mapNode;
            mindMap = ApplicationFramework.getInstance().getSerializer().loadMapTemplate(file,project);
        }else return;

        MainFrame.getInstance().getMapTree().addChildToParent(mindMap, project);
    }
}
