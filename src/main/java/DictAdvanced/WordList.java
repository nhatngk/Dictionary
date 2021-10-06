package DictAdvanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordList {
    public static ArrayList<WordAdvanced> Words = new ArrayList<>();

    public static void insertFromFile() throws FileNotFoundException {
        File dictionary = new File("dictionaries.txt");
        Scanner sc = new Scanner(dictionary,"utf-8");
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String nextLine = sc.nextLine();
            String word = "";
            String pronunce = "";
            String explain = "";
            while (! nextLine.equals("")) {
                if (currentLine.startsWith("@")) {
                    currentLine = currentLine.replace("@","");
                    String[] splits = currentLine.split("/");
                    word = splits[0].trim();
                    if (splits.length > 1) {
                        pronunce = "[" + splits[1] + "]";
                    }
                } else {
                    explain += handleLine(currentLine);
                }
                currentLine = nextLine;
                if (sc.hasNextLine()) {
                    nextLine = sc.nextLine();
                }
            }
            WordAdvanced newWord = new WordAdvanced(word, pronunce, explain);
            WordList.Words.add(newWord);
        }
        sc.close();
    }

    public static String handleLine(String line) {
        if(line.startsWith("*")) {
            line = line.replace("*","");
            line = line.trim() + "\n";
            return line;
        }

        if (line.startsWith("-")) {
            line = line.replace("-","") + "\n";
            return line;
        }

        if (line.startsWith("=")) {
            line = line.replace("=","   ");
            line = line.replace("+","\n   ") + "\n";
            return line;
        }
        return line;
    }

    public static void main(String[] args) throws FileNotFoundException {
        insertFromFile();
        for (WordAdvanced wordAdvanced : WordList.Words) {
            System.out.println(wordAdvanced.getWord());
            System.out.println(wordAdvanced.getPronunce());
            System.out.println(wordAdvanced.getExplain());
        }
    }
}