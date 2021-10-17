package Object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordList {
    public static HashMap<Integer , WordAdvanced> Words = new HashMap<>();
    public static Trie trie = new Trie();

    public WordList() throws IOException {
        insertFromFile();
    }

    public static void exportToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("dictionaries.txt");
            for (Map.Entry<Integer, WordAdvanced> entry : Words.entrySet()) {
                WordAdvanced wordAdvanced = entry.getValue();
                String text = "@" +wordAdvanced.getWord();
                text += " " + wordAdvanced.getPronounce() +"\n";
                for (Explain explain : wordAdvanced.explains) {
                    if (! explain.getKind().equals("")) {
                        text = text + "*" + explain.getKind() + "\n";
                    }
                    if (! explain.getExplain().equals("")) {
                        text = text + "-" + explain.getExplain() + "\n";
                    }
                    if (! explain.getExample().equals("")) {
                        text = text + "=" + explain.getExample().replace(":", "+") + "\n";
                    }
                }
                fileWriter.write(text);
                fileWriter.close();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String pronounce = "";
            String explain = "";
            String kind = "";
            String example = "";
            if (currentLine.startsWith("@")) {
                currentLine = currentLine.replace("@", "");
                String[] splits = currentLine.split("/");
                word = splits[0].trim();
                index = 4;
                if (splits.length > 1) {
                    pronounce = "/" + splits[1] + "/";
                }
                WordAdvanced newWord = new WordAdvanced(word, pronounce);
                trie.insert(word);
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
                Integer hashCode = word.hashCode();
                add(newWord);
            }
        }
        sc.close();
    }

    public static void delete(String word) {
        if (Words.containsKey(word.hashCode())) {
            Words.remove(word.hashCode());
        }
    }

   public static void add(WordAdvanced wordAdvanced) {
        int hashCode = wordAdvanced.getWord().hashCode();
        if (Words.get(hashCode) != null) {
            Words.get(hashCode).addAllExplain(wordAdvanced);
            return;
        }
        Words.put(hashCode, wordAdvanced);
        trie.insert(wordAdvanced.getWord());
   }

   public static void change(WordAdvanced wordAdvanced) {
        Words.replace(wordAdvanced.getWord().hashCode(),wordAdvanced);
   }
}
