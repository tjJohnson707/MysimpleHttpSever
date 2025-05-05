package config;

import com.coderfromscratch.httpserver.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myconfigurationManager;
    private static Configuration mycurrentconfiguration;

    // Private constructor
    private ConfigurationManager() {
    }

    // Singleton accessor
    public static ConfigurationManager getInstance() {
        if (myconfigurationManager == null) {
            myconfigurationManager = new ConfigurationManager();
        }
        return myconfigurationManager;
    }

    /**
     * Used to load a configuration file by the path provided
     */
    public void loadConfigurationFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException("Error reading configuration file", e);
        }

        System.out.println("Loading config file: " + filePath);
        JsonNode conf;
        try {
            conf = Json.parse(stringBuilder.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing JSON", e);
        }

        try {
            mycurrentconfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error mapping JSON to Configuration class", e);
        }
    }

    /**
     * Returns the current loaded configuration
     */
    public Configuration getCurrentConfiguration() {
        if (mycurrentconfiguration == null) {
            throw new HttpConfigurationException("No current configuration set.");
        }
        return mycurrentconfiguration;
    }
}
