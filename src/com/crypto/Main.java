package com.crypto;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        WordChecker wordChecker = new WordChecker();
        wordChecker.lettersStatistic("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "Hello")
                .entrySet().forEach(System.out::println);
        SimpleSubstitutionHacker hacker = new SimpleSubstitutionHacker("AJFRF");
        hacker.test();

    }
}