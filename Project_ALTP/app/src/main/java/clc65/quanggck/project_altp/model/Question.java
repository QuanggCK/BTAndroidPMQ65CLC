package clc65.quanggck.project_altp.model;

public class Question {

    public int id;
    public String content;

    public String a,b,c,d;

    public float rate_a, rate_b, rate_c, rate_d;

    public String correct;        // A, B, C, D
    public Difficulty difficulty; // LEVEL_1 → LEVEL_15


    public Question() {
    }

    public Question(int id, String content, String a, String b, String c, String d, float rate_a,
                    float rate_b, float rate_c, float rate_d, String correct, Difficulty difficulty) {
        this.id = id;
        this.content = content;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.rate_a = rate_a;
        this.rate_b = rate_b;
        this.rate_c = rate_c;
        this.rate_d = rate_d;
        this.correct = correct;
        this.difficulty = difficulty;
    }
}
