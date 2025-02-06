package fpoly.anhltph45438.dam_anhltph45438.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.DAO.SachDAO;
import fpoly.anhltph45438.dam_anhltph45438.DAO.ThanhVienDAO;
import fpoly.anhltph45438.dam_anhltph45438.FragPhieuMuon;
import fpoly.anhltph45438.dam_anhltph45438.Model.PhieuMuonModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.SachModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;
import fpoly.anhltph45438.dam_anhltph45438.R;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuonModel> {
    private Context context;
    private ArrayList<PhieuMuonModel> list;
    FragPhieuMuon frpm;
    TextView tv_mapm,tv_tentv,tv_tensach,tv_tienthue,tv_ngay,tv_trasach;
    ImageButton img_xoaphieumuon;
    SachDAO sachdao;
    ThanhVienDAO tvdao;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(@NonNull Context context, FragPhieuMuon frpm,ArrayList<PhieuMuonModel> list) {
        super(context, 0,list);
        this.context=context;
        this.frpm=frpm;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_phieumuon,null);
        }
        final PhieuMuonModel pm=list.get(position);
        if(pm!=null){
            tv_mapm=v.findViewById(R.id.tv_Maphieumuon);
            tv_mapm.setText("Mã phiếu mượn: "+pm.maPM+"");
            sachdao=new SachDAO(context);
            SachModel sa=sachdao.getID(String.valueOf(pm.maSach));
            tv_tensach=v.findViewById(R.id.tv_Tensach);
            tv_tensach.setText("Tên sách: "+sa.tenSach);
            tvdao=new ThanhVienDAO(context);
            ThanhVienModel tv=tvdao.getID(String.valueOf(pm.maTV));
            tv_tentv=v.findViewById(R.id.tv_Tenthanhvien);
            tv_tentv.setText("Thành viên: "+tv.hoTen);
            tv_tienthue=v.findViewById(R.id.tv_Tienthue);
            tv_tienthue.setText("Tiền thuê: "+pm.tienThue+"");
            tv_ngay=v.findViewById(R.id.tv_Ngaythue);
            tv_ngay.setText("Ngày thuê: "+sdf.format(pm.ngay));

            tv_trasach=v.findViewById(R.id.tv_Trasach);
            if(pm.traSach==1){
                tv_trasach.setTextColor(Color.BLUE);
                tv_trasach.setText("Đã trả sách");
            }else {
                tv_trasach.setTextColor(Color.RED);
                tv_trasach.setText("Chưa trả sách");
            }
            img_xoaphieumuon=v.findViewById(R.id.img_xoaphieumuon);

        }
//        img_xoaphieumuon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                frpm.xoa(String.valueOf(pm.maPM));
//            }
//        });
        return v;
    }
}
