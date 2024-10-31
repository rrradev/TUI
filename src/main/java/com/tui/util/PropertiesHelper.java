package com.tui.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private final Properties properties = new Properties();

    public PropertiesHelper(String path) throws IOException {
        InputStream input = new FileInputStream(path);
        properties.load(input);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
