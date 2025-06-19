package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    /**
     * This method is a shortcut to get a value from the default config.properties file.
     * You only need to pass the key (like "browser"), and this method will automatically use
     * the default file path (from Constants.CONFIG_FILE_PATH) to find the value.
     * Example usage: ConfigReader.read("browser");
     * → Returns: "chrome" (if config file contains browser=chrome)
     */
    public static String read(String key){
        return read(key, Constants.CONFIG_FILE_PATH);
    }

    /**
     * This method does the actual work of reading a properties file.
     * It loads the file from the provided path, looks for the given key,
     * and returns the corresponding value from the file.
     * Example usage: read("browser", "src/test/resources/config/config.properties");
     *
     * Steps:
     * - Loads the .properties file as a key-value map
     * - Finds the value that matches the key (e.g., "browser")
     * - Returns the value (e.g., "chrome")
     * @param key   The name of the property you want to read (e.g., "url", "timeout")
     * @param path  The full path to the .properties file
     * @return      The value of the given key from the file
     */


    public static String read(String key, String path){

        Properties properties=new Properties();
        try(FileInputStream fis = new FileInputStream(path))
        {
            properties.load(fis);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
