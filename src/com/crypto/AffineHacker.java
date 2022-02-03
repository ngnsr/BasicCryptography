package com.crypto;

import java.util.Scanner;

public class AffineHacker {
    private final AffineCipher affineCipher;
    private final String SYMBOLS = "ABCDEFGHIJKLМNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890 !?.";
    private final int dictionaryLength = SYMBOLS.length();
    private final CryptoMath cryptoMath = new CryptoMath();
    private final WordChecker wordChecker = new WordChecker();

    public AffineHacker(AffineCipher affineCipher) {
        this.affineCipher = affineCipher;
    }

    public AffineHacker(String message) {
        this.affineCipher = new AffineCipher(message,"decrypt");
    }

    void bruteForce() {
        for (int key = 0; key < Math.pow(dictionaryLength, 2); key++) {
            long keyA = affineCipher.getKeyParts(key)[0];
            long keyB = key - keyA * dictionaryLength;
            if (cryptoMath.gcd(keyA, dictionaryLength) != 1) continue;

            String decryptMessage = affineCipher.decrypt(key);
            if (wordChecker.isItEnglishText(decryptMessage, 60, 70)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Possible encryption hack:");
                System.out.printf("Key %s: %s\n", key, decryptMessage.substring(0, (int) (decryptMessage.length()/1.5)));
                System.out.print("Enter D if done, anything else to continue hacking:\n> ");
                if (sc.hasNextLine()) {
                    if (sc.nextLine().toUpperCase().startsWith("D")) {
                        System.out.printf("KeyA: %s\nKeyB: %s\n%s", keyA, keyB, decryptMessage);
                        System.exit(0);
                    }
                }
            }
        }
    }
}