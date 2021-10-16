package com.example.dicv2;

public class Explain {
    private String kind;
    private String explain;
    private String example;

    public Explain() {}

    public Explain(String kind, String explain, String example) {
        this.kind = kind;
        this.explain = explain;
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setWord(String kind) {
        this.kind = kind;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getKind() {
        return kind;
    }

    public String getExplain() {
        return explain;
    }
}
