package fpoly.anhltph45438.dam_anhltph45438.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;
import fpoly.anhltph45438.dam_anhltph45438.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVienModel> {
    private Context context;
    private ArrayList<ThanhVienModel> list;
    TextView tv_matv,tv_tentv;
    public ThanhVienSpinnerAdapter(@NonNull Context context,ArrayList<ThanhVienModel> list) {
        super(context,0,list);
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_spinner_thanhvien,null);
        }
        final ThanhVienModel tv=list.get(position);
        if(tv!=null){
            tv_matv=v.findViewById(R.id.tv_spinner_mathanhvien);
            tv_tentv=v.findViewById(R.id.tv_spinner_tenthanhvien);
            tv_matv.setText(tv.maTV+"");
            tv_tentv.setText(tv.hoTen);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_spinner_thanhvien,null);
        }
        final ThanhVienModel tv=list.get(position);
        if(tv!=null){
            tv_matv=v.findViewById(R.id.tv_spinner_mathanhvien);
            tv_tentv=v.findViewById(R.id.tv_spinner_tenthanhvien);
            tv_matv.setText(tv.maTV+"");
            tv_tentv.setText(tv.hoTen);
        }
        return v;
    }
}
