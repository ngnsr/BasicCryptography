package com.crypto;

import org.jetbrains.annotations.NotNull;

public class CaesarCipher {
    private final String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890 !?.";
    public final int length = symbols.length();
    private final String message;
    private int key;

    CaesarCipher(@NotNull String message) {
        if (message.length() == 0) throw new ExceptionInInitializerError("Message is not have been initialized");
        this.message = message;
        this.key = 0;
    }

    CaesarCipher(@NotNull String message, int key) {
        this(message);
        if (key <= 0) throw new NumberFormatException("Key must be greater than 0");
        else if (key == length) throw new NumberFormatException("Encrypt is useless -> key == lengthSymbolsArr");
        else if (key > length) this.key = key % length;
        else this.key = key;
    }

    public String encrypt() {
        if (key == length) return message;
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!charContains(chars[i])) continue;
            int position = symbols.indexOf(chars[i]) + key;
            if (position >= length) position -= length;
            chars[i] = symbols.charAt(position);
        }
        return new String(chars);
    }

    private boolean charContains(char c) {
        for (char character :
                symbols.toCharArray()) {
            if (character == c) return true;
        }
        return false;
    }

    public String encrypt(int key) {
        return new CaesarCipher(message, key).encrypt();
    }

    public String decrypt() {
        if (key == length) return message;
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!charContains(chars[i])) continue;
            int position = symbols.indexOf(chars[i]) - key;
            if (position < 0) position += length;
            chars[i] = symbols.charAt(position);
        }
        return new String(chars);
    }

    public String decrypt(int key) {
        return new CaesarCipher(message, key).decrypt();
    }

    public String toString() {
        return String.format("%2s -> %s : \"%s\"", message, key, encrypt());
    }
}