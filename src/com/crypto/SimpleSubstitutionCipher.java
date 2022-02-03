package com.crypto;

import org.jetbrains.annotations.NotNull;

import java.io.CharArrayWriter;
import java.util.HashSet;

public class SimpleSubstitutionCipher {
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String message;
    private String key = "";

    SimpleSubstitutionCipher(@NotNull String message) {
        if (message.length() == 0) throw new ExceptionInInitializerError("Message is not have been initialized");
        this.message = message.toUpperCase();
    }

    SimpleSubstitutionCipher(@NotNull String message, @NotNull String key) {
        this(message);
        if (isKeyCorrect(key)) this.key = key;
    }

    public boolean isKeyCorrect(@NotNull String key) {
        if (key.length() != LETTERS.length()) return false;
        HashSet<Character> chars = new HashSet<>();
        for (char c :
                key.toCharArray()) {
            if (Character.isAlphabetic(c))
                chars.add(c);
        }

        return chars.size() == LETTERS.length();
    }

    public String encrypt() {
        CharArrayWriter charArrayWriter = new CharArrayWriter();

        for (char c :
                message.toCharArray()) {
            if (LETTERS.indexOf(c) != -1) {
                charArrayWriter.write(key.charAt(LETTERS.indexOf(c)));
            } else charArrayWriter.write(c);
        }
        return charArrayWriter.toString();
    }

    public String encrypt(@NotNull String key) {
        return new SimpleSubstitutionCipher(message, key).encrypt();
    }

    public String decrypt() {
        CharArrayWriter charArrayWriter = new CharArrayWriter();

        for (char c :
                message.toCharArray()) {
            if (LETTERS.indexOf(c) != -1) {
                charArrayWriter.write(LETTERS.charAt(key.indexOf(c)));
            } else charArrayWriter.write(c);
        }
        return charArrayWriter.toString();
    }

    public String decrypt(@NotNull String key) {
        return new SimpleSubstitutionCipher(message, key).decrypt();
    }
}