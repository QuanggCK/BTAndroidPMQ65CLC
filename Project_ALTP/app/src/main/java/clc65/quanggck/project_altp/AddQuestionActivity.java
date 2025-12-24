package clc65.quanggck.project_altp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

// Nhớ import DatabaseHelper
import clc65.quanggck.project_altp.database.DatabaseHelper;

public class AddQuestionActivity extends AppCompatActivity {

    private Button btn_return, btn_confirm;
    private EditText edt_namequestion;
    private EditText tv_a, tv_b, tv_c, tv_d;
    private Spinner sp_diff;

    // 1. KHAI BÁO BIẾN DATABASE
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // 2. KHỞI TẠO DATABASE
        dbHelper = new DatabaseHelper(this);

        TimCT();
        setupSpinner();
        addEvents();
    }

    // ... (Giữ nguyên TimCT, setupSpinner, addEvents, randomRateQuestion) ...

    private void TimCT() {
        btn_return = findViewById(R.id.btn_return);
        btn_confirm = findViewById(R.id.btn_confirm);
        edt_namequestion = findViewById(R.id.edt_namequestion);
        tv_a = findViewById(R.id.tv_a);
        tv_b = findViewById(R.id.tv_b);
        tv_c = findViewById(R.id.tv_c);
        tv_d = findViewById(R.id.tv_d);
        sp_diff = findViewById(R.id.sp_diff);
    }

    private void setupSpinner() {
        String[] levels = new String[15];
        for (int i = 0; i < 15; i++) levels[i] = String.valueOf(i + 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_spinner, levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_diff.setAdapter(adapter);
    }

    private void addEvents() {
        btn_return.setOnClickListener(v -> QuayVe());
        btn_confirm.setOnClickListener(v -> XacNhanThem());
    }

    private void QuayVe() { finish(); }

    private int[] randomRateQuestion() {
        Random rand = new Random();
        int a, b, c, d;
        while (true) {
            a = rand.nextInt(21) + 15;
            b = rand.nextInt(21) + 15;
            c = rand.nextInt(21) + 15;
            d = 100 - (a + b + c);
            if (d >= 15 && d < 40) break;
        }
        return new int[]{a, b, c, d};
    }

    // 3. CẬP NHẬT HÀM XÁC NHẬN THÊM
    private void XacNhanThem() {
        String content = edt_namequestion.getText().toString().trim();
        String strA = tv_a.getText().toString().trim();
        String strB = tv_b.getText().toString().trim();
        String strC = tv_c.getText().toString().trim();
        String strD = tv_d.getText().toString().trim();

        String levelStr = sp_diff.getSelectedItem().toString();
        int level = Integer.parseInt(levelStr);

        if (content.isEmpty() || strA.isEmpty() || strB.isEmpty() || strC.isEmpty() || strD.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sinh tỷ lệ
        int[] rates = randomRateQuestion();

        // --- LƯU VÀO DATABASE ---
        try {
            // Vì form nhập liệu quy định:
            // tv_a là ĐÚNG -> lưu vào cột 'a', và cột 'correct' là "A"
            // tv_b là Sai -> lưu vào cột 'b'
            // tv_c là Sai -> lưu vào cột 'c'
            // tv_d là Sai -> lưu vào cột 'd'
            // rates[0] là rate cao nhất -> gán cho A (vì A đúng)

            long result = dbHelper.insertQuestion(
                    content,
                    strA, strB, strC, strD, // 4 đáp án
                    "A",                    // Đáp án đúng luôn là A (theo form nhập)
                    level,                  // Độ khó
                    rates[0], rates[1], rates[2], rates[3] // Tỷ lệ (A, B, C, D)
            );

            if (result != -1) {
                Toast.makeText(this, "Thêm thành công câu hỏi Level " + level, Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(this, "Lỗi: Không thể thêm vào DB", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi Crash: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearInputs() {
        edt_namequestion.setText("");
        tv_a.setText("");
        tv_b.setText("");
        tv_c.setText("");
        tv_d.setText("");
        sp_diff.setSelection(0);
        edt_namequestion.requestFocus();
    }
}