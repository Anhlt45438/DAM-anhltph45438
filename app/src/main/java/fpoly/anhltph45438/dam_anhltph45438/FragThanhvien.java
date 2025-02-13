package fpoly.anhltph45438.dam_anhltph45438;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.Adapter.ThanhVienAdapter;
import fpoly.anhltph45438.dam_anhltph45438.DAO.ThanhVienDAO;
import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;

public class FragThanhvien extends Fragment {
    ListView lv;
    ArrayList<ThanhVienModel> list;
    ImageView img_them;
    Dialog dialog;
    EditText ed_tenthanhvien,ed_namsinh,ed_mathanhvien;
    Button btn_them_thanhvien,btn_cancel_thanhvien;
    static ThanhVienDAO dao;
    ThanhVienAdapter adt;
    ThanhVienModel tv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frag_thanhvien,container,false);
        lv=v.findViewById(R.id.lv_thanhvien);
        img_them=v.findViewById(R.id.img_themthanhvien);
        dao=new ThanhVienDAO(getActivity());
        capNhatLV();
        img_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tv=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }


    protected void openDialog(final Context context, final int type){

        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_themthanhvien);
        ed_tenthanhvien=dialog.findViewById(R.id.ed_tenthanhvien);
        ed_namsinh=dialog.findViewById(R.id.ed_namsinh);
        ed_mathanhvien=dialog.findViewById(R.id.ed_mathanhvien);
        btn_them_thanhvien=dialog.findViewById(R.id.btn_them_thanhvien);
        btn_cancel_thanhvien=dialog.findViewById(R.id.btn_cancel_thanhvien);

        ed_mathanhvien.setEnabled(false);
        if(type!=0){
            ed_mathanhvien.setText(String.valueOf(tv.maTV));
            ed_tenthanhvien.setText(tv.hoTen);
            ed_namsinh.setText(tv.namSinh);

        }
        btn_cancel_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_them_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv=new ThanhVienModel();
                tv.hoTen=ed_tenthanhvien.getText().toString();
                tv.namSinh=ed_namsinh.getText().toString();
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(tv)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(type!=0){
                        tv.maTV=Integer.parseInt(ed_mathanhvien.getText().toString());
                        if(dao.update(tv)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(final String id){

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa dữ liệu");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLV();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    void capNhatLV(){

        list=(ArrayList<ThanhVienModel>) dao.getList();
        adt=new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adt);
    }
    public int validate(){

        int check=1;
        if(ed_tenthanhvien.getText().length()==0 || ed_namsinh.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}
