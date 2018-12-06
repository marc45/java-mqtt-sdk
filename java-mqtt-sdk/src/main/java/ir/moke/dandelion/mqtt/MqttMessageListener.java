package ir.moke.dandelion.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;

public class MqttMessageListener implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        System.out.println("Consumer connection lost : " + throwable.getMessage());
    }

    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("Message arrived from topic : " + s + " | Message : " + new String(mqttMessage.getPayload()) + " | Message ID : " + mqttMessage.getId());
        MessageListenerHandler.instance.notifyListener(Arrays.toString(mqttMessage.getPayload()));
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivery completed from : ");
    }
}
