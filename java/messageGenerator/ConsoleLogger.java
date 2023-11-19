package messageGenerator;

import core.ApplicationFramework;
import observer.ObserverMessage;
import observer.Subscriber;

public class ConsoleLogger implements Subscriber {

    public ConsoleLogger() {
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
    }

    @Override
    public void update(Object notification, ObserverMessage message) {
        if(message.equals(ObserverMessage.ERROR_MESSAGE)){
            Message poruka = (Message) notification;
            System.out.println(poruka.getMessageType() + " " + poruka.getLocalDateTime().toString() + " " + poruka.getContext());
        }
    }
}
