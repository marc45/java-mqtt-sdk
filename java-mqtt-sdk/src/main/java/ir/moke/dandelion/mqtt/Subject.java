package ir.moke.dandelion.mqtt;

public interface Subject {
    void registerMessageListener(Class<? extends MessageListener> listenerClass) ;
    void notifyListener(String message) ;
}
