package com.coherentsolutions.store;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("store/src/main/resources/config.properties");
        properties.load(fis);
        fis.close();
        return properties;
    }
}
