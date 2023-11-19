package repository.Implementation;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import observer.Subscriber;
import repository.composite.MapNode;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class Veza extends Element {
    Element element1;
    Element element2;
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;
    String bojaAsString;
    int lineThickness;

    public Veza(String name, MapNode parent, Element element1, Element element2, int xStart, int yStart, int xEnd, int yEnd, Color color, int debljinaLinije) {
        super(name, parent, debljinaLinije, color);
        this.element1 = element1;
        this.element2 = element2;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        bojaAsString = Integer.toString(this.getColor().getRGB());
        lineThickness = debljinaLinije;
    }

    public void setElement1(Element element1) {
        this.element1 = element1;
    }

    public void setElement2(Element element2) {
        this.element2 = element2;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        super.addSubscriber(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        super.removeSubscriber(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification, ObserverMessage message) {
        this.getParent().notifySubscribers(notification, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veza)) return false;
        if (!super.equals(o)) return false;
        Veza veza = (Veza) o;
        return xStart == veza.xStart && yStart == veza.yStart && xEnd == veza.xEnd && yEnd == veza.yEnd && Objects.equals(getElement1(), veza.getElement1()) && Objects.equals(getElement2(), veza.getElement2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElement1(), getElement2(), xStart, yStart, xEnd, yEnd);
    }
}
