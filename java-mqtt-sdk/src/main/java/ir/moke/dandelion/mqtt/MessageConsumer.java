package ir.moke.dandelion.mqtt;

import ir.moke.dandelion.DandelionCredentialFactory;
import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.util.logging.Logger;

public class MessageConsumer {

    private static final Logger logger = LoggerProducer.produceLogger();
    private static MqttClient mqttClient;

    public static MqttClient connect(String apiKey, String endpoint) {
        try {
            Credential credential = DandelionCredentialFactory.getCredential();
            final String clientId = apiKey + ":" + credential.getDeviceId();
            mqttClient = new MqttClient(endpoint, clientId);
            mqttClient.connect(getOptions(credential));
            mqttClient.setCallback(new MqttMessageListener());
            mqttClient.subscribe("device/mqtt", 2);
            logger.info("Connection established .");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mqttClient;
    }

    public static void disconnect() {
        try {
            mqttClient.disconnect();
            mqttClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static MqttConnectOptions getOptions(Credential credential) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(credential.getAuth());
        connOpts.setPassword(credential.getAccessKey().toCharArray());
        connOpts.setCleanSession(false);
        connOpts.setAutomaticReconnect(false);
        return connOpts;
    }
}
