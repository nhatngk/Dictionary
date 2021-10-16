package com.example.dicv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WordList {
    public static HashMap<Integer,WordAdvanced> Words = new HashMap<>();

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
                Integer index = word.hashCode();
                Words.put(index,newWord);
            }
        }
        sc.close();
    }

    public static Object search(String word) {
        if (Words.containsKey(word.hashCode())) {
            return Words.get(word.hashCode());
        }
        return "";
    }

    public static void add(WordAdvanced word) {
        Words.put(word.getWord().hashCode(),word);
    }

    public static void delete(String word) {
        Words.remove(word.hashCode());
    }



    public static void main(String[] args) throws IOException {
        insertFromFileEx();

    }
}
