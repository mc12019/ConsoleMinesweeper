import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameProperties {
    public void getProperties() {
        GameManager gm = new GameManager();
        Properties properties = new Properties();
        try {
            InputStream configFile = gm.getClass().getResourceAsStream("config.properties");
            properties.load(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameManager.width = Integer.parseInt(properties.getProperty("width"));
        GameManager.height = Integer.parseInt(properties.getProperty("height"));
        GameManager.mineCount = Integer.parseInt(properties.getProperty("mineCount"));
    }
}
