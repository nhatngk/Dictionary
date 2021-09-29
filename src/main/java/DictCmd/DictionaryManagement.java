package DictCmd;


import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;


public class DictionaryManagement {
    public static void insertFromCommandline() {
        System.out.println("Nhap so tu muon nhap:");
        Scanner sc = new Scanner(System.in);
        int num = 0;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < num; i++) {
            System.out.print("Nhap tu tieng Anh:");
            String target = sc.nextLine();
            boolean check = false;
            for (Word value : Dictionary.Words) {
                if (value.getWord_target().equals(target.toLowerCase())) {
                    System.out.println("Da co tu nay trong tu dien. Moi nhap lai:");
                    check = true;
                    i--;
                    break;
                }
            }
            if (! check) {
                System.out.print("Nhap nghia tieng Viet:");
                String explain = sc.nextLine();
                Dictionary.Words.add(new Word(target.toLowerCase(),explain));
                System.out.println("Da them tu " + target.toLowerCase() + " vao tu dien.");
            }
        }
    }

    public static void insertFromFile() throws FileNotFoundException {
        File dictionary = new File("dictionaries.txt");
        Scanner sc = new Scanner(dictionary,"utf-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] values = line.split("\t");
            Word word = new Word(values[0],values[1]);
            Dictionary.Words.add(word);
        }
        sc.close();
        Collections.sort(Dictionary.Words);
        System.out.println("Da nhap du lieu thanh cong");
    }

    public static int binarySearch(String word) {
        if(Dictionary.Words.size() == 0) {
            return -1;
        }
        int right = Dictionary.Words.size()-1;
        int left = 0;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (word.compareTo(Dictionary.Words.get(mid).getWord_target()) == 0) {
                return mid;
            }
            if (word.compareTo(Dictionary.Words.get(mid).getWord_target()) < 0) {
                right = mid - 1;
            }
            if (word.compareTo(Dictionary.Words.get(mid).getWord_target()) > 0) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static String dictionaryLookup(String wordLookUp) {
        int index = binarySearch(wordLookUp);
        if (index < 0 ){
            return "Khong tim thay tu "  + wordLookUp + " trong tu dien";
        } else {
            return Dictionary.Words.get(index).getWord_explain();
        }
    }

    public static void insertAWordCmd() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu tieng Anh:");
        String target = sc.nextLine();
        boolean check = false;
        do {
            check = false;
            int index = binarySearch(target);
            if (index >= 0 ) {
                check = true;
                System.out.println("Da co tu nay trong tu dien. Moi nhap lai:");
                target = sc.nextLine();
            } else {
                break;
            }
        } while (check);
        System.out.print("Nhap nghia tieng Viet:");
        String explain = sc.nextLine();
        Dictionary.Words.add(new Word(target.toLowerCase(),explain));
        Collections.sort(Dictionary.Words);
        System.out.println("Da them tu " + target.toLowerCase() + " vao tu dien.");
    }

    public static void changeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu muon sua:");
        String target = sc.nextLine();
        int index = binarySearch(target);
        if (index < 0) {
            System.out.println("Chua co tu " + target + "trong tu dien.Ban co muon them vao tu dien? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                insertAWordCmd();
            } else if (c == 'N') {
                return;
            }
        } else {
            String oldWord = Dictionary.Words.get(index).getWord_target();
            System.out.println("Ban co muon sua tu tieng Anh? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                System.out.println("Sua tu tieng Anh:");
                String changedWord = sc.nextLine();
                boolean check = false;
                do {
                    check = false;
                    int index1 = binarySearch(changedWord);
                    if (index1 >= 0) {
                        check = true;
                        System.out.println("Da co tu nay trong tu dien. Moi nhap lai:");
                        changedWord = sc.nextLine();
                    } else {
                        break;
                    }
                } while (check);
                System.out.print("Nhap nghia tieng Viet:");
                String explain = sc.nextLine();
                Dictionary.Words.get(index).setWord_target(changedWord.toLowerCase());
                Dictionary.Words.get(index).setWord_explain(explain);
                Collections.sort(Dictionary.Words);
                System.out.println("Da thay tu " + oldWord + " thanh tu " + changedWord.toLowerCase());
            } else if (c == 'N') {
                System.out.print("Nhap nghia tieng Viet:");
                String explain = sc.nextLine();
                Dictionary.Words.get(index).setWord_explain(explain);
                Collections.sort(Dictionary.Words);
                System.out.println("Da thay doi nghia cua tu " + oldWord);
            }
        }
    }

    public static void removeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu muon xoa:");
        String target = sc.nextLine();
        int index = binarySearch(target);
        if (index < 0) {
            System.out.println("Chua co tu " + target + "trong tu dien.");
        } else {
            System.out.println("Ban co chac muon xoa tu "+ target + "? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                Dictionary.Words.remove(index);
                Collections.sort(Dictionary.Words);
                System.out.println("Da xoa tu " + target + " khoi tu dien.");
            } else if (c == 'N') {
                return;
            }
        }
    }

    public static void searchWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu muon tim kiem:");
        String target = sc.nextLine();
        int num = 0;
        for (Word word : Dictionary.Words) {
            if (word.getWord_target().startsWith(target)) {
                System.out.println(word.getWord_target());
                num++;
            }
        }
        if (num == 0) {
            System.out.println("Khong co tu nao bat dau bang \""+ target + "\"");
        }
    }

    public static void dictionaryExportToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("dictionaries.txt");
        for (Word word : Dictionary.Words) {
            fileWriter.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
        }
        fileWriter.close();
        System.out.println("Da ghi du lieu vao file.");
    }
}

