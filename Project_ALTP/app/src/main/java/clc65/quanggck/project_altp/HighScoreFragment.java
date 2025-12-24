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

    // 1. Hàm TimCT cho Fragment (Ánh xạ ListView và Nút quay lại)
    private void TimCT(View view) {
        lvHighScore = view.findViewById(R.id.lv_highscore);
        btnReturn = view.findViewById(R.id.btn_return);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_high_score, container, false);

        TimCT(view); // Gọi hàm ánh xạ

        dao = new HighScoreDAO(getContext());
        loadData();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình trước
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                } else {
                    // Nếu Fragment được nhúng trực tiếp thì có thể cần finish Activity chứa nó
                    // getActivity().finish();
                }
            }
        });

        return view;
    }

    private void loadData() {
        if (dao != null) {
            List<HighScore> list = dao.getTop5();
            HighScoreAdapter adapter = new HighScoreAdapter(list);
            lvHighScore.setAdapter(adapter);
        }
    }

    // ===== ADAPTER =====
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
            ViewHolder holder;

            // Nếu view chưa được tạo thì tạo mới và ánh xạ (TimCT)
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_high_score, parent, false);

                holder = new ViewHolder();
                holder.TimCT(convertView); // Gọi hàm TimCT của item

                convertView.setTag(holder);
            } else {
                // Nếu đã có thì lấy lại holder cũ (không cần findView lại -> mượt hơn)
                holder = (ViewHolder) convertView.getTag();
            }

            // Gán dữ liệu
            HighScore item = dataList.get(position);
            holder.tvName.setText(item.playerName);


            String formattedMoney = String.format("%,d VND", item.money);
            holder.tvScore.setText(formattedMoney);

            return convertView;
        }

        private class ViewHolder {
            TextView tvName, tvScore;

            public void TimCT(View view) {
                tvName = view.findViewById(R.id.tv_item_name);
                tvScore = view.findViewById(R.id.tv_item_score);
            }
        }
    }
}