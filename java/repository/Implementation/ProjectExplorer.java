package repository.Implementation;

import observer.ObserverMessage;
import observer.Subscriber;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
        notifySubscribers(this, ObserverMessage.DODATO_DETE);
    }


}
