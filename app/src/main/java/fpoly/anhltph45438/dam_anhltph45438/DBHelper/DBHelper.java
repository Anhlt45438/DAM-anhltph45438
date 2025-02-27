package fpoly.anhltph45438.dam_anhltph45438.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "QLTV", null, 14);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Thủ Thư
        String tb_thuthu = "CREATE TABLE ThuThu (" +
                "maTT TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(tb_thuthu);
        db.execSQL("INSERT INTO ThuThu(maTT, hoTen, matKhau) VALUES " +
                "('tt01', 'Lê Tuấn Anh', '123')," +
                "('tt02', 'Trần Thị Mai', '456')," +
                "('tt03', 'Lê Văn Hùng', '789')");

        // Tạo bảng Thành Viên
        String tb_thanhvien = "CREATE TABLE ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL)";
        db.execSQL(tb_thanhvien);
        db.execSQL("INSERT INTO ThanhVien(maTV, hoTen, namSinh) VALUES " +
                "(1, 'Nguyễn Văn A', '2004')," +
                "(2, 'Phạm Thị B', '2003')," +
                "(3, 'Hoàng Văn C', '2002')");

        // Tạo bảng Loại Sách
        String tb_loaisach = "CREATE TABLE LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(tb_loaisach);
        db.execSQL("INSERT INTO LoaiSach(maLoai, tenLoai) VALUES " +
                "(1, 'CNTT')," +
                "(2, 'Kinh Tế')");

        // Tạo bảng Sách
        String tb_sach = "CREATE TABLE Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER NOT NULL," +
                "FOREIGN KEY (maLoai) REFERENCES LoaiSach(maLoai))";
        db.execSQL(tb_sach);
        db.execSQL("INSERT INTO Sach(maSach, tenSach, giaThue, maLoai) VALUES " +
                "(1, 'Java1', 3000, 1)," +
                "(2, 'Android Basics', 3500, 1)," +
                "(3, 'Kinh Tế Vĩ Mô', 4000, 2)");

        // Tạo bảng Phiếu Mượn
        String tb_phieumuon = "CREATE TABLE PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT NOT NULL," +
                "maTV INTEGER NOT NULL," +
                "maSach INTEGER NOT NULL," +
                "tienThue INTEGER NOT NULL," +
                "traSach INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "FOREIGN KEY (maTT) REFERENCES ThuThu(maTT)," +
                "FOREIGN KEY (maTV) REFERENCES ThanhVien(maTV)," +
                "FOREIGN KEY (maSach) REFERENCES Sach(maSach))";
        db.execSQL(tb_phieumuon);
        db.execSQL("INSERT INTO PhieuMuon(maPM, maTT, maTV, maSach, tienThue, traSach, ngay) VALUES " +
                "(1, 'tt01', 1, 1, 3000, 1, '2024-01-01')," +
                "(2, 'tt02', 2, 2, 3500, 0, '2024-01-10')," +
                "(3, 'tt03', 3, 3, 4000, 1, '2024-01-15')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS ThuThu");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS LoaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            onCreate(db);
        }
    }
}
