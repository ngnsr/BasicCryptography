package com.crypto;

import org.jetbrains.annotations.NotNull;

import java.io.CharArrayWriter;

public class TranspositionCipher {
    private final String message;
    private final int length;
    private int key;

    public TranspositionCipher(@NotNull String message) {
        if (message.length() == 0) throw new ExceptionInInitializerError("Massage is not have been initialized");
        this.message = message;
        this.length = message.length();
        this.key = 0;
    }

    public TranspositionCipher(@NotNull String message, int key) {
        this(message);
        if (key <= 0) throw new NumberFormatException("Key must be greater than 0");
        else if (key == length) throw new NumberFormatException("Encrypt is useless -> key == lengthSymbolsArr");
        else if (key > length) this.key = key % length;
        else this.key = key;
    }

    public String encrypt() {
        if (key == length) return message;
        char[] chars = message.toCharArray();
        CharArrayWriter translated = new CharArrayWriter(length);
        for (int i = 0; i < key; i++) {
            int curIndex = i;
            while (curIndex < length) {
                translated.append(chars[curIndex]);
                curIndex += key;
            }
        }
        return translated.toString();
    }

    public String encrypt(int key) {
        return new TranspositionCipher(message, key).encrypt();
    }

    public String decrypt() {
        if (key == length) return message;
        int numOfRows = key;
        int numOfColumns = (int) Math.ceil(length / (float) key);
        int numOfShadedBox = numOfColumns * numOfRows - length;
        String[] plaintext = new String[numOfColumns];
        for (int i = 0; i < numOfColumns; i++) {
            plaintext[i] = "";
        }
        int column = 0, row = 0;
        for (char c : message.toCharArray()) {
            plaintext[column] += String.valueOf(c);
            column += 1;
            if (column == numOfColumns || (column == numOfColumns - 1 && row >= numOfRows - numOfShadedBox)) {
                column = 0;
                row += 1;
            }
        }
        return String.join("", plaintext);
    }

    public String decrypt(int key) {
        return new TranspositionCipher(message, key).decrypt();
    }

    public String getMessage() {
        return message;
    }
}