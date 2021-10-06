package Translator;

import java.io.IOException;

public class testGoogleTranslate {
    public static void main(String[] args) throws IOException {
        String string = "Hello World";
        System.out.println(GoogleTranslate.translate("vi",string));
    }
}

