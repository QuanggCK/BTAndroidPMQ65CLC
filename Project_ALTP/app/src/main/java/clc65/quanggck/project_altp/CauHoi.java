package clc65.quanggck.project_altp;

import java.util.List;

public class CauHoi {

    private int id;
    private String noidung;
    private List<DapAn> dapAnList;
    private DoKho doKho;
    private int soCau;

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
