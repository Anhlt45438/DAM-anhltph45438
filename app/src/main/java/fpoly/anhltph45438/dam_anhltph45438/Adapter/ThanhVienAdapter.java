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
import java.util.List;

import fpoly.anhltph45438.dam_anhltph45438.FragThanhvien;
import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;
import fpoly.anhltph45438.dam_anhltph45438.R;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVienModel> {
    private Context context;
    FragThanhvien frtv;
    private ArrayList<ThanhVienModel> list;
    TextView tv_mathanhvien,tv_tenthanhvien,tv_namssinh;
    ImageButton img_xoathanhvien;
    public ThanhVienAdapter(@NonNull Context context, FragThanhvien frtv, ArrayList<ThanhVienModel> list) {
        super(context, 0, list);
        this.context=context;
        this.list=list;
        this.frtv=frtv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_thanhvien,null);
        }
        final ThanhVienModel item=list.get(position);
        if(item!=null){
            tv_mathanhvien=v.findViewById(R.id.tv_mathanhvien);
            tv_tenthanhvien=v.findViewById(R.id.tv_tenthanhvien);
            tv_namssinh=v.findViewById(R.id.tv_namsinh);
            img_xoathanhvien=v.findViewById(R.id.img_xoathanhvien);

            tv_mathanhvien.setText("Mã thành viên: "+item.maTV);
            tv_tenthanhvien.setText("Tên thành viên: "+item.hoTen);
            tv_namssinh.setText("Năm sinh: "+item.namSinh);

//            img_xoathanhvien.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    frtv.xoa(String.valueOf(item.maTV));
//                }
//            });

        }
        return v;
    }
}
