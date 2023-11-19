package gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import observer.Subscriber;
import repository.Implementation.Veza;

import java.awt.*;
@Getter
@Setter
public class VezaView extends ElementView implements Subscriber {

    int line;
    Veza veza;
    boolean lineSelected;

    public VezaView(Veza veza) {
        this.veza = veza;
        lineSelected = false;
        line = veza.getDebljinaLinije();
    }

    @Override
    public void update(Object notification, ObserverMessage message) {
        veza.getParent().notifySubscribers(this, ObserverMessage.PROMENJENA_POZICIJA);
    }

    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        g.setColor(veza.getColor());
        if(lineSelected){
            float dash[] = { 10.0f };
            g.setStroke(new BasicStroke(line, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        }
        else
            g.setStroke(new BasicStroke(line));
        g.drawLine(veza.getXStart(), veza.getYStart(), veza.getXEnd(), veza.getYEnd());
    }

    public int getXStart(){
        return veza.getXStart();
    }

    public int getYStart(){
        return veza.getYStart();
    }

    public int getXEnd(){
        return veza.getXEnd();
    }

    public int getYEnd(){
        return veza.getYEnd();
    }
}
