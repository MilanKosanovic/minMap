package repository.Implementation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import observer.ObserverMessage;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;
@Getter
@Setter
@ToString(callSuper = true)
public class Project extends MapNodeComposite {

    private String filePath;
    public Project(String name, MapNode parent) {
        super(name, parent);
        filePath = null;
    }

    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            if (!this.getChildren().contains(mindMap)){
                this.getChildren().add(mindMap);
            }
        }
        notifySubscribers(this, ObserverMessage.DODATO_DETE);
    }

}
