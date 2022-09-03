package SJProject.assignment1;

public class Word {
    public Word() {
    }
    public Word(int level, String word, String def) {
        this.level = level;
        this.word = word;
        this.def = def;
    }
    private int level;

    private String word;

    private String def;

    public int getLevel() {

        return level;
    }

    public String getWord() {

        return word;
    }

    public String getDef() {

        return def;
    }

    public void setLevel(int level) {

        this.level = level;
    }

    public void setWord(String word) {

        this.word = word;
    }

    public void setDef(String def) {

        this.def = def;
    }
}
