package ru.komarov;

import java.io.InputStream;
import java.util.Properties;

public class BotConfig {
    private static final Properties PROPERTIES = new Properties();
    public static final String INVALID_PATH_MESSAGE = "Invalid path for reading the configuration file.";

    public BotConfig(String path) {
        try (InputStream input = BotConfig.class
                .getClassLoader()
                .getResourceAsStream(path)) {
            PROPERTIES.load(input);
        } catch (Exception ex) {
            throw new RuntimeException(INVALID_PATH_MESSAGE);
        }
    }

    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }

}
