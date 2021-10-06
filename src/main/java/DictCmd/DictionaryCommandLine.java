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

        } while (option >= 0 && option <=2);
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        Scanner sc = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Từ điển Anh - Việt");
            System.out.println("Nhập 1 Để xem tất cả các từ trong từ điển.");
            System.out.println("Nhập 2 Để tìm kiếm từ hoàn chỉnh trong từ điển.");
            System.out.println("Nhập 3 Để thêm từ vào từ điển.");
            System.out.println("Nhập 4 Để thêm nhiều từ vào từ điển.");
            System.out.println("Nhập 5 Để thay đổi từ trong từ điển.");
            System.out.println("Nhập 6 Để xóa từ trong từ điển.");
            System.out.println("Nhập 7 để tìm kiếm từ trong từ điển.");
            System.out.println("Nhập 0 để thoát.");
            System.out.print("Mời nhập lựa chọn: ");
            do {
                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } while (option < 0 || option > 7);
            switch (option) {
                case 0:
                    DictionaryManagement.dictionaryExportToFile();
                    return;
                case 1:
                    showAllWords();
                    break;
                case 2:
                    System.out.println("Nhap tu can tim:");
                    String word = sc.nextLine();
                    System.out.println(DictionaryManagement.dictionaryLookup(word));
                    break;
                case 3:
                    DictionaryManagement.insertAWordCmd();
                    break;
                case 4:
                    DictionaryManagement.insertFromCommandline();
                    break;
                case 5:
                    DictionaryManagement.changeWord();
                    break;
                case 6:
                    DictionaryManagement.removeWord();
                    break;
                case 7:
                    dictionarySearch();
                    break;
            }

        } while (option >= 0 && option <= 7);
    }

    public static void dictionarySearch() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn tìm kiếm:");
        String target = sc.nextLine();
        int num = 0;
        for (Word word : Dictionary.Words) {
            if (word.getWord_target().startsWith(target)) {
                System.out.println(word.getWord_target());
                num++;
            }
        }
        if (num == 0) {
            System.out.println("Không có từ bắt đầu bằng \""+ target + "\"");
        }
    }

    public static void main(String[] args) throws IOException {
        dictionaryAdvanced();
    }
}

