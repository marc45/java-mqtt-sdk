package ir.moke.dandelion.mqtt;

public class MessageListenerHandler implements Subject {
    public static MessageListenerHandler instance = new MessageListenerHandler();
    private MessageListener messageListener;

    private MessageListenerHandler() {
    }

    @Override
    public void registerMessageListener(Class<? extends MessageListener> listenerClass) {
        try {
            this.messageListener = listenerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyListener(String message) {
        messageListener.onMessage(message);
    }
}
