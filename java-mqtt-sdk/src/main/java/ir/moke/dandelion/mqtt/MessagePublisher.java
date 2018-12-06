/*
package ir.moke.dandelion.mqtt;

import ir.moke.dandelion.web.HttpClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.util.Random;

public class MessagePublisher {

    private static final String content = "Salam javad";
    private static final String broker = "tcp://127.0.0.1:1883";

    public static void main(String[] args) throws Exception {
        sendDataWithQOSOne();
        System.exit(0);
    }

    private static void sendDataWithQOSOne() {
        try {
            final String clientId = "2ac450f0-a875-4a75-b809-f39d84f0ee4f:2222";
//            final String clientId = "3333";
            MqttClient sampleClient = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setKeepAliveInterval(1);
            JSONObject authObject = HttpClient.register();
            connOpts.setUserName(authObject.get("auth").toString());
            connOpts.setPassword(authObject.get("accessKey").toString().toCharArray());
//            connOpts.setUserName("lLTemrxXvNjP");
//            connOpts.setPassword("ytKCJdFeLaYzmjxhowMofVRIWSUmJKDoUoUbQw8JW40FmAzb6XHjNFHAXqKFbYsu".toCharArray());
            connOpts.setCleanSession(true); // for publisher - this is not needed I think
            sampleClient.connect(connOpts);
            String msg = content + " " + String.valueOf(getRandomNumber());
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setRetained(false);
            message.setQos(2);
            final String topic = "sample";
            sampleClient.publish(topic, message);
            System.out.println("Message published from : " + clientId + " with payload of : " + msg);
//            sampleClient.disconnect();
//            sampleClient.close();
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

    private static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }
}*/
