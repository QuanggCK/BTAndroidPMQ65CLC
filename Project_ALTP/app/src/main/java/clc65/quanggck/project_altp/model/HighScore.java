package clc65.quanggck.project_altp.model;

public class HighScore {

    public int id;
    public String playerName;

    public int score;

    // số tiền tương ứng
    public int money;

    public HighScore() {
    }

    public HighScore(int id, String playerName, int score, int money) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
        this.money = money;
    }
}
