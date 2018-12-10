package ir.moke.dandelion.mqtt;

import ir.moke.dandelion.DandelionCredentialFactory;
import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.logging.Logger;

public class MqttFactory implements Subject {

    private static final Logger logger = LoggerProducer.produceLogger();
    private static MqttClient mqttClient;
    private MessageListener messageListener;

    public static final MqttFactory instance = new MqttFactory();

    private MqttFactory() {
    }

    public MqttClient connect(String apiKey, String endpoint) {
        try {
            Credential credential = DandelionCredentialFactory.getCredential();
            final String clientId = apiKey + ":" + credential.getDeviceId();
            mqttClient = new MqttClient(endpoint, clientId);
            mqttClient.connect(getOptions(credential));
            mqttClient.setCallback(new MqttMessageListener());
            mqttClient.subscribe("device/sample", 2);
            logger.info("Connection established .");
        } catch (Exception e) {
            logger.fine("Exception : " + e.getMessage());
        }
        return mqttClient;
    }

    public void disconnect() {
        try {
            mqttClient.disconnect();
            mqttClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MqttConnectOptions getOptions(Credential credential) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(credential.getAuth());
        connOpts.setPassword(credential.getAccessKey().toCharArray());
        connOpts.setCleanSession(true);
        connOpts.setAutomaticReconnect(false);
        return connOpts;
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

    public void send(String message) {
        try {
//            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
//            mqttMessage.setRetained(false);
//            mqttMessage.setQos(2);
            final String topic = "mqtt";
            mqttClient.publish(topic, message.getBytes(),2,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
