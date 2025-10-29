package thigki1.phamminhquang.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CongTrinhAdapter extends RecyclerView.Adapter<CongTrinhAdapter.ItemHoatDongHolder> {
    Context context;
    ArrayList<CongTrinhModel> listHoatDong;

    public CongTrinhAdapter(Context context, ArrayList<CongTrinhModel> listHoatDong) {
        this.context = context;
        this.listHoatDong = listHoatDong;
    }

    @NonNull
    @Override
    public ItemHoatDongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem= cai_bom.inflate(R.layout.item_ctrinh, parent, false);
        ItemHoatDongHolder viewholderCreated = new ItemHoatDongHolder(vItem);
        return viewholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoatDongHolder holder, int position) {
        CongTrinhModel hoatdongHienThi = listHoatDong.get(position);

        String mainContent = hoatdongHienThi.getMainContent();
        String subContent = hoatdongHienThi.getSubContent();
        String image = hoatdongHienThi.getImages();

        holder.tv_MainContent.setText(mainContent);
        holder.tv_SubContent.setText(subContent);

        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(image, "mipmap",packageName);
        holder.Image.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return listHoatDong.size();
    }


    class ItemHoatDongHolder extends RecyclerView.ViewHolder{
        TextView tv_MainContent, tv_SubContent;
        ImageView Image;

        public ItemHoatDongHolder(@NonNull View itemView) {
            super(itemView);
            tv_MainContent = itemView.findViewById(R.id.Maincontent);
            tv_SubContent = itemView.findViewById(R.id.Subcontent);
            Image = itemView.findViewById(R.id.image_logo);
        }
    }
}
