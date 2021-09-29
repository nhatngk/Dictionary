package DictCmd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
    public static void showAllWords() {
        if (! Dictionary.Words.isEmpty()) {
            System.out.printf("%-4s| %-20s| Vietnamese\n", "No", "English");
            int i = 1;
            for (Word word : Dictionary.Words) {
                System.out.printf("%-4d| %-20s| %s\n", i, word.getWord_target(), word.getWord_explain());
                i++;
            }
        } else {
            System.out.println("Khong co tu nao trong tu dien.");
        }
    }

    public static void dictionaryBasic() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Tu dien Anh - Viet");
            System.out.println("Nhap 1 de them tu vao tu dien.");
            System.out.println("Nhap 2 de xem tat ca tu trong tu dien.");
            System.out.println("Nhap 0 de thoat.");
            System.out.print("Moi nhap lua chon: ");
            do {
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } while (option < 0 || option > 2);
            switch (option) {
                case 0:
                    return;
                case 1:
                    DictionaryManagement.insertFromCommandline();
                    break;
                case 2:
                    showAllWords();
                    break;
            }

        } while (option >= 0 && option <=2 );
    }

    public static void dictionaryAdvanced() throws IOException {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Tu dien Anh - Viet");
            System.out.println("Nhap 1 ghi du lieu vao tu dien.");
            System.out.println("Nhap 2 de xem tat ca tu trong tu dien.");
            System.out.println("Nhap 3 de tim kiem tu hoan chinh trong tu dien.");
            System.out.println("Nhap 4 de them tu vao tu dien.");
            System.out.println("Nhap 5 de thay doi tu trong tu dien.");
            System.out.println("Nhap 6 de xoa tu trong tu dien.");
            System.out.println("Nhap 7 de tim kiem tu trong tu dien.");
            System.out.println("Nhap 8 de luu du lieu vao file.");
            System.out.println("Nhap 0 de thoat.");
            System.out.print("Moi nhap lua chon: ");
            do {
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } while (option < 0 || option > 8);
            switch (option) {
                case 0:
                    return;
                case 1:
                    DictionaryManagement.insertFromFile();
                    break;
                case 2:
                    showAllWords();
                    break;
                case 3:
                    System.out.println("Nhap tu can tim:");
                    String word = sc.nextLine();
                    System.out.println(DictionaryManagement.dictionaryLookup(word));
                    break;
                case 4:
                    DictionaryManagement.insertAWordCmd();
                    break;
                case 5:
                    DictionaryManagement.changeWord();
                    break;
                case 6:
                    DictionaryManagement.removeWord();
                    break;
                case 7:
                    DictionaryManagement.searchWord();
                    break;
                case 8:
                    DictionaryManagement.dictionaryExportToFile();
                    break;
            }

        } while (option >= 0 && option <= 8);
    }

    public static void main(String[] args) throws IOException {
        dictionaryAdvanced();
    }
}

