package gui.swing.view;

import gui.swing.view.controler.MindMapMouseListener;
import lombok.Getter;
import lombok.Setter;
import observer.ObserverMessage;
import observer.Subscriber;
import repository.Implementation.MindMap;
import repository.Implementation.Pojam;
import repository.Implementation.Veza;
import repository.composite.MapNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
public class MindMapView extends JPanel implements Subscriber {

    MindMap mindMap;
    JLabel ime;
    List<PojamView> pojamViewList;
    List<VezaView> vezaViewList;

    SelectView selectView;

    List<PojamView> selectedPojamList;
    List<VezaView> selectedVezaList;


    public MindMapView(MindMap mindMap) {
        this.mindMap = mindMap;
        ime = new JLabel();
        ime.setText(mindMap.getName());
        pojamViewList = new ArrayList<>();
        vezaViewList = new ArrayList<>();
        selectedPojamList = new ArrayList<>();
        selectedVezaList = new ArrayList<>();
        this.addMouseListener(new MindMapMouseListener(this));
        this.addMouseMotionListener(new MindMapMouseListener(this));
        selectView = new SelectView(0,0,0,0);

        if(!mindMap.getChildren().isEmpty()){
            for(MapNode mapNode : mindMap.getChildren()){
                if(mapNode instanceof Pojam){
                    PojamView pojamView = new PojamView((Pojam)mapNode);
                    pojamViewList.add(pojamView);
                }else{
                    VezaView vezaView = new VezaView((Veza)mapNode);
                    vezaViewList.add(vezaView);
                }
            }
        }

    }

    @Override
    public void update(Object notification, ObserverMessage message) {
        if(message.equals(ObserverMessage.PROMENJENO_IME)){
            if(notification instanceof MapNode){
                int index = MainFrame.getInstance().getProjectView().getTabbedPane().indexOfComponent(this);
                MainFrame.getInstance().getProjectView().getTabbedPane().setTitleAt(index, ((MapNode)notification).getName());
            }
        }

        if(message.equals(ObserverMessage.DODATO_DETE)){

            pojamViewList.clear();
            vezaViewList.clear();
            for(MapNode mapNode : mindMap.getChildren()){
                if(mapNode instanceof Pojam){
                    pojamViewList.add(new PojamView((Pojam)mapNode));
                }else{
                    vezaViewList.add(new VezaView((Veza) mapNode));
                }
            }
            this.repaint();
        }

        if(message.equals(ObserverMessage.PROMENJENA_POZICIJA)){
            pojamViewList.clear();
            vezaViewList.clear();
            for(MapNode mapNode : mindMap.getChildren()){
                if(mapNode instanceof Pojam){
                    pojamViewList.add(new PojamView((Pojam)mapNode));
                }else{
                    vezaViewList.add(new VezaView((Veza) mapNode));
                }
            }
            this.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(PojamView pojamView : pojamViewList){
            pojamView.paint((Graphics2D) g);
        }
        for(VezaView vezaView : vezaViewList){
            vezaView.paint((Graphics2D) g);
        }
        selectView.paint((Graphics2D) g);
    }

    public void saveImage(String name,String type) {
        type = "png";
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showSaveDialog(null);
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(image, type, new File(jfc.getSelectedFile() + "/" + name + "." + type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void select(int xStart, int yStart, int xEnd, int yEnd){
        selectView.setXStart(xStart);
        selectView.setYStart(yStart);
        selectView.setXEnd(xEnd);
        selectView.setYEnd(yEnd);
        this.repaint();
    }

    public void selected(int xStart, int yStart, int xEnd, int yEnd){
        for(PojamView pojamView : selectedPojamList){
            pojamView.setLineSelected(false);
        }
        selectedPojamList.clear();
        for(PojamView pojamView : pojamViewList){
            if(pojamView.getX() >= xStart && pojamView.getX() <= xEnd && pojamView.getY() >= yStart && pojamView.getY() <= yEnd){
                selectedPojamList.add(pojamView);
            }
        }
        for(PojamView pojamView : selectedPojamList){
            pojamView.setLineSelected(true);
        }

        for(VezaView vezaView : selectedVezaList){
            vezaView.setLineSelected(false);
        }
        selectedVezaList.clear();
        for(VezaView vezaView : vezaViewList){
            if((vezaView.getXStart() >= xStart && vezaView.getXStart() <= xEnd && vezaView.getYStart() >= yStart && vezaView.getYStart() <= yEnd) ||
                    (vezaView.getXEnd() >= xStart && vezaView.getXEnd() <= xEnd && vezaView.getYEnd() >= yStart && vezaView.getYEnd() <= yEnd)){
                selectedVezaList.add(vezaView);

            }
        }
        for(VezaView vezaView : selectedVezaList){
            vezaView.setLineSelected(true);
        }
        this.repaint();
    }

    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().misKliknut(e, this);
    }

    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().misOtpusten(e, this);
    }

    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getProjectView().getStateManager().getCurrentState().misPovucen(e, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MindMapView)) return false;
        MindMapView that = (MindMapView) o;
        return Objects.equals(getMindMap(), that.getMindMap()) && Objects.equals(getIme(), that.getIme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMindMap(), getIme());
    }
}
