package gui.swing.controller.implementation;

import core.ApplicationFramework;
import gui.swing.controller.AbstractActionGeruMap;
import gui.swing.view.MainFrame;
import repository.Implementation.MindMap;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveTemplateAction extends AbstractActionGeruMap {
    public SaveTemplateAction() {
        putValue(SMALL_ICON,loadIcon("/images/saveTemplate.png"));
        putValue(NAME,"Save Template");
        putValue(SHORT_DESCRIPTION,"Save Template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MindMap mindMap = MainFrame.getInstance().getProjectView().getActiveMindMap().getMindMap();
        if(mindMap == null)return;
        File file = new File("src/main/resources/templates");
        ApplicationFramework.getInstance().getSerializer().saveMapTemplate(mindMap, file.getAbsolutePath());
    }
}
