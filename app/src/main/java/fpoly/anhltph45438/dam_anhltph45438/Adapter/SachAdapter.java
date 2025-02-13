package fpoly.anhltph45438.dam_anhltph45438.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.DAO.LoaiSachDAO;
import fpoly.anhltph45438.dam_anhltph45438.FragSach;
import fpoly.anhltph45438.dam_anhltph45438.Model.LoaiSachModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.SachModel;
import fpoly.anhltph45438.dam_anhltph45438.R;

public class SachAdapter extends ArrayAdapter<SachModel> {
    private Context context;
    private ArrayList<SachModel> list;
    FragSach frs;
    TextView tv_masach,tv_tensach,tv_giathue,tv_loai;
    ImageButton img_xoasach;
    public SachAdapter(@NonNull Context context, FragSach frs,ArrayList<SachModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.frs=frs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_sach,null);

        }
        final SachModel sa=list.get(position);
        if(sa!=null){
            LoaiSachDAO lsdao=new LoaiSachDAO(context);
            LoaiSachModel ls=lsdao.getID(String.valueOf(sa.maLoai));
            tv_masach=v.findViewById(R.id.tv_masach);
            tv_masach.setText("Mã sách: "+sa.maSach);

            tv_tensach=v.findViewById(R.id.tv_tensach);
            tv_tensach.setText("Tên sách: "+sa.tenSach);

            tv_giathue=v.findViewById(R.id.tv_giathue);
            tv_giathue.setText("Giá thuê: "+sa.giaThue);

            tv_loai=v.findViewById(R.id.tv_tenloaisach);
            tv_loai.setText("Loại sách: "+ls.tenLoai);
            img_xoasach=v.findViewById(R.id.img_xoasach);
        }
        img_xoasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frs.xoa(String.valueOf(sa.maSach));
            }
        });
        return v;
    }
}
