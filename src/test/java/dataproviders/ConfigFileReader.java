package dataproviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;
    private static ConfigFileReader configReader;

    private ConfigFileReader() {
        BufferedReader reader;
        String propertyFilePath = "configs//configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties file not found at: " + propertyFilePath);
        }
    }

    public static ConfigFileReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigFileReader();
        }
        return configReader;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if (baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in  configuration.properties file.");
    }

    public String getUserToken() {
        String userId = properties.getProperty("user_token");
        if (userId != null) return userId;
        else throw new RuntimeException("user_token not specified in configuration.properties file.");
    }
}
