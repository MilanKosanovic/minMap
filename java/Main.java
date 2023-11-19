import core.ApplicationFramework;
import core.Gui;
import core.MapRepository;
import gui.swing.controller.implementation.SwingGui;
import repository.MapRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapRepositoryImpl();
        appCore.initialise(gui, mapRepository);
        appCore.run();
    }
}
