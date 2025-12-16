package clc65.quanggck.project_altp.model;

public class Question {

    public int id;
    public String content;
    public String a;
    public String b;
    public String c;
    public String d;
    public String correct;
    public int difficultyId;

    public Question() {
    }

    // Constructor đầy đủ
    public Question(int id, String content,
                    String a, String b, String c, String d,
                    String correct, int difficultyId) {

        this.id = id;
        this.content = content;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
        this.difficultyId = difficultyId;
    }
}
