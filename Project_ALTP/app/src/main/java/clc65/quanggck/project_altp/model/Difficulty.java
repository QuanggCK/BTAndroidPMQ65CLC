package clc65.quanggck.project_altp.model;

public enum Difficulty {

    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    LEVEL_6(6),
    LEVEL_7(7),
    LEVEL_8(8),
    LEVEL_9(9),
    LEVEL_10(10),
    LEVEL_11(11),
    LEVEL_12(12),
    LEVEL_13(13),
    LEVEL_14(14),
    LEVEL_15(15);

    public final int value;

    Difficulty(int value) {
        this.value = value;
    }

    // Chuyển từ số sang Difficulty
    public static Difficulty fromLevel(int level) {
        for (Difficulty d : values()) {
            if (d.value == level) return d;
        }
        return LEVEL_1;
    }
}
