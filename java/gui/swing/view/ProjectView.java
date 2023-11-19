package gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import observer.Subscriber;
import repository.Implementation.MindMap;
import repository.Implementation.Project;
import repository.composite.MapNode;
import state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectView extends JPanel implements Subscriber {

    Project project;
    JLabel ime;
    JLabel autor;
    List<MindMapView> mindMapViewList;
    JTabbedPane tabbedPane;
    ProjectToolBar projectToolBar;

    StateManager stateManager;

    public ProjectView() {
        this.ime = new JLabel("Otvorite zeljeni projekat");
        tabbedPane = new JTabbedPane();
        autor = new JLabel("Nepoznat");
        projectToolBar = new ProjectToolBar();
        mindMapViewList = new ArrayList<>();
        this.setLayout(new BorderLayout());
        this.add(ime,BorderLayout.NORTH);
        this.add(projectToolBar, BorderLayout.NORTH);
        this.add(autor,BorderLayout.SOUTH);
        this.add(tabbedPane, BorderLayout.CENTER);
        stateManager = new StateManager();
    }

    public void setProject(Project project) {

        this.project = project;

        if(this.project != null)
            this.project.removeSubscriber(this);
        else{
            this.ime.setText("Otvorite zeljeni projekat");
            return;
        }

        project.addSubscriber(this);

        ime.setText(project.getName());
        resetTabova();
    }

    @Override
    public void update(Object notification, observer.ObserverMessage message) {
        if(message.equals(ObserverMessage.PROMENJENO_IME)){
            if(notification instanceof MapNode)
                this.getIme().setText(((MapNode) notification).getName());
        }

        if(message.equals(ObserverMessage.OBRISANO_DETE)){
            resetTabova();
        }

        if(message.equals((ObserverMessage.DODATO_DETE))){
            resetTabova();
        }
    }

    void resetTabova(){
        tabbedPane.removeAll();

        if(mindMapViewList.size() != 0)
            for(MindMapView mindMapView : mindMapViewList){
                mindMapView.getMindMap().removeSubscriber(mindMapView);
            }

        mindMapViewList.clear();

        for(MapNode mapNode : project.getChildren()) {
            MindMapView mindMapView = new MindMapView((MindMap) mapNode);
            mapNode.addSubscriber(mindMapView);
            mindMapViewList.add(mindMapView);
            tabbedPane.addTab(mapNode.getName(), mindMapView);
        }
    }

    public MindMapView getActiveMindMap(){
        MindMapView mindMapView = null;
        for(MindMapView mindMapView1 : MainFrame.getInstance().getProjectView().getMindMapViewList()){
            if(mindMapView1.getIme().getText().equals(((MindMapView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent()).getIme().getText())){
                mindMapView = mindMapView1;
            }
        }
        return mindMapView;
    }

    public void startAddState(){stateManager.setAddState();}
    public void startConnectState(){stateManager.setConnectState();}
    public void startDragState(){stateManager.setDragState();}
    public void startRemoveState(){stateManager.setRemoveState();}
    public void startSelectState(){stateManager.setSelectState();}
    public void startZoomState(){stateManager.setZoomState();}
}
