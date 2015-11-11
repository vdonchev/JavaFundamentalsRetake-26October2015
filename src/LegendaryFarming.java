import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegendaryFarming {
    private static Map<String, Integer> keyMaterials;
    private static Map<String, Integer> junkItems;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        junkItems = new TreeMap<>();
        keyMaterials = new TreeMap<>();
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        keyMaterials.put("shards", 0);
        Pattern pattern = Pattern.compile("(\\d+)\\s+([^\\s]+)");
        while (true) {
            String tokens = scanner.nextLine().toLowerCase();
            Matcher matcher = pattern.matcher(tokens);
            while (matcher.find()) {
                String itemName = matcher.group(2);
                int itemCount = Integer.parseInt(matcher.group(1));
                if (keyMaterials.containsKey(itemName)) {
                    int currentItemCount = keyMaterials.get(itemName);
                    keyMaterials.put(itemName, currentItemCount + itemCount);
                    if (itemCount + currentItemCount >= 250) {
                        keyMaterials.put(itemName, itemCount + currentItemCount - 250);
                        String legendaryItem = getLegendaryItem(itemName);
                        printFinalResults(legendaryItem);
                        return;
                    }
                } else {
                    if (!junkItems.containsKey(itemName)) {
                        junkItems.put(itemName, 0);
                    }

                    int currentJunkItemCount = junkItems.get(itemName);
                    junkItems.put(itemName, currentJunkItemCount + itemCount);
                }
            }
        }
    }

    private static String getLegendaryItem(String itemName) {
        String legendaryItem;
        if (itemName.equals("shards")) {
            legendaryItem = "Shadowmourne";
        } else if (itemName.equals("fragments")) {
            legendaryItem = "Valanyr";
        } else {
            legendaryItem = "Dragonwrath";
        }
        return legendaryItem;
    }

    private static void printFinalResults(String legendaryItem) {
        System.out.printf("%s obtained!\n", legendaryItem);
        keyMaterials.keySet().stream()
                .sorted((item1, item2) -> keyMaterials.get(item2).compareTo(keyMaterials.get(item1)))
                .forEach(s -> System.out.printf("%s: %d\n", s, keyMaterials.get(s)));

        junkItems.keySet().stream()
                .forEach(s -> System.out.printf("%s: %d\n", s, junkItems.get(s)));
    }
}