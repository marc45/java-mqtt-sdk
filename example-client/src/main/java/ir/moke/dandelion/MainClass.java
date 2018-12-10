package ir.moke.dandelion;

public class MainClass {
    public static void main(String[] args) {
        String apiKey = "5a5898a0-3212-4898-9e99-f926827bc5cc" ;
        DandelionSDK dandelionSDK = new DandelionSDK(apiKey);
        dandelionSDK.registerMessageListener(SampleMessageListener.class);
        dandelionSDK.start().peek(e-> System.out.println("Hello Dear !"));
    }
}
