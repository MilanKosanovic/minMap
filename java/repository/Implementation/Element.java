package repository.Implementation;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import observer.Subscriber;
import repository.composite.MapNode;

import java.awt.*;

@Getter
@Setter

public class Element extends MapNode {

    private int debljinaLinije;
    private transient Color color;
    private String boja;

    public Element(String name, MapNode parent) {
        super(name, parent);
        this.debljinaLinije = 2;
        this.color = Color.BLACK;
        this.boja = "Black";
    }

    public Element(String name, MapNode parent, int debljinaLinije, Color color) {
        super(name, parent);
        this.debljinaLinije = debljinaLinije;
        this.color = color;
    }

    public void setDebljinaLinije(int debljinaLinije) {
        this.debljinaLinije = debljinaLinije;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    public void setColor(Color color) {
        this.color = color;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {

    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification, ObserverMessage message) {
        this.getParent().notifySubscribers(notification, message);
    }
}
