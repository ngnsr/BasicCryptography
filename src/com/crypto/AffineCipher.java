package com.crypto;

import org.jetbrains.annotations.NotNull;

import java.io.CharArrayWriter;

public class AffineCipher {
    private final String SYMBOLS = "ABCDEFGHIJKLМNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890 !?.";
    private final CryptoMath cryptoMath = new CryptoMath();
    private final long length = SYMBOLS.length();
    private final String message;
    private long key;

    AffineCipher(@NotNull String message) {
        if (message.length() == 0) throw new ExceptionInInitializerError("Message is not have been initialized");
        this.message = message;
        this.key = 0;

    }

    AffineCipher(@NotNull String message, String mode){
        this(message);
    }

    AffineCipher(@NotNull String message, long key,String mode) {
        this(message);
        if (!isKeysCorrect(getKeyParts(key)) && mode.equals("encrypt")) throw new NumberFormatException();
        this.key = key;
    }

    public String encrypt() {
        long[] keys = getKeyParts(key);
        long keyA = keys[0], keyB = keys[1];
        StringBuilder sb = new StringBuilder(message.length());
        for (char c : message.toCharArray()) {
            if (SYMBOLS.indexOf(c) != -1) {
                int charIndex = SYMBOLS.indexOf(c);
                sb.append(SYMBOLS.charAt((int) ((charIndex * keyA + keyB) % length)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String encrypt(long key) {
        return new AffineCipher(message, key,"encrypt").encrypt();
    }

    public String decrypt() {
        long[] keys = getKeyParts(key);
        long keyA = keys[0], keyB = keys[1];
        CharArrayWriter charArrayWriter = new CharArrayWriter((int) length);
        Long modInverse = cryptoMath.findModInverse(keyA, length);
        if (modInverse == null) return "Mode inverse is null";
        char[] chars = message.toCharArray();
        for (char c : chars) {
            if (SYMBOLS.indexOf(c) != -1) {
                int charIndex = SYMBOLS.indexOf(c);
                int newChar = (int) (((charIndex - (int) keyB) * modInverse) % length);
                charArrayWriter.append((newChar < 0) ? SYMBOLS.charAt((int) (length + newChar)) : SYMBOLS.charAt(newChar));
            } else {
                charArrayWriter.append(c);
            }
        }
        return charArrayWriter.toString();

    }

    /*public String decrypt(long key) {
        return new AffineCipher(message, key).decrypt();
    }*/

    public String decrypt(long key){
        return new AffineCipher(message,key,"decrypt").decrypt();
    }

    public void getAndSetRandomKey() {
        setKey(getRandomKey());
    }

    public String getMessage() {
        return message;
    }

    public long getRandomKey() {
        while (true) {
            long range = length - 2;
            long keyA = (long) (Math.random() * range + 2);
            long keyB = (long) (Math.random() * range + 2);
            if (isKeysCorrect(new long[]{keyA, keyB}))
                return keyA * length + keyB;
        }
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long[] getKeyParts(long key) {
        long keyA = key / length;
        long keyB = key % length;

        return new long[]{keyA, keyB};
    }

    private boolean isKeysCorrect(long @NotNull [] keys) {
        long keyA = keys[0];
        long keyB = keys[1];

        if (keyA == 1 || keyB == 0) {
            return false;
        } else if (keyA < 0 || keyB < 0 || keyB > length - 1) {
            return false;
        } else return cryptoMath.gcd(keyA, length) == 1;
    }

    public String toString() {
        if (key == 0) return String.format("Key: %s\nMessage: %s", key, message);
        return String.format("Key: %s\nMessage: %s\nEncrypt: %s\nDecrypt: %s",
                key,
                message,
                encrypt(),
                decrypt());
    }
}