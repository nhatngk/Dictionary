package com.example.dicv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordList {
    public static ArrayList<WordAdvanced> Words = new ArrayList<WordAdvanced>();

    public static void insertFromFileEx() throws FileNotFoundException {
        File dictionary = new File("dictionaries.txt");
        Scanner sc = new Scanner(dictionary, "utf-8");
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String nextLine = currentLine;
            String word = "";
            String pronunce = "";
            String explain = "";
            String kind = "";
            if (currentLine.startsWith("@")) {
                currentLine = currentLine.replace("@", "");
                String[] splits = currentLine.split("/");
                word = splits[0].trim();
                if (splits.length > 1) {
                    pronunce = "[" + splits[1] + "]";
                }
                WordAdvanced newWord = new WordAdvanced(word);
                do {
                    if (nextLine.startsWith("*")) {
                        currentLine = nextLine.replace("*", "");
                        kind = currentLine;
                    } else if (nextLine.startsWith("-")) {
                        currentLine = nextLine.replace("-", "");
                        explain = currentLine;
                    }
                    if (sc.hasNextLine()) {
                        nextLine = sc.nextLine();
                    } else {
                        break;
                    }
                } while (!nextLine.startsWith("@"));
                newWord.addExplain(kind, pronunce, explain);
                Words.add(newWord);
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws IOException {
        insertFromFileEx();
        System.out.println(Words.size());
        System.out.println(Words.get(0).explains.size());
        System.out.println(Words.get(0).getWord());
        System.out.println(Words.get(0).explains.get(0).getPronunce());
        System.out.println(Words.get(0).explains.get(0).getKind());
        System.out.println(Words.get(0).explains.get(0).getExplain());

    }
}
