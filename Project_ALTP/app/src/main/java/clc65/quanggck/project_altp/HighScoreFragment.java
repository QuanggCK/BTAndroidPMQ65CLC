package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import clc65.quanggck.project_altp.dao.HighScoreDAO;
import clc65.quanggck.project_altp.model.HighScore;

public class HighScoreFragment extends Fragment {

    // Khai báo biến
    ListView lvHighScore;
    Button btnReturn;
    HighScoreDAO dao;

    private void TimCT(View view) {
        lvHighScore = view.findViewById(R.id.lv_highscore);
        btnReturn = view.findViewById(R.id.btn_return);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Gắn layout xml vào Fragment
        View view = inflater.inflate(R.layout.fragment_high_score, container, false);

        // 2. Ánh xạ View (Truyền biến view vừa tạo vào hàm)
        TimCT(view);

        // 3. Khởi tạo DAO
        dao = new HighScoreDAO(getContext());

        // 4. Load dữ liệu lên ListView
        loadData();

        // 5. Sự kiện nút Quay lại
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng Fragment hiện tại để quay về màn hình trước (Menu)
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // ===== Hàm lấy dữ liệu và đổ vào List =====
    private void loadData() {
        if (dao != null) {
            // Lấy danh sách Top 5 từ Database
            List<HighScore> list = dao.getTop5();

            // Tạo Adapter và gán vào ListView
            HighScoreAdapter adapter = new HighScoreAdapter(list);
            lvHighScore.setAdapter(adapter);
        }
    }

    // ========================================================
    // CLASS ADAPTER (Viết lồng bên trong để xử lý giao diện dòng)
    // ========================================================
    class HighScoreAdapter extends BaseAdapter {
        List<HighScore> dataList;

        public HighScoreAdapter(List<HighScore> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1. Tạo View cho dòng nếu chưa có
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_high_score, parent, false);
            }

            // 2. Lấy đối tượng dữ liệu tại vị trí hiện tại
            HighScore item = dataList.get(position);

            // 3. Ánh xạ các View trong dòng (item_high_score.xml)
            TextView tvName = convertView.findViewById(R.id.tv_item_name);
            TextView tvScore = convertView.findViewById(R.id.tv_item_score);

            // 4. Gán dữ liệu (Dùng tên biến playerName như bạn yêu cầu)
            tvName.setText(item.playerName);

            // Định dạng tiền tệ cho đẹp (Ví dụ: 150000 -> 150,000 VND)
            String formattedMoney = String.format("%,d VND", item.money);
            tvScore.setText(formattedMoney);

            return convertView;
        }
    }
}