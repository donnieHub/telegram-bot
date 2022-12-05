package telegram.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringUtils {

    private static final List<String> names = Arrays.asList("дмитрий", "дима", "димас", "димон", "илья", "ильюха", "dima", "ilia");
    protected static final List<String> filthyWords = Arrays.asList("Пашел нахуй!", "Пшел вон!", "Иди в пизду!", "Иди нахуй и там погибни!", "Иди на три хуя сразу!", "Сдохни гнида!", "Нахуй иди!", "Ты уёбок!");


    public static String isFriendName(String line) {
        line = line.toLowerCase(Locale.ROOT);
        if (line.split("\\s+").length > 1) {
            String[] words = line.split("\\s+");
            List<String> wordsList = Arrays.asList(words);
            List<String> friendName = wordsList.stream()
                .distinct()
                .filter(names::contains)
                .collect(Collectors.toList());
            String capitalizedName = null;
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
