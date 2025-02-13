package fpoly.anhltph45438.dam_anhltph45438;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.Adapter.LoaiSachSpinnerAdapter;
import fpoly.anhltph45438.dam_anhltph45438.Adapter.SachAdapter;
import fpoly.anhltph45438.dam_anhltph45438.DAO.LoaiSachDAO;
import fpoly.anhltph45438.dam_anhltph45438.DAO.SachDAO;
import fpoly.anhltph45438.dam_anhltph45438.Model.LoaiSachModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.SachModel;

public class FragSach extends Fragment {
    ListView lv_sach;
    ArrayList<SachModel> list;
    ImageView img_themsach;
    Dialog dialog;
    EditText ed_masach,ed_tensach,ed_giathue;
    Button btn_themsach,btn_cancelsach;
    Spinner spnloaisach;
    static SachDAO dao;
    SachAdapter adt;
    SachModel sa;
    LoaiSachSpinnerAdapter spnadt;
    ArrayList<LoaiSachModel> list_ls;
    LoaiSachDAO lsdao;
    LoaiSachModel ls;
    int maLoaissach,position;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_frag_sach,container,false);
        lv_sach=v.findViewById(R.id.lv_sach);
        img_themsach=v.findViewById(R.id.img_themsach);
        dao=new SachDAO(getActivity());
        capNhatLV();
        img_themsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);

            }
        });
        lv_sach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sa=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;

    }
    protected void openDialog(final Context context, final int type){

        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_themsach);
        ed_masach=dialog.findViewById(R.id.ed_masach);
        ed_tensach=dialog.findViewById(R.id.ed_tensach);
        ed_giathue=dialog.findViewById(R.id.ed_giathue);
        spnloaisach=dialog.findViewById(R.id.spn_loaisach);
        btn_themsach=dialog.findViewById(R.id.btn_them_sach);
        btn_cancelsach=dialog.findViewById(R.id.btn_cancel_sach);
        list_ls=new ArrayList<LoaiSachModel>();
        lsdao=new LoaiSachDAO(context);
        list_ls=(ArrayList<LoaiSachModel>) lsdao.getList();
        spnadt=new LoaiSachSpinnerAdapter(context,list_ls);
        spnloaisach.setAdapter(spnadt);

        spnloaisach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaissach=list_ls.get(position).maLoai;
                Toast.makeText(context, "Chọn" +list_ls.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ed_masach.setEnabled(false);
        if(type==1){
            ed_masach.setText(String.valueOf(sa.maSach));
            ed_tensach.setText(sa.tenSach);
            ed_giathue.setText(String.valueOf(sa.giaThue));
            for(int i=0; i<list_ls.size();i++){
                if(sa.maLoai == (list_ls.get(i).maLoai)){
                    position=i;
                }
                Log.i("demo","posSach"+position);
                spnloaisach.setSelection(position);
            }}
        btn_cancelsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_themsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sa=new SachModel();
                sa.tenSach=ed_tensach.getText().toString();
                sa.giaThue=Integer.parseInt(ed_giathue.getText().toString());
                sa.maLoai=maLoaissach;
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(sa)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        sa.maSach=Integer.parseInt(ed_masach.getText().toString());
                        if(dao.update(sa)>0){
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
        builder.setTitle("Xóa Sách!");
        builder.setMessage("Bạn có muốn xóa sách này không?");
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
        list=(ArrayList<SachModel>) dao.getList();
        adt=new SachAdapter(getActivity(),this,list);
        lv_sach.setAdapter(adt);
    }
    public int validate(){
        int check=1;
        if(ed_tensach.getText().length()==0 || ed_giathue.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}
