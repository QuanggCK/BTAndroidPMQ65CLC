package clc65.quanggck.project_altp.model;

public class Question {

    public int id;
    public String content;

    public String a;
    public String b;
    public String c;
    public String d;

    public String correct;        // A, B, C, D
    public Difficulty difficulty; // LEVEL_1 → LEVEL_15

    public Question() {
    }

    public Question(int id,
                    String content,
                    String a, String b, String c, String d,
                    String correct,
                    Difficulty difficulty) {

        this.id = id;
        this.content = content;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
        this.difficulty = difficulty;
    }
}
