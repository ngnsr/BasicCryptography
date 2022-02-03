package com.crypto;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class WordChecker {
    private final ArrayList<String> dictionary = new ArrayList<>();
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    WordChecker() {
        File file = new File("C:\\Users\\rsngn\\IdeaProjects\\BasicCryptography\\dictionary.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                dictionary.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isEnglishWord(String word) {
        return dictionary.contains(word);
    }

    public boolean isItEnglishText(String text, int wordPercentage, int letterPercentage) {
        int foundLetterPercentage = letterPercentage(text);
        int foundWordPercentage = wordPercentage(possibleWords(text));

        return foundLetterPercentage >= letterPercentage & foundWordPercentage >= wordPercentage;
    }

    public String[] possibleWords(String text) {
        // eliminate leading and trailing spaces
        text = text.trim().toUpperCase();

        // split all non-alphabetic characters
        String delims = "\\W+"; // split any non word

        return text.split(delims);
    }


    public int wordPercentage(String[] words) {
        if (words.length == 0) return 0;
        int foundWords = 0;
        for (String word :
                words) {
            if (isEnglishWord(word)) {
                foundWords++;
            }
        }

        return (int) ((foundWords / (double) words.length) * 100);
    }

    public int letterPercentage(String text) {
        if (text.length() == 0) return 0;
        int letterFound = 0;
        char[] chars = text.toCharArray();
        for (char symbol : chars) {
            if (Character.isAlphabetic(symbol)) {
                letterFound++;
            }
        }

        return (int) ((letterFound / (double) text.length()) * 100);
    }

    public HashMap<Character, Integer> lettersStatistic(@NotNull String alphabet,@NotNull String text) {
        HashMap<Character, Integer> chars = new HashMap<>();
        for (char c :
                alphabet.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                chars.put(c, 0);
            }
        }

        for (char c :
                text.toCharArray()) {
            if (chars.containsKey(c)) {
                chars.replace(c, chars.get(c) + 1);
            }
        }

        HashMap<Character, Integer> sortChars = chars.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortChars;
    }

    public String lettersByFrequency(HashMap<Character, Integer> charsStatistic) {
        StringBuilder writer = new StringBuilder(35);
        for (Map.Entry<Character, Integer> entry :
                charsStatistic.entrySet()) {
            writer.append(entry.getKey());
        }
        return writer.reverse().toString();
    }
}