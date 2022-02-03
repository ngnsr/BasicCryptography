package com.crypto;

import java.io.*;
import java.util.Scanner;

public class TranspositionFileCipher {
    private final String pathToFile;
    private final int key;

    public TranspositionFileCipher(String pathToFile, int key) {
        this.pathToFile = pathToFile;
        this.key = key;
    }

    public void encryptFile() {
        String content = readFile();
        File file = new File(pathToFile);
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.append(new TranspositionCipher(content, key).encrypt());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decryptFile() {
        String content = readFile();
        File file = new File(pathToFile);
        try {
            FileWriter fw = new FileWriter(file, false);
            fw.append(new TranspositionCipher(content, key).decrypt());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(pathToFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append('\n');
            }
            sc.close();
        } catch (IOException e) {
            System.err.println("File does not exist");
        }
        return sb.toString();
    }
}