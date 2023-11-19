package core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import messageGenerator.ConsoleLogger;
import messageGenerator.FileLogger;
import messageGenerator.MessageGenerator;
import serializer.Serializer;

import java.io.Console;

@Getter
@Setter
public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    private MessageGenerator messageGenerator;
    private ConsoleLogger  consoleLogger;
    private FileLogger fileLogger;
    private Serializer serializer;

    public void run(){
        this.gui.start();
    }

    public ApplicationFramework() {

    }

    public void initialise(Gui gui,MapRepository mapRepository){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(gui);
        consoleLogger = new ConsoleLogger();
        fileLogger = new FileLogger();
        serializer = new Serializer();
    }
    private static ApplicationFramework instance;

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }
}
