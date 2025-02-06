package fpoly.anhltph45438.dam_anhltph45438.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.FragLoaisach;
import fpoly.anhltph45438.dam_anhltph45438.Model.LoaiSachModel;
import fpoly.anhltph45438.dam_anhltph45438.R;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSachModel> {
    private Context context;
    FragLoaisach frloaisach;
    private ArrayList<LoaiSachModel> list;
    TextView tv_maloai,tv_tenloai;
    ImageButton img_xoaloaisach;

    public LoaiSachAdapter(@NonNull Context context, FragLoaisach frls, ArrayList<LoaiSachModel> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.frloaisach=frls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View v=convertView;
       if(v==null){
           LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           v=inf.inflate(R.layout.item_loaisach,null);

       }
       final LoaiSachModel ls=list.get(position);
       if(ls!=null){
           tv_maloai=v.findViewById(R.id.tv_maloai);
           tv_tenloai=v.findViewById(R.id.tv_tenloai);
           tv_maloai.setText("Mã loại: "+ls.maLoai);
           tv_tenloai.setText("Tên loại: "+ls.tenLoai);
           img_xoaloaisach=v.findViewById(R.id.img_xoaloaisach);
       }
//       img_xoaloaisach.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               frloaisach.xoa(String.valueOf(ls.maLoai));
//           }
//       });
       return v;
    }
}
