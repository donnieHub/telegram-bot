package telegram.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.net.JarURLConnection;

public class Utils {

    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final List<String> names = Arrays.asList("дмитрий", "дима", "димас", "димон", "илья", "ильюха", "dima", "ilia");
    protected static final List<String> greetings = Arrays.asList("Добрый день!", "Здравствуйте!", "Рад Вас видеть!", "Добро пожаловать!", "Доброво времени суток!", "Привет!", "Салют!", "Ты красавчик!");

    public static void initProperties(Properties property) {
        InputStream secretPropertyStream = Main.class.getClassLoader().getResourceAsStream("secret.properties");
        InputStream configPropertyStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            property.load(secretPropertyStream);
            property.load(configPropertyStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAppVersion() {
        try {
            var jarUrl = Main.class.getProtectionDomain().getCodeSource().getLocation();
            logger.info("Loaded from: " + jarUrl);

            if (jarUrl.toString().endsWith(".jar")) {
                try (JarFile jarFile = new JarFile(jarUrl.getPath())) {
                    Manifest manifest = jarFile.getManifest();
                    if (manifest != null) {
                        Attributes attributes = manifest.getMainAttributes();
                        String version = attributes.getValue("Implementation-Version"); // Или ваш ключ
                        logger.info("Version: " + version);
                    } else {
                        logger.info("Manifest not found in JAR file");
                    }
                }
            } else {
                logger.info("Not running from a JAR file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String isFriendName(String line) {
        line = line.toLowerCase(Locale.ROOT);
        if (line.split("\\s+").length > 1) {
            String[] words = line.split("\\s+");
            List<String> wordsList = Arrays.asList(words);
            List<String> friendName = wordsList.stream()
                .distinct()
                .filter(names::contains)
                .collect(Collectors.toList());
            String capitalizedName;
            try {
                capitalizedName = friendName.get(0).substring(0, 1).toUpperCase() + friendName.get(0).substring(1);
            } catch (Exception e) {
                return null;
            }
            return capitalizedName;
        } else {
            if (names.contains(line.toLowerCase(Locale.ROOT))) {
                String capitalizedName = line.substring(0, 1).toUpperCase() + line.substring(1);
                return capitalizedName;
            } else {
                return null;
            }
        }
    }
}