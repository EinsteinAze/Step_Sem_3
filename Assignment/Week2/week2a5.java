import java.util.*;

public class week2a5 {

    void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        Set<String> stopWordSet = new HashSet<>(Arrays.asList(stopWords));

        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.split("\\s+");

        LinkedHashMap<String, Integer> frequency = new LinkedHashMap<>();
        for (String word : words) {
            if (word.isEmpty() || stopWordSet.contains(word)) continue;
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        week2a5 report = new week2a5();
        report.printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
