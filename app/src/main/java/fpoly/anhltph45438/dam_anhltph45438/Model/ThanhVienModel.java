package fpoly.anhltph45438.dam_anhltph45438.Model;

public class ThanhVienModel {
    public int maTV;
    public String hoTen;
    public String namSinh;

    public ThanhVienModel() {
    }

    public ThanhVienModel(String hoTen_tv, String namSinh) {
        this.hoTen = hoTen_tv;
        this.namSinh = namSinh;
    }

    public ThanhVienModel(int maTV, String hoTen_tv, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen_tv;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen_tv() {
        return hoTen;
    }

    public void setHoTen_tv(String hoTen_tv) {
        this.hoTen = hoTen_tv;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}

