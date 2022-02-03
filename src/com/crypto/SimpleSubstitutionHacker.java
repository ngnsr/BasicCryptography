package com.crypto;

import org.jetbrains.annotations.NotNull;

public class SimpleSubstitutionHacker {
    private final SimpleSubstitutionCipher simpleSubstitutionCipher;
    private final String message;
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int letLength = LETTERS.length();
    private final WordChecker wordChecker = new WordChecker();

    SimpleSubstitutionHacker(@NotNull String message) {
        if (message.length() == 0) throw new ExceptionInInitializerError("Message is not have been initialized");
        this.message = message.toUpperCase();
        simpleSubstitutionCipher = new SimpleSubstitutionCipher(message);
    }

    public void test() {
        wordChecker.lettersStatistic(LETTERS, message).forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println(wordChecker.lettersByFrequency(wordChecker.lettersStatistic(LETTERS, message)));
    }
}
