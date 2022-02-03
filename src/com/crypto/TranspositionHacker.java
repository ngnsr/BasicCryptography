package com.crypto;

import java.util.Scanner;

public class TranspositionHacker {
    private final TranspositionCipher tc;
    private final String message;
    private final int length;

    public TranspositionHacker(TranspositionCipher tc) {
        this.tc = tc;
        this.message = tc.getMessage();
        this.length = message.length();
    }

    public TranspositionHacker(String message) {
        this.tc = new TranspositionCipher(message);
        this.message = message;
        this.length = message.length();
    }

    void bruteForce() {
        WordChecker wordChecker = new WordChecker();
        String hack = "";
        for (int key = 2; key < length; key++) {
            String hash = tc.decrypt(key);
            Scanner sc = new Scanner(System.in);
            if (wordChecker.isItEnglishText(hash, 20, 60)) {
                System.out.println("Possible encryption hack:");
                System.out.printf("Key %s: %s\n", key, hash.substring(0, 10));
                System.out.print("Enter D if done, anything else to continue hacking:\n> ");
                if (sc.hasNextLine()) {
                    if (sc.nextLine().toUpperCase().startsWith("D")) {
                        hack = hash;
                        break;
                    }
                }
            }
        }
        System.out.println();
        System.out.println(hack);
    }
}