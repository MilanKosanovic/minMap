package repository.Implementation;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import repository.composite.MapNode;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class Pojam extends Element {

    int X;
    int Y;
    int height;
    String bojaAsString;
    int lineThickness;


    public Pojam(String name, MapNode parent, int x, int y, int line, Color color) {
        super(name, parent, line, color);
        X = x;
        Y = y;
        this.height = 30;
        bojaAsString = Integer.toString(this.getColor().getRGB());
        lineThickness = line;
    }

    public void setX(int x) {
        X = x;
        //System.out.println("Promenjen X"  +x);
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    public void setY(int y) {
        Y = y;
        //System.out.println("Promenjen Y"  +y);
        this.notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }



    @Override
    public void notifySubscribers(Object notification, ObserverMessage message) {
        this.getParent().notifySubscribers(notification, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pojam)) return false;
        if (!super.equals(o)) return false;
        Pojam pojam = (Pojam) o;
        return getX() == pojam.getX() && getY() == pojam.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Pojam: " + this.getName();
    }
}
