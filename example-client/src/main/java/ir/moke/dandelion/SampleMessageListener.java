package ir.moke.dandelion;

import ir.moke.dandelion.mqtt.MessageListener;

public class SampleMessageListener implements MessageListener {
    @Override
    public void onMessage(String message) {
        System.out.println("Message received : " + message);
    }
}
