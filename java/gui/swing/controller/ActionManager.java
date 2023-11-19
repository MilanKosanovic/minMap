package gui.swing.controller;

import gui.swing.controller.implementation.*;
import lombok.Getter;

@Getter
public class ActionManager {

    RemoveAction exitAction;
    InfoAction infoAction;
    NewAction newAction;
    EditAction editAction;
    AddElementAction addElementAction;
    ConnectElementAction connectElementAction;
    DragElementAction dragElementAction;
    RemoveElementAction removeElementAction;
    SelectElementAction selectElementAction;
    ZoomElementAction zoomElementAction;
    EditElementAction editElementAction;
    SaveAsAction saveAsAction;
    SaveAction saveAction;
    OpenAction openAction;
    UndoAction undoAction;
    RedoAction redoAction;
    CenterAction centerAction;
    ExportAction exportAction;
    LoadTemplateAction loadTemplateAction;
    SaveTemplateAction saveTemplateAction;


    public ActionManager() {
        initActions();
    }

    private void initActions(){
        exitAction = new RemoveAction();
        infoAction = new InfoAction();
        newAction = new NewAction();
        editAction = new EditAction();
        addElementAction = new AddElementAction();
        connectElementAction = new ConnectElementAction();
        dragElementAction = new DragElementAction();
        removeElementAction = new RemoveElementAction();
        selectElementAction = new SelectElementAction();
        zoomElementAction = new ZoomElementAction();
        editElementAction = new EditElementAction();
        saveAsAction = new SaveAsAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        centerAction = new CenterAction();
        exportAction = new ExportAction();
        loadTemplateAction = new LoadTemplateAction();
        saveTemplateAction = new SaveTemplateAction();
    }

}
