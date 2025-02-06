package fpoly.anhltph45438.dam_anhltph45438.Model;

public class LoaiSachModel {
    public int maLoai;
    public String tenLoai;

    public LoaiSachModel() {
    }

    public LoaiSachModel(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSachModel(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }


}
