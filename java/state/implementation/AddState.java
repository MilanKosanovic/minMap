package state.implementation;

import com.sun.tools.javac.Main;
import core.ApplicationFramework;
import gui.swing.command.AbstractCommand;
import gui.swing.command.implementation.AddElementCommand;
import gui.swing.view.MainFrame;
import gui.swing.view.MindMapView;
import repository.Implementation.Pojam;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddState extends State {

    private static int enumerator = 0;

    @Override
    public void misKliknut(MouseEvent e, MindMapView mindMapView) {
        JLabel labela = new JLabel("Unesite tekst");
        JLabel labelaLine = new JLabel("Unesite zeljenu deblinu linije (px)");
        JTextArea jTextArea = new JTextArea();
        JTextArea jTextAreaLine = new JTextArea();
        JColorChooser jColorChooser = new JColorChooser();
        jTextAreaLine.setText("2");
        JComponent[] komponente = new JComponent[]{
                labela,jTextArea,jColorChooser,labelaLine,jTextAreaLine
        };
        int result = JOptionPane.showConfirmDialog(MainFrame.getInstance(), komponente, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
        Color boja = jColorChooser.getColor();
        String text = jTextArea.getText();
        int deblinaLinije = Integer.valueOf(jTextAreaLine.getText());

        Pojam pojam = null;
        if(result == JOptionPane.OK_OPTION)
            pojam = new Pojam(text, mindMapView.getMindMap(), e.getX(), e.getY(), deblinaLinije, boja);
        else return;

        AbstractCommand command = new AddElementCommand(pojam, mindMapView);
        MainFrame.getInstance().getCommandManager().addCommand(command);
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapView mindMapView) {

    }

    @Override
    public void misOtpusten(MouseEvent e, MindMapView mindMapView) {

    }
}
