package com.example.dicv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WordList {
    public static ArrayList<WordAdvanced> Words = new ArrayList<WordAdvanced>();

    public WordList() throws IOException {
        insertFromFile();
        sortDic();
    }

    public static void insertFromFile() throws FileNotFoundException {
        File dictionary = new File("dictionaries.txt");
        Scanner sc = new Scanner(dictionary, "utf-8");
        String currentLine = sc.nextLine();
        String tempLine = currentLine;
        while (sc.hasNextLine()) {
            //Danh dau cac dau: @, *, -, =
            int index;
            currentLine = tempLine;
            String word = "";
            String pronunce = "";
            String explain = "";
            String kind = "";
            String example = "";
            if (currentLine.startsWith("@")) {
                currentLine = currentLine.replace("@", "");
                String[] splits = currentLine.split("/");
                word = splits[0].trim();
                index = 4;
                if (splits.length > 1) {
                    pronunce = "/" + splits[1] + "/";
                }
                WordAdvanced newWord = new WordAdvanced(word, pronunce);
                do {
                    if (tempLine.startsWith("*")) {
                        currentLine = tempLine.replace("*", "");
                        kind = currentLine;
                        index = 3;
                    } else if (tempLine.startsWith("-")) {
                        currentLine = tempLine.replace("-", "");
                        explain = currentLine;
                        index = 2;
                    } else if (tempLine.startsWith("=")) {
                        currentLine = tempLine.replace("=", "");
                        currentLine = currentLine.replace("+", ":");
                        example = currentLine;
                        index = 1;
                    }
                    if (sc.hasNextLine()) {
                        tempLine = sc.nextLine();
                        if ((tempLine.startsWith("-") && index < 3)
                                || (tempLine.startsWith("*") && index < 4)
                                ||(tempLine.startsWith("@") && index < 5)) {
                            newWord.addExplain(kind, explain, example);
                            kind = "";
                            explain = "";
                            example = "";
                        }
                    } else {
                        newWord.addExplain(kind, explain, example);
                        kind = "";
                        explain = "";
                        example = "";
                        break;
                    }
                } while (!tempLine.startsWith("@"));
                Words.add(newWord);
            }
        }
        sc.close();
    }

    public static void sortDic() {
        Collections.sort(WordList.Words, new Comparator<WordAdvanced>() {
            @Override
            public int compare(WordAdvanced o1, WordAdvanced o2) {
                return o1.getWord().compareTo(o2.getWord());
            }
        });
    }
}
