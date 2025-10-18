package clc65.quanggck.adapter_recyclerview;

public class LandScape {
    /// (1): Xây model class cho mỗi item dữ liệu
    ///  Cái này có 1 ảnh và 1 thông tin tên
    /// (2:) Tạo 1 file xml để thiết kế 1 bố cục cho 1 item RecyclerView
    String landImageFileName;
    String landCaption;

    public LandScape(String landImageFileName, String landCaption) {
        this.landImageFileName = landImageFileName;
        this.landCaption = landCaption;
    }

    public String getLandImageFileName() {
        return landImageFileName;
    }

    public void setLandImageFileName(String landImageFileName) {
        this.landImageFileName = landImageFileName;
    }

    public String getLandCaption() {
        return landCaption;
    }

    public void setLandCaption(String landCaption) {
        this.landCaption = landCaption;
    }
}
