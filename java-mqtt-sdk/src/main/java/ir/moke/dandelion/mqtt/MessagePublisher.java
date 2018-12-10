package ir.moke.dandelion.mqtt;

public interface MessagePublisher {
    static void apply(String message) {
        MqttFactory.instance.send(message);
    }
}
