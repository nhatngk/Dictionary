package DictAdvanced;

public class WordAdvanced {
    private String word;
    private String pronunce;
    private String explain;

    public WordAdvanced() {

    }

    public WordAdvanced(String word, String pronunce, String explain) {
        this.word = word;
        this.pronunce = pronunce;
        this.explain = explain;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setPronunce(String pronunciation) {
        this.pronunce = pronunciation;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getWord() {
        return word;
    }

    public String getPronunce() {
        return pronunce;
    }

    public String getExplain() {
        return explain;
    }

}
