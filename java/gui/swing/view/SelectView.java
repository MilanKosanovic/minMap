package gui.swing.view;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class SelectView {
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;

    public SelectView(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public void paint(Graphics2D g){
        float dash[] = { 10.0f };
        g.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));

        g.setPaint(Color.darkGray);

        Rectangle r = new Rectangle(xStart, yStart, xEnd - xStart, yEnd - yStart);

        g.draw(r);
    }
}
