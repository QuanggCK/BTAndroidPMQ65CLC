package clc65.quanggck.project_altp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "altp.db";

    private static final int DB_VERSION = 5;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 1. Tạo bảng Difficulty
        db.execSQL(
                "CREATE TABLE Difficulty (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL)"
        );

        // 2. Tạo bảng Question
        db.execSQL(
                "CREATE TABLE Question (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "content TEXT NOT NULL," +
                        "a TEXT NOT NULL," +
                        "b TEXT NOT NULL," +
                        "c TEXT NOT NULL," +
                        "d TEXT NOT NULL," +
                        "correct TEXT NOT NULL," +
                        "difficulty_id INTEGER NOT NULL," +
                        "rate_a INTEGER DEFAULT 0," +
                        "rate_b INTEGER DEFAULT 0," +
                        "rate_c INTEGER DEFAULT 0," +
                        "rate_d INTEGER DEFAULT 0)"
        );

        // 3. Tạo bảng HighScore
        db.execSQL(
                "CREATE TABLE HighScore (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "player_name TEXT NOT NULL," +
                        "score INTEGER NOT NULL," +
                        "money INTEGER NOT NULL," +
                        "createdAt INTEGER NOT NULL)"
        );

        // 4. Thêm dữ liệu vào bảng Difficulty
        for (int i = 1; i <= 15; i++) {
            db.execSQL("INSERT INTO Difficulty(id, name) VALUES (" + i + ", 'LEVEL_" + i + "')");
        }

        // --- NẠP DỮ LIỆU CÂU HỎI (Chia làm 3 đợt theo kiểu nối chuỗi) ---

        // ===================================================
        // ĐỢT 1: CÂU DỄ (LEVEL 1 - 5)
        // ===================================================
        String sql1 = "INSERT INTO Question(content, a, b, c, d, correct, difficulty_id, rate_a, rate_b, rate_c, rate_d) VALUES ";

        // Level 1
        sql1 += "('Một cộng một bằng mấy?', '1', '2', '3', '4', 'B', 1, 0, 100, 0, 0),";
        sql1 += "('Con trâu có mấy chân?', '2 chân', '4 chân', '6 chân', '8 chân', 'B', 1, 0, 100, 0, 0),";
        sql1 += "('Mặt trời mọc ở hướng nào?', 'Đông', 'Tây', 'Nam', 'Bắc', 'A', 1, 100, 0, 0, 0),";
        sql1 += "('Đâu là một loại phương tiện giao thông đường thủy?', 'Xe đạp', 'Máy bay', 'Tàu hỏa', 'Tàu thủy', 'D', 1, 5, 5, 5, 85),";
        sql1 += "('Trong truyện Tấm Cám, quả gì bị rụng xuống giếng?', 'Quả táo', 'Quả thị', 'Quả cau', 'Quả dừa', 'B', 1, 10, 80, 5, 5),";
        sql1 += "('Màu nào sau đây thuộc gam màu nóng?', 'Xanh dương', 'Đỏ', 'Trắng', 'Đen', 'B', 1, 5, 90, 5, 0),";
        sql1 += "('Con gì vo ve thích hút máu người?', 'Con ruồi', 'Con muỗi', 'Con gián', 'Con kiến', 'B', 1, 0, 100, 0, 0),";

        // Level 2
        sql1 += "('Thủ đô của Việt Nam là gì?', 'Sài Gòn', 'Đà Nẵng', 'Hà Nội', 'Huế', 'C', 2, 5, 5, 90, 0),";
        sql1 += "('Bánh chưng thường có hình gì?', 'Hình tròn', 'Hình vuông', 'Hình tam giác', 'Hình chữ nhật', 'B', 2, 0, 95, 5, 0),";
        sql1 += "('Loài vật nào sau đây ăn cỏ?', 'Hổ', 'Báo', 'Sư tử', 'Bò', 'D', 2, 0, 0, 5, 95),";
        sql1 += "('Tháng hai có tối đa bao nhiêu ngày?', '28 hoặc 29', '30', '31', '27', 'A', 2, 85, 5, 5, 5),";
        sql1 += "('Đâu là tên một loại nhạc cụ?', 'Cái bàn', 'Cái ghế', 'Đàn Guitar', 'Cái chảo', 'C', 2, 0, 0, 100, 0),";
        sql1 += "('Nước nào có diện tích lớn nhất thế giới?', 'Mỹ', 'Trung Quốc', 'Nga', 'Canada', 'C', 2, 10, 10, 80, 0),";
        sql1 += "('Ai là người sáng tác bài Quốc ca Việt Nam?', 'Văn Cao', 'Trịnh Công Sơn', 'Phạm Tuyên', 'Phú Quang', 'A', 2, 90, 5, 3, 2),";

        // Level 3
        sql1 += "('Hổ và Sư tử, con nào sống theo bầy đàn?', 'Hổ', 'Sư tử', 'Cả hai', 'Không con nào', 'B', 3, 20, 70, 5, 5),";
        sql1 += "('Đỉnh núi cao nhất Việt Nam là?', 'Fansipan', 'Langbiang', 'Bạch Mã', 'Hồng Lĩnh', 'A', 3, 85, 5, 5, 5),";
        sql1 += "('Đâu không phải là một mùa trong năm?', 'Xuân', 'Hạ', 'Thu', 'Đêm', 'D', 3, 0, 5, 5, 90),";
        sql1 += "('Dế Mèn là nhân vật của nhà văn nào?', 'Ngô Tất Tố', 'Tô Hoài', 'Nam Cao', 'Vũ Trọng Phụng', 'B', 3, 10, 85, 5, 0),";
        sql1 += "('Vịnh Hạ Long thuộc tỉnh nào?', 'Quảng Ninh', 'Hải Phòng', 'Thái Bình', 'Nam Định', 'A', 3, 90, 5, 3, 2),";
        sql1 += "('Chất lỏng nào cần thiết nhất cho sự sống?', 'Dầu ăn', 'Xăng', 'Nước', 'Rượu', 'C', 3, 0, 0, 100, 0),";
        sql1 += "('Đèn giao thông có mấy màu?', '2', '3', '4', '5', 'B', 3, 5, 90, 5, 0),";

        // Level 4
        sql1 += "('Hành tinh nào gần Mặt Trời nhất?', 'Sao Kim', 'Sao Thủy', 'Trái Đất', 'Sao Hỏa', 'B', 4, 15, 75, 5, 5),";
        sql1 += "('Con sông nào chảy qua Hà Nội?', 'Sông Hồng', 'Sông Hương', 'Sông Hàn', 'Sông Cửu Long', 'A', 4, 80, 10, 5, 5),";
        sql1 += "('Số Pi xấp xỉ bằng bao nhiêu?', '3.12', '3.14', '3.16', '3.18', 'B', 4, 10, 80, 5, 5),";
        sql1 += "('Bác Hồ đọc bản Tuyên ngôn độc lập năm nào?', '1930', '1945', '1954', '1975', 'B', 4, 10, 85, 2, 3),";
        sql1 += "('Lá cờ Olympic có mấy vòng tròn?', '4', '5', '6', '7', 'B', 4, 20, 70, 5, 5),";
        sql1 += "('Đơn vị đo cường độ dòng điện là gì?', 'Vôn', 'Ampe', 'Ohm', 'Watt', 'B', 4, 15, 75, 5, 5),";
        sql1 += "('Loài hoa nào là biểu tượng của Nhật Bản?', 'Hoa Sen', 'Hoa Hồng', 'Hoa Anh Đào', 'Hoa Cúc', 'C', 4, 5, 10, 80, 5),";

        // Level 5
        sql1 += "('World Cup là giải đấu của môn thể thao nào?', 'Bóng rổ', 'Bóng đá', 'Quần vợt', 'Cầu lông', 'B', 5, 5, 90, 3, 2),";
        sql1 += "('Tháp Eiffel nằm ở thành phố nào?', 'London', 'Berlin', 'Paris', 'Rome', 'C', 5, 10, 10, 75, 5),";
        sql1 += "('Ai là tác giả Truyện Kiều?', 'Nguyễn Trãi', 'Nguyễn Du', 'Nguyễn Khuyến', 'Hồ Xuân Hương', 'B', 5, 10, 85, 3, 2),";
        sql1 += "('Châu lục nào lạnh nhất thế giới?', 'Châu Á', 'Châu Âu', 'Châu Nam Cực', 'Châu Bắc Cực', 'C', 5, 20, 20, 55, 5),";
        sql1 += "('Con vật nào là biểu tượng của y học?', 'Rắn', 'Ngựa', 'Đại bàng', 'Sư tử', 'A', 5, 70, 10, 10, 10),";
        sql1 += "('Nước nào có dân số đông nhất thế giới (2023)?', 'Trung Quốc', 'Mỹ', 'Ấn Độ', 'Indonesia', 'C', 5, 25, 20, 45, 10),";
        sql1 += "('Kim tự tháp Giza nằm ở nước nào?', 'Ai Cập', 'Hy Lạp', 'Thổ Nhĩ Kỳ', 'Iran', 'A', 5, 80, 10, 5, 5)";

        db.execSQL(sql1);


        // ===================================================
        // ĐỢT 2: CÂU TRUNG BÌNH (LEVEL 6 - 10)
        // ===================================================
        String sql2 = "INSERT INTO Question(content, a, b, c, d, correct, difficulty_id, rate_a, rate_b, rate_c, rate_d) VALUES ";

        // Level 6
        sql2 += "('Đất nước nào là quê hương của ông già Noel?', 'Na Uy', 'Thụy Điển', 'Phần Lan', 'Đan Mạch', 'C', 6, 20, 20, 50, 10),";
        sql2 += "('Hạt nhân nguyên tử được cấu tạo bởi?', 'Proton và Electron', 'Proton và Neutron', 'Neutron và Electron', 'Chỉ Proton', 'B', 6, 25, 45, 20, 10),";
        sql2 += "('Sông Mê Kông chảy qua bao nhiêu quốc gia?', '4', '5', '6', '7', 'C', 6, 20, 20, 40, 20),";
        sql2 += "('Tác phẩm \"Tắt đèn\" là của ai?', 'Nam Cao', 'Ngô Tất Tố', 'Nguyên Hồng', 'Vũ Trọng Phụng', 'B', 6, 15, 60, 15, 10),";
        sql2 += "('SEA Games tổ chức bao nhiêu năm một lần?', '1 năm', '2 năm', '3 năm', '4 năm', 'B', 6, 10, 70, 10, 10),";
        sql2 += "('Loại vitamin nào có nhiều trong quả cam?', 'Vitamin A', 'Vitamin B', 'Vitamin C', 'Vitamin D', 'C', 6, 5, 10, 80, 5),";
        sql2 += "('Đơn vị đo độ lớn của âm thanh là gì?', 'Hz', 'Decibel', 'Watt', 'Volt', 'B', 6, 20, 50, 15, 15),";

        // Level 7
        sql2 += "('Giải Nobel không có lĩnh vực nào?', 'Toán học', 'Vật lý', 'Hóa học', 'Y học', 'A', 7, 50, 20, 20, 10),";
        sql2 += "('Thành phố nào được mệnh danh là \"Thành phố của những cây cầu\"?', 'Hà Nội', 'Đà Nẵng', 'Huế', 'Cần Thơ', 'B', 7, 15, 65, 10, 10),";
        sql2 += "('Trong bảng tuần hoàn, O là ký hiệu của?', 'Ozon', 'Oxy', 'Osmium', 'Olivin', 'B', 7, 10, 80, 5, 5),";
        sql2 += "('Ai là vị vua cuối cùng của triều Nguyễn?', 'Bảo Đại', 'Khải Định', 'Duy Tân', 'Thành Thái', 'A', 7, 60, 20, 10, 10),";
        sql2 += "('Đâu là ngọn núi cao nhất Nhật Bản?', 'Núi Phú Sĩ', 'Núi Kita', 'Núi Hotaka', 'Núi Yari', 'A', 7, 75, 10, 10, 5),";
        sql2 += "('Quốc gia nào có đường bờ biển dài nhất thế giới?', 'Nga', 'Canada', 'Úc', 'Mỹ', 'B', 7, 20, 50, 15, 15),";
        sql2 += "('Đàn bầu của Việt Nam có mấy dây?', '1 dây', '2 dây', '3 dây', '4 dây', 'A', 7, 60, 20, 10, 10),";

        // Level 8
        sql2 += "('Hành tinh nào được gọi là \"Hành tinh đỏ\"?', 'Sao Hỏa', 'Sao Mộc', 'Sao Thổ', 'Sao Kim', 'A', 8, 55, 15, 15, 15),";
        sql2 += "('Vị vua nào đã dời đô từ Hoa Lư về Thăng Long?', 'Lê Đại Hành', 'Đinh Tiên Hoàng', 'Lý Thái Tổ', 'Lý Thánh Tông', 'C', 8, 15, 15, 60, 10),";
        sql2 += "('Động vật nào lớn nhất hành tinh hiện nay?', 'Voi Châu Phi', 'Cá Voi Xanh', 'Khủng long', 'Cá Mập Trắng', 'B', 8, 10, 70, 10, 10),";
        sql2 += "('Quốc gia nào đăng cai World Cup 2022?', 'Nga', 'Pháp', 'Qatar', 'Brazil', 'C', 8, 10, 20, 60, 10),";
        sql2 += "('Thành phố nào đông dân nhất thế giới (theo vùng đô thị)?', 'New York', 'Thượng Hải', 'Tokyo', 'Mumbai', 'C', 8, 20, 20, 40, 20),";
        sql2 += "('Nguyên tố hóa học nào phổ biến nhất trong vỏ Trái Đất?', 'Sắt', 'Oxy', 'Silic', 'Nhôm', 'B', 8, 25, 40, 20, 15),";
        sql2 += "('Ai là người đầu tiên bay vào vũ trụ?', 'Neil Armstrong', 'Yuri Gagarin', 'Buzz Aldrin', 'Phạm Tuân', 'B', 8, 30, 50, 10, 10),";

        // Level 9
        sql2 += "('Ngày Quốc khánh Pháp là ngày nào?', '14/7', '4/7', '2/9', '1/5', 'A', 9, 45, 20, 20, 15),";
        sql2 += "('Bộ phận nào của cơ thể người không có mạch máu?', 'Giác mạc', 'Móng tay', 'Tóc', 'Cả 3', 'D', 9, 20, 20, 20, 40),";
        sql2 += "('Tác giả của \"Mona Lisa\" là ai?', 'Picasso', 'Van Gogh', 'Da Vinci', 'Michelangelo', 'C', 9, 15, 15, 60, 10),";
        sql2 += "('Hồ nào sâu nhất thế giới?', 'Hồ Victoria', 'Hồ Baikal', 'Hồ Superior', 'Hồ Titicaca', 'B', 9, 20, 50, 15, 15),";
        sql2 += "('Đâu là đơn vị tiền tệ của Thái Lan?', 'Ringgit', 'Peso', 'Baht', 'Rupiah', 'C', 9, 15, 15, 60, 10),";
        sql2 += "('Loài chim nào có thể bay lùi?', 'Đại bàng', 'Chim ruồi', 'Bồ câu', 'Hải âu', 'B', 9, 20, 55, 10, 15),";
        sql2 += "('Đỉnh núi Everest nằm ở biên giới giữa Nepal và nước nào?', 'Ấn Độ', 'Bhutan', 'Trung Quốc', 'Pakistan', 'C', 9, 15, 15, 55, 15),";

        // Level 10
        sql2 += "('Ai là tổng thống đầu tiên của Hoa Kỳ?', 'Lincoln', 'Washington', 'Jefferson', 'Kennedy', 'B', 10, 20, 50, 15, 15),";
        sql2 += "('Chất nào làm cho lá cây có màu xanh?', 'Diệp lục', 'Xantophyl', 'Caroten', 'Anthocyanin', 'A', 10, 60, 15, 15, 10),";
        sql2 += "('Sông Amazon đổ ra đại dương nào?', 'Thái Bình Dương', 'Ấn Độ Dương', 'Đại Tây Dương', 'Bắc Băng Dương', 'C', 10, 25, 25, 40, 10),";
        sql2 += "('Chiến thắng Điện Biên Phủ diễn ra năm nào?', '1945', '1954', '1968', '1975', 'B', 10, 10, 70, 10, 10),";
        sql2 += "('Loại máu nào được gọi là máu chuyên cho?', 'O', 'A', 'B', 'AB', 'A', 10, 65, 15, 10, 10),";
        sql2 += "('Thủ đô của Úc là gì?', 'Sydney', 'Melbourne', 'Canberra', 'Perth', 'C', 10, 30, 30, 30, 10),";
        sql2 += "('Ai phát minh ra bóng đèn điện?', 'Tesla', 'Edison', 'Bell', 'Faraday', 'B', 10, 20, 55, 15, 10)";

        // Thực thi đợt 2
        db.execSQL(sql2);


        // ===================================================
        // ĐỢT 3: CÂU KHÓ (LEVEL 11 - 15)
        // ===================================================
        String sql3 = "INSERT INTO Question(content, a, b, c, d, correct, difficulty_id, rate_a, rate_b, rate_c, rate_d) VALUES ";

        // Level 11
        sql3 += "('Đâu là thủ đô của Canada?', 'Toronto', 'Vancouver', 'Montreal', 'Ottawa', 'D', 11, 25, 25, 20, 30),";
        sql3 += "('Năm 1010 ứng với thế kỷ nào?', 'X', 'XI', 'IX', 'XII', 'B', 11, 20, 45, 20, 15),";
        sql3 += "('Quốc gia nào có nhiều múi giờ nhất?', 'Nga', 'Mỹ', 'Pháp', 'Anh', 'C', 11, 20, 20, 35, 25),";
        sql3 += "('Hợp chất chính trong đá vôi là gì?', 'CaSO4', 'CaCO3', 'CaO', 'CaCl2', 'B', 11, 25, 40, 20, 15),";
        sql3 += "('Ai là người Việt Nam đầu tiên bay vào vũ trụ?', 'Phạm Tuân', 'Trịnh Công Sơn', 'Ngô Bảo Châu', 'Hoàng Phủ Ngọc Tường', 'A', 11, 50, 20, 15, 15),";
        sql3 += "('Giải Grand Slam gồm bao nhiêu giải đấu quần vợt?', '3', '4', '5', '6', 'B', 11, 25, 35, 20, 20),";
        sql3 += "('Tác phẩm \"Số đỏ\" trào phúng xã hội thời nào?', '1930-1945', '1945-1954', '1954-1975', 'Sau 1975', 'A', 11, 40, 20, 20, 20),";

        // Level 12
        sql3 += "('Quốc gia nào không có sông?', 'Saudi Arabia', 'Ai Cập', 'Lào', 'Mông Cổ', 'A', 12, 35, 25, 20, 20),";
        sql3 += "('Đại dương nào nhỏ nhất thế giới?', 'Ấn Độ Dương', 'Đại Tây Dương', 'Bắc Băng Dương', 'Nam Băng Dương', 'C', 12, 20, 20, 40, 20),";
        sql3 += "('Định lý Pitago dùng cho loại tam giác nào?', 'Tam giác đều', 'Tam giác cân', 'Tam giác vuông', 'Mọi tam giác', 'C', 12, 10, 10, 70, 10),";
        sql3 += "('Tên thật của nhà văn Kim Lân?', 'Nguyễn Văn Tài', 'Nguyễn Sen', 'Trần Hữu Tri', 'Nguyễn Tường Lân', 'A', 12, 35, 25, 20, 20),";
        sql3 += "('World Cup đầu tiên (1930) tổ chức ở đâu?', 'Brazil', 'Uruguay', 'Ý', 'Pháp', 'B', 12, 25, 35, 20, 20),";
        sql3 += "('Nguyên tố nào có ký hiệu hóa học là K?', 'Kẽm', 'Kali', 'Krypton', 'Kim cương', 'B', 12, 20, 40, 20, 20),";
        sql3 += "('Đâu không phải là tên một loại mây?', 'Mây Cumulus', 'Mây Cirrus', 'Mây Stratus', 'Mây Olympus', 'D', 12, 25, 25, 20, 30),";

        // Level 13
        sql3 += "('Ai là người vẽ bức tranh \"Bữa ăn tối cuối cùng\"?', 'Michelangelo', 'Raphael', 'Da Vinci', 'Donatello', 'C', 13, 20, 20, 40, 20),";
        sql3 += "('Đỉnh núi cao nhất hệ mặt trời nằm ở đâu?', 'Trái Đất', 'Sao Hỏa', 'Mặt Trăng', 'Sao Kim', 'B', 13, 20, 35, 25, 20),";
        sql3 += "('Thành phố nào là thủ đô của Thụy Sĩ?', 'Zurich', 'Geneva', 'Bern', 'Basel', 'C', 13, 20, 25, 35, 20),";
        sql3 += "('Ai là người phát minh ra thuốc nổ Dynamite?', 'Einstein', 'Nobel', 'Newton', 'Pascal', 'B', 13, 15, 45, 20, 20),";
        sql3 += "('Quốc gia nào có nhiều đảo nhất thế giới?', 'Indonesia', 'Philippines', 'Thụy Điển', 'Nhật Bản', 'C', 13, 30, 25, 35, 10),";
        sql3 += "('Vị vua nào có thời gian trị vì ngắn nhất lịch sử Việt Nam?', 'Dục Đức', 'Hiệp Hòa', 'Kiến Phúc', 'Đồng Khánh', 'A', 13, 35, 25, 20, 20),";
        sql3 += "('Khí nào chiếm tỷ lệ lớn nhất trong khí quyển Trái Đất?', 'Oxy', 'Nitơ', 'Carbonic', 'Agon', 'B', 13, 20, 40, 20, 20),";

        // Level 14
        sql3 += "('Tên viết tắt của Cơ quan Hàng không Vũ trụ Mỹ?', 'NATO', 'NASA', 'UNICEF', 'WHO', 'B', 14, 10, 70, 10, 10),";
        sql3 += "('Ngày 22/12 hàng năm là ngày gì?', 'Quốc tế phụ nữ', 'Nhà giáo VN', 'Quân đội NDVN', 'Thầy thuốc VN', 'C', 14, 15, 15, 55, 15),";
        sql3 += "('Ai là tác giả của tiểu thuyết \"Ông già và biển cả\"?', 'Hemingway', 'Mark Twain', 'Jack London', 'Victor Hugo', 'A', 14, 45, 20, 20, 15),";
        sql3 += "('Hóa chất nào thường dùng để khử trùng nước bể bơi?', 'Flo', 'Clo', 'I ốt', 'Brom', 'B', 14, 15, 55, 15, 15),";
        sql3 += "('Đâu là thủ đô của New Zealand?', 'Auckland', 'Wellington', 'Christchurch', 'Hamilton', 'B', 14, 25, 35, 20, 20),";
        sql3 += "('Loài vật nào có nhịp tim chậm nhất?', 'Voi', 'Cá voi xanh', 'Rùa', 'Gấu ngủ đông', 'B', 14, 20, 40, 20, 20),";
        sql3 += "('Bức tượng Nữ thần Tự do là quà tặng của nước nào cho Mỹ?', 'Anh', 'Pháp', 'Đức', 'Ý', 'B', 14, 20, 50, 15, 15),";

        // Level 15
        sql3 += "('Người phụ nữ đầu tiên giành giải Nobel là ai?', 'Marie Curie', 'Mother Teresa', 'Rosalind Franklin', 'Jane Addams', 'A', 15, 40, 20, 20, 20),";
        sql3 += "('Hành tinh nào nóng nhất hệ mặt trời?', 'Sao Thủy', 'Sao Kim', 'Sao Hỏa', 'Mặt Trời', 'B', 15, 30, 35, 20, 15),";
        sql3 += "('Quốc gia nào có diện tích rừng lớn nhất thế giới?', 'Brazil', 'Canada', 'Nga', 'Mỹ', 'C', 15, 20, 25, 35, 20),";
        sql3 += "('Ai là người sáng lập ra giải thưởng Nobel?', 'Alfred Nobel', 'Ludwig Nobel', 'Robert Nobel', 'Emil Nobel', 'A', 15, 50, 20, 15, 15),";
        sql3 += "('Chất duy nhất ở dạng lỏng ở nhiệt độ thường là kim loại nào?', 'Nhôm', 'Sắt', 'Thủy ngân', 'Chì', 'C', 15, 15, 15, 60, 10),";
        sql3 += "('Thành phố nào được gọi là \"Hòn ngọc Viễn Đông\" xưa kia?', 'Hà Nội', 'Sài Gòn', 'Hồng Kông', 'Singapore', 'B', 15, 20, 50, 15, 15),";
        sql3 += "('Tác phẩm văn học đầu tiên của nhân loại được viết bằng chữ gì?', 'Chữ Hán', 'Chữ Nôm', 'Chữ Tượng hình Ai Cập', 'Chữ Hình nêm (Cuneiform)', 'D', 15, 25, 25, 15, 35)";

        // Thực thi đợt 3
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Difficulty");
        db.execSQL("DROP TABLE IF EXISTS HighScore");
        // Tạo lại từ đầu
        onCreate(db);
    }
    public long insertQuestion(String content, String a, String b, String c, String d,
                               String correct, int difficulty,
                               int rateA, int rateB, int rateC, int rateD) {

        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues values = new android.content.ContentValues();

        values.put("content", content);
        values.put("a", a);
        values.put("b", b);
        values.put("c", c);
        values.put("d", d);
        values.put("correct", correct);
        values.put("difficulty_id", difficulty);
        values.put("rate_a", rateA);
        values.put("rate_b", rateB);
        values.put("rate_c", rateC);
        values.put("rate_d", rateD);

        long result = db.insert("Question", null, values);
        db.close();
        return result;
    }
}