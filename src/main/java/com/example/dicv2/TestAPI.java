package com.example.dicv2;

import java.io.IOException;

public class TestAPI {
    public static void main(String[] args) throws IOException {
        String string = "Hello World";
        System.out.println(GoogleTranslate.translate("vi",string));
    }
}
