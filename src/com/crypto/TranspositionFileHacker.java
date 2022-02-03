package com.crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TranspositionFileHacker {
    private File file;

    public TranspositionFileHacker(String pathToFile) {
        File file = new File(pathToFile);
        if (file.exists()) this.file = file;
    }

    public TranspositionFileHacker(File file) {
        if (file.exists()) this.file = file;
    }

    public void bruteForce() {
        String text = readFile();
        new TranspositionHacker(text);
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) sb.append(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}