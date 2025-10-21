package clc65.quanggck.recylerview1_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape> lstData;


    public LandScapeAdapter(Context context, ArrayList<LandScape> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem = cai_bom.inflate(R.layout.item_land, parent, false);
        ItemLandHolder viewHolderCreated = new ItemLandHolder(vItem);
        ///Mục đích hàm này là để tạo 1 cái ViewHolder
        return viewHolderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        // Lấy phần tử ở vị trí position của nguồn dữ liệu
        LandScape landScapeHienThi = lstData.get(position);
        // Đặt  vào các thuộc tính hiển thị của view con
        // Đặt tên
        String caption = landScapeHienThi.getLandCaption();
        String tenFileAnh = landScapeHienThi.getLandImageFileName();
        holder.tv_caption.setText(caption);
        //Đặt ảnh
        String packpageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getContext().getResources().getIdentifier(tenFileAnh, "mipmap", packpageName);
        holder.imageViewLandScape.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    /// (1) và (2) bên class LandScape
    /// (3): Kế thừa RecyclerView bên lớp Adapter
    //Cái RecyclerView yêu cầu phải có 1 ViewHolder nên phải tạo để nhét vào chỗ <> trên class extends
    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_caption;
        ImageView imageViewLandScape;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            imageViewLandScape = itemView.findViewById(R.id.imageViewLand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Lấy vị trí item được click thông qua phương thức getAdapterPosition()
            int viTriDuocClick = getAdapterPosition();
            LandScape phanTuDuocChon = lstData.get(viTriDuocClick);
            // Lấy dữ liệu tương ứng từ danh sách (theo vị trí)
            String ten = phanTuDuocChon.getLandCaption();
            String tenFile = phanTuDuocChon.getLandImageFileName();
            // Hiển thị thông báo hoặc thực hiện các xử lý khác
            //Toast.makeText(context, ten, Toast.LENGTH_SHORT).show();
            String chuoiThongBao = "Bạn vừa click vào " + ten ;
            Toast.makeText(context, chuoiThongBao, Toast.LENGTH_LONG).show();


            // Hiện thông báo, hoặc thực hiện các xử lý khác
        }
    }


}
