package DictCmd;


import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;


public class DictionaryManagement {
    public static void insertFromCommandline() {
        System.out.println("Nhập số từ muốn nhập:");
        Scanner sc = new Scanner(System.in);
        int num = 0;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < num; i++) {
            System.out.print("Nhập từ tiếng Anh:");
            String target = sc.nextLine();
            boolean check = false;
            for (Word value : Dictionary.Words) {
                if (value.getWord_target().equals(target.toLowerCase())) {
                    System.out.println("Đã có từ này trong từ điển. Nhập lại? (Y/N)");
                    String reput = sc.nextLine();
                    if (reput.equals("Y")) {
                        check = true;
                        i--;
                    }
                    break;
                }
            }
            if (! check) {
                System.out.print("Nhập nghĩa tiếng Việt:");
                String explain = sc.nextLine();
                Dictionary.Words.add(new Word(target.toLowerCase(),explain));
                System.out.println("Đã thêm từ " + target.toLowerCase() + " vào từ điển.");
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
    }

    public static int binarySearch(String word) {
        if(Dictionary.Words.size() == 0) {
            return -1;
        }
        int right = Dictionary.Words.size() - 1;
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
            return "Không tìm thấy từ "  + wordLookUp + " trong từ điển";
        } else {
            return Dictionary.Words.get(index).getWord_explain();
        }
    }

    public static void insertAWordCmd() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ tiếng Anh:");
        String target = sc.nextLine();
        boolean check = false;
        do {
            check = false;
            int index = binarySearch(target);
            if (index >= 0 ) {
                check = true;
                System.out.println("Đã có từ này trong từ điển. Mời nhập lại? (Y/N)");
                String reput = sc.nextLine();
                if (reput.equals("N")) {
                    return;
                }
                target = sc.nextLine();
            } else {
                break;
            }
        } while (check);
        System.out.print("Nhập nghĩa tiếng Việt:");
        String explain = sc.nextLine();
        Dictionary.Words.add(new Word(target.toLowerCase(),explain));
        Collections.sort(Dictionary.Words);
        System.out.println("Đã thêm từ " + target.toLowerCase() + " vào từ điển.");
    }

    public static void changeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn sửa:");
        String target = sc.nextLine();
        int index = binarySearch(target);
        if (index < 0) {
            System.out.println("Chưa có từ " + target + "trong từ điển.Bạn có muốn thêm vào từ điển không? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                insertAWordCmd();
            } else if (c == 'N') {
                return;
            }
        } else {
            String oldWord = Dictionary.Words.get(index).getWord_target();
            System.out.println("Bạn có muốn sửa từ tiếng Anh? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                System.out.println("Sửa từ tiếng Anh:");
                String changedWord = sc.nextLine();
                boolean check = false;
                do {
                    check = false;
                    int index1 = binarySearch(changedWord);
                    if (index1 >= 0) {
                        check = true;
                        System.out.println("Đã có từ này trong tù điển. Mời nhập lại:");
                        changedWord = sc.nextLine();
                    } else {
                        break;
                    }
                } while (check);
                System.out.print("Nhập nghĩa tiếng việt:");
                String explain = sc.nextLine();
                Dictionary.Words.get(index).setWord_target(changedWord.toLowerCase());
                Dictionary.Words.get(index).setWord_explain(explain);
                Collections.sort(Dictionary.Words);
                System.out.println("Đã thay từ " + oldWord + " thành từ " + changedWord.toLowerCase());
            } else if (c == 'N') {
                System.out.print("Nhập nghĩa tiếng việt:");
                String explain = sc.nextLine();
                Dictionary.Words.get(index).setWord_explain(explain);
                Collections.sort(Dictionary.Words);
                System.out.println("Đã thay đổi nghĩa của từ " + oldWord);
            }
        }
    }

    public static void removeWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ muốn xóa:");
        String target = sc.nextLine();
        int index = binarySearch(target);
        if (index < 0) {
            System.out.println("Chưa có từ " + target + "trong từ điển.");
        } else {
            System.out.println("Bạn có chắc chắn muốn xóa từ "+ target + "? Y/N?");
            char c = sc.nextLine().charAt(0);
            if (c == 'Y') {
                Dictionary.Words.remove(index);
                Collections.sort(Dictionary.Words);
                System.out.println("Đã xóa từ " + target + " khỏi từ điển.");
            } else if (c == 'N') {
                return;
            }
        }
    }

    public static void dictionaryExportToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("dictionaries.txt");
        for (Word word : Dictionary.Words) {
            fileWriter.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
        }
        fileWriter.close();
        System.out.println("Đã ghi dữ liệu vào file.");
    }
}

