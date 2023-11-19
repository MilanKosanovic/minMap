package messageGenerator;

import core.ApplicationFramework;
import observer.ObserverMessage;
import observer.Subscriber;

import javax.annotation.processing.SupportedSourceVersion;
import java.awt.desktop.AppForegroundListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Subscriber {

    public FileLogger() {
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
    }

    @Override
    public void update(Object notification, ObserverMessage message) {
        if(message.equals(ObserverMessage.ERROR_MESSAGE)){
            Message poruka = (Message) notification;
            String path = getClass().getResource("/") + "/log.txt";
            File file = new File(path);
            FileWriter fileWriter;
            /*try {
                fileWriter = new FileWriter(file);
                fileWriter.write(poruka.getContext() + " " + poruka.getLocalDateTime().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }
    }
}
