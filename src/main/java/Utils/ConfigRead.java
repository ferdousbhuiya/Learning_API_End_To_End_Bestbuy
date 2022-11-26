package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {

    public static Properties readConfigProperties(String fileName) throws IOException {
        fileName = fileName.trim();

        Properties configProperties = new Properties();
        InputStream inStream = new FileInputStream(fileName);
        configProperties.load(inStream);
        return configProperties;
    }
}
