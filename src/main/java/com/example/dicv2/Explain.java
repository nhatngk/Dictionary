package com.example.dicv2;

public class Explain {
    private String kind;
    private String explain;
    private String pronunce;

    public Explain() {}

    public Explain(String kind, String pronunce, String explain) {
        this.kind = kind;
        this.pronunce = pronunce;
        this.explain = explain;
    }

    public void setWord(String kind) {
        this.kind = kind;
    }

    public void setPronunce(String pronunciation) {
        this.pronunce = pronunciation;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getKind() {
        return kind;
    }

    public String getPronunce() {
        return pronunce;
    }

    public String getExplain() {
        return explain;
    }
}
