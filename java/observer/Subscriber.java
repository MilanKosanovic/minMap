package observer;

public interface Subscriber {
    public void update(Object notification, ObserverMessage message);
}
