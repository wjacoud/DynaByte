package app.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesController {

    private PropertiesController() {
        //
    }

    public static Properties getProperties() {
        Properties proper = new Properties();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            proper.load(inputStream);
            inputStream.close();
            return proper;
        } catch (Exception error) {
            return null;
        }
    }
}
