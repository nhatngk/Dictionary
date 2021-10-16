package com.example.dicv2;

import java.util.ArrayList;

public class WordAdvanced {
    private String word;
    private String pronunce;
    public ArrayList<Explain> explains = new ArrayList<Explain>();

    public WordAdvanced() {
    }

    public WordAdvanced(String word, String pronunce) {
        this.word = word;
        this.pronunce = pronunce;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunce() {
        return pronunce;
    }

    public void setPronunce(String pronunce) {
        this.pronunce = pronunce;
    }

    public void addExplain(String kind, String explain, String example) {
        Explain temp = new Explain(kind, explain, example);
        explains.add(temp);
    }

//    public String toString() {
//        StringBuilder temp = new StringBuilder();
//    }
}
