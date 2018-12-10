package ir.moke.dandelion;

import ir.moke.dandelion.mqtt.MessagePublisher;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        String apiKey = "5a5898a0-3212-4898-9e99-f926827bc5cc" ;
        DandelionSDK dandelionSDK = new DandelionSDK(apiKey);
        dandelionSDK.registerMessageListener(SampleMessageListener.class);
        dandelionSDK.start();

        while (true) {
            System.out.print("Enter your text : ");
            Scanner scanner = new Scanner(System.in) ;
            String text = scanner.nextLine() ;
            MessagePublisher.apply(text);
        }
    }
}
