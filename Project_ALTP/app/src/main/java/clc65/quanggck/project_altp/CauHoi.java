package clc65.quanggck.project_altp;

import java.util.List;

public class CauHoi {

    private int id;                  // ID câu hỏi
    private String noidung;           // Nội dung câu hỏi
    private List<DapAn> dapAnList;    // Danh sách đáp án
    private DoKho doKho;              // Độ khó (EZ, MEDIUM, HARD)
    private int soCau;                // Số thứ tự câu (1 → 15)

    public CauHoi() {
    }

    public CauHoi(int id, String noidung, List<DapAn> dapAnList, DoKho doKho, int soCau) {
        this.id = id;
        this.noidung = noidung;
        this.dapAnList = dapAnList;
        this.doKho = doKho;
        this.soCau = soCau;
    }

    public int getId() {
        return id;
    }

    public String getNoiDung() {
        return noidung;
    }

    public List<DapAn> getDapAnList() {
        return dapAnList;
    }

    public DoKho getDoKho() {
        return doKho;
    }

    public int getSoCau() {
        return soCau;
    }

    // Lấy đáp án đúng
    public DapAn getDapAnDung() {
        for (DapAn da : dapAnList) {
            if (da.isCorrect()) {
                return da;
            }
        }
        return null;
    }
}
