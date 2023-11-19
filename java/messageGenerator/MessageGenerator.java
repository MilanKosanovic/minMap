package messageGenerator;

import observer.ObserverMessage;
import observer.Publisher;
import observer.Subscriber;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {

    private List<Subscriber> subscribers;

    public MessageGenerator() {
        subscribers = new ArrayList<>();
    }

    public void generateMessage(MessageType messageType){

        Message message = new Message(messageType,this.getMessage(messageType), LocalDateTime.now());
        notifySubscribers(message, ObserverMessage.ERROR_MESSAGE);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscribers.contains(subscriber)){
            subscribers.remove(subscriber);
        }
    }

    @Override
    public void notifySubscribers(Object notification, ObserverMessage message) {
            for(Subscriber subscriber : subscribers){
                subscriber.update(notification,message);
            }
    }

    private String getMessage(MessageType messageType){
        String message = "Nepoznati error";
        switch (messageType) {
            case NAME_EMPTY -> message = "Ime ne moze biti prazno";
            case CANT_ADD_CHILD -> message = "Ovom cvoru ne mozete dodati element";
            case NOTHING_SELECTED -> message = "Selektujte cvor za zeljenu operaciju";
            case PROJECT_EXPLORER_DELETE -> message = "Ne mozete obrisati project explorer";
        }
        return message;
    }
}
