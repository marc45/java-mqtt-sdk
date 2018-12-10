package ir.moke.dandelion;

import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.mqtt.MessageListener;
import ir.moke.dandelion.mqtt.MqttFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;

import java.util.logging.Logger;

public class DandelionSDK {
    private static final Logger logger = LoggerProducer.produceLogger();

    private String endpoint = "tcp://localhost:1883";
    private String apiKey;

    public DandelionSDK(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void registerMessageListener(Class<? extends MessageListener> messageListener) {
        MqttFactory.instance.registerMessageListener(messageListener);
    }

    private void init() throws Exception {
        DandelionCredentialFactory.initialize();
    }

    public void start() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    init();
                    MqttClient mqttClient = MqttFactory.instance.connect(apiKey, endpoint);
                    if (mqttClient.isConnected()) {
                        logger.info("Connection established .");
                        while (mqttClient.isConnected()) {
                        }
                    } else {
                        DandelionCredentialFactory.destroyToken();
                    }
                } catch (Exception e) {
                    logger.fine("Exception : " + e.getMessage());
                }
                sleep(5000);
            }
        });
        thread.start();
    }

    public void stop() {
        MqttFactory.instance.disconnect();
    }

    private void sleep(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
