package com.crypto;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class CaesarHacker {
    private final CaesarCipher cc;
    private final int length;

    CaesarHacker(@NotNull CaesarCipher caesarCipher){
        this.cc = caesarCipher;
        this.length = caesarCipher.length;
    }

    CaesarHacker(String cipher) {
        this.cc = new CaesarCipher(cipher);
        this.length = cc.length;
    }

    void bruteForce() {
        WordChecker wordChecker = new WordChecker();
        String hack = "";
        for (int key = 1; key < length; key++) {
            String hash = cc.decrypt();
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
            System.out.println(hack);
        }
    }
}