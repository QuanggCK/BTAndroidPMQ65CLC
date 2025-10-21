package clc65.quanggck.lamthem_qldapm;

import android.widget.ImageView;
import android.widget.TextView;

public class QLDA {
    ImageView imageViewFormula;
    TextView tv_description;

    public QLDA(ImageView imageViewFormula, TextView tv_description) {
        this.imageViewFormula = imageViewFormula;
        this.tv_description = tv_description;
    }

    public ImageView getImageViewFormula() {
        return imageViewFormula;
    }

    public void setImageViewFormula(ImageView imageViewFormula) {
        this.imageViewFormula = imageViewFormula;
    }

    public TextView getTv_description() {
        return tv_description;
    }

    public void setTv_description(TextView tv_description) {
        this.tv_description = tv_description;
    }
}
