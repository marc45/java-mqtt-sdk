package ir.moke.javaee.mqtt;

import ir.moke.javaee.web.HttpClient;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

public class MessageConsumer implements MqttCallback {

    private static final String topicPrefix = "devices/reported";
    private static final String broker = "tcp://127.0.0.1:1883";
    private static final String clientIdPrefix = "consumer";

    public static void main(String[] args) throws MqttException {
        JSONObject authObject = HttpClient.register();
        System.out.println(authObject.toString());
        String deviceId = authObject.getString("deviceId");
        final String clientId = "d7bc9198-64e4-47c9-a815-afbbb41f39ab:" + deviceId;
//        final String clientId = "3333";
        MqttClient sampleClient = new MqttClient(broker, clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setUserName("EGhlfMqV27xKi");
//        connOpts.setPassword("2fLCAQf4MZQotXXeNm73Lal4Qs0v4fVgEV3rp04okmCr0ulrM0nManCKVMIhdHSvi".toCharArray());
        connOpts.setUserName(authObject.get("auth").toString());
        connOpts.setPassword(authObject.get("accessKey").toString().toCharArray());
        connOpts.setCleanSession(false);
        connOpts.setAutomaticReconnect(false);
        sampleClient.setCallback(new MessageConsumer());
        sampleClient.connect(connOpts);
        sampleClient.subscribe("devices/reported/consumer_1", 2);
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Consumer connection lost : " + throwable.getMessage());
    }

    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("Message arrived from topic : " + s + " | Message : " + new String(mqttMessage.getPayload()) + " | Message ID : " + mqttMessage.getId());
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivery completed from : " + clientIdPrefix + "_1");
    }
}
