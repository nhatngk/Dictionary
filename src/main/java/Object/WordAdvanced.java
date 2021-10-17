package Object;

import java.util.ArrayList;

public class WordAdvanced {
    private String word;
    private String pronounce;
    public ArrayList<Explain> explains = new ArrayList<Explain>();

    public WordAdvanced() {
    }

    public WordAdvanced(String word, String pronounce) {
        this.word = word;
        this.pronounce = pronounce;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public void addExplain(String kind, String explain, String example) {
        Explain temp = new Explain(kind, explain, example);
        explains.add(temp);
    }

    public void addAllExplain(WordAdvanced x) {
        explains.addAll(x.explains);
    }

}
