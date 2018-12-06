package ir.moke.dandelion;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class DandelionCredentialFactory {
    private static final Logger logger = LoggerProducer.produceLogger();
    private static Properties properties = new Properties();
    private static final String configFile = ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE;

    public static void initialize() throws Exception {
        if (!configurationFileExist()) {
            logger.info("Initialize client .");
            registerDevice();
            storeToken();
        } else {
            logger.info("Configuration file already exist");
        }
    }

    private static boolean configurationFileExist() {
        File file = new File(configFile);
        return file.exists();
    }

    private static void registerDevice() throws Exception {
        logger.info("Register new client on server .");
        HttpResponse<JsonNode> jsonResponse = Unirest
                .get(ClientConfig.HTTP_ENDPOINT + ClientConfig.AUTH_URI)
                .asJson();
        JSONObject jsonObject = jsonResponse.getBody().getObject();
        String auth = jsonObject.getString("auth");
        String accessKey = jsonObject.getString("accessKey");
        String deviceId = jsonObject.getString("deviceId");
        properties.setProperty("auth", auth);
        properties.setProperty("accessKey", accessKey);
        properties.setProperty("deviceId", deviceId);
    }

    private static void storeToken() {

        File filePath = new File(ClientConfig.STORAGE_DIRECTORY);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            String clientConfigFile = ClientConfig.STORAGE_DIRECTORY + ClientConfig.CLIENT_CONFIG_FILE;
            logger.info("Store token on " + clientConfigFile);
            FileWriter fileWriter = new FileWriter(clientConfigFile);
            properties.store(fileWriter, "Dandelion Client Configuration");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Credential getCredential() {
        Credential credential = new Credential();
        try {
            properties.load(new FileReader(configFile));
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        credential.setAuth(properties.getProperty("auth"));
        credential.setAccessKey(properties.getProperty("accessKey"));
        credential.setDeviceId(properties.getProperty("deviceId"));
        return credential;
    }

    public static void destroyToken() {
        if (configurationFileExist()) {
            File file = new File(configFile);
            logger.warning("Token destroyed");
            file.delete();
        }
    }
}
