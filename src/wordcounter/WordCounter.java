package wordcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'text' to input text or 'file' to provide a file:");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("text")) {
            System.out.println("Enter the text:");
            String text = scanner.nextLine();
            countWords(text);
        } else if (choice.equalsIgnoreCase("file")) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                StringBuilder textBuilder = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    textBuilder.append(fileScanner.nextLine()).append("\n");
                }
                String text = textBuilder.toString();
                countWords(text);
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            System.out.println("Invalid choice.");
            System.exit(0);
        }

        scanner.close();
    }

    public static void countWords(String text) {
        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;
        System.out.println("Total words: " + wordCount);

        // Count unique words and frequency
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
        }

        int uniqueWords = wordFrequency.size();
        System.out.println("Unique words: " + uniqueWords);

        // Display frequency of each word
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
