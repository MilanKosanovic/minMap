package repository.composite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import observer.ObserverMessage;
import observer.Publisher;
import observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public abstract class MapNode implements Publisher {
    private String name;
    private transient MapNode parent;
    transient List<Subscriber> subscribers;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
            subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification, ObserverMessage message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification, message);
        }
    }

    public void setName(String name) {
        this.name = name;
    this.notifySubscribers(this, ObserverMessage.PROMENJENO_IME);
    }
}
