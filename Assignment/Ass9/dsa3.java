import java.util.HashMap;
import java.util.Map;

public class dsa3 {

    static Map.Entry<String, Integer> mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String item : orders) {
            counts.merge(item, 1, Integer::sum);
        }
        String bestItem = null;
        int bestCount = 0;
        for (String item : orders) {
            int count = counts.get(item);
            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }
        return Map.entry(bestItem, bestCount);
    }

    static String format(Map.Entry<String, Integer> result) {
        return "(\"" + result.getKey() + "\", " + result.getValue() + ")";
    }

    public static void main(String[] args) {
        System.out.println(format(mostPopular(new String[]{"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"})));
        System.out.println(format(mostPopular(new String[]{"tea", "coffee", "coffee", "tea"})));
    }
}
