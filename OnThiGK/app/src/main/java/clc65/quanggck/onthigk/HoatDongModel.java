package clc65.quanggck.onthigk;

public class HoatDongModel {
    String mainContent, subContent, images_logo;

    public HoatDongModel(String mainContent, String images_logo, String subContent) {
        this.mainContent = mainContent;
        this.images_logo = images_logo;
        this.subContent = subContent;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public String getImages() {
        return images_logo;
    }

    public void setImages(String images_logo) {
        this.images_logo = images_logo;
    }
}
