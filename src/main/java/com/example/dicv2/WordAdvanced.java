package com.example.dicv2;

import java.util.ArrayList;

public class WordAdvanced {
    private String word;
    protected ArrayList<Explain> explains = new ArrayList<Explain>();

    public WordAdvanced() {
    }

    public WordAdvanced(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void addExplain(String kind, String pronunce, String explain) {
        Explain temp = new Explain(kind, pronunce, explain);
        explains.add(temp);
    }
}
