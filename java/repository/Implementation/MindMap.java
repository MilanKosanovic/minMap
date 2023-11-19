package repository.Implementation;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import repository.composite.MapNode;
import repository.composite.MapNodeComposite;
@Getter
@Setter
public class MindMap extends MapNodeComposite {

    Pojam centralniPojam = null;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
    }

    public void setCentralniPojam(Pojam centralniPojam) {
        this.centralniPojam = centralniPojam;
        notifySubscribers(this, ObserverMessage.DODATO_DETE);
    }


    @Override
    public void addChild(MapNode child) {
        if (child != null &&  child instanceof Element){
            Element element = (Element) child;
            if (!this.getChildren().contains(element)){
                this.getChildren().add(element);
            }
        }
        notifySubscribers(this, ObserverMessage.DODATO_DETE);
    }

    @Override
    public void removeChild(MapNode child) {
        this.getChildren().remove(child);
        notifySubscribers(this, ObserverMessage.DODATO_DETE);
    }


}
