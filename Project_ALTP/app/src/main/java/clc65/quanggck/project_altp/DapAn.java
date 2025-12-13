package clc65.quanggck.project_altp;

public class DapAn {
        private String id;        // A, B, C, D
        private String noidung;   // Nội dung đáp án
        private boolean correct;  // Đúng hay sai
        private double ratio;     // Tỉ lệ khán giả / gọi điện

        public DapAn() {
        }

        public DapAn(String id, String noidung, boolean correct, double ratio) {
            this.id = id;
            this.noidung = noidung;
            this.correct = correct;
            this.ratio = ratio;
        }

        public String getId() {
            return id;
        }

        public String getNoiDung() {
            return noidung;
        }

        public boolean isCorrect() {
            return correct;
        }

        public double getRatio() {
            return ratio;
        }
    }
