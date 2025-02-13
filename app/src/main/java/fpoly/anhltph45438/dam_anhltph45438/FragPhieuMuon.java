package fpoly.anhltph45438.dam_anhltph45438;

import static java.time.MonthDay.now;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.Adapter.PhieuMuonAdapter;
import fpoly.anhltph45438.dam_anhltph45438.Adapter.SachSpinnerAdapter;
import fpoly.anhltph45438.dam_anhltph45438.Adapter.ThanhVienSpinnerAdapter;
import fpoly.anhltph45438.dam_anhltph45438.DAO.PhieuMuonDAO;
import fpoly.anhltph45438.dam_anhltph45438.DAO.SachDAO;
import fpoly.anhltph45438.dam_anhltph45438.DAO.ThanhVienDAO;
import fpoly.anhltph45438.dam_anhltph45438.Model.PhieuMuonModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.SachModel;
import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;

public class FragPhieuMuon extends Fragment {


    ListView lv_phieumuon;
    ArrayList<PhieuMuonModel> list;
    ImageView img_themphieumuon;
    Dialog dialog;
    EditText ed_maPM;
    Spinner sptv,spsach;
    TextView tv_ngay,tv_tienthue;
    CheckBox chk_trasach;
    Button btn_them_phieumuon,btn_cancel_phieumuon;
    static PhieuMuonDAO pmdao;
    PhieuMuonAdapter adt;
    PhieuMuonModel pm;
    ThanhVienSpinnerAdapter tvspnadt;
    ArrayList<ThanhVienModel> list_tv;
    ThanhVienDAO tvdao;
    ThanhVienModel tv;
    int maThanhVien;
    SachSpinnerAdapter sachspnadt;
    ArrayList<SachModel> list_sach;
    SachDAO sachdao;
    SachModel sach;
    int maSach,tienThue;
    int positionTV,positionSach;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_frag_phieu_muon,container,false);
        lv_phieumuon=v.findViewById(R.id.lv_phieumuon);
        img_themphieumuon=v.findViewById(R.id.img_themphieumuon);
        pmdao=new PhieuMuonDAO(getActivity());
        capNhatLV();
        img_themphieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv_phieumuon.setOnItemLongClickListener((parent, view, position, id) -> {
            pm=list.get(position);
            openDialog(getActivity(),1);
            return false;
        });
        return v;

    }
    protected void openDialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_themphieumuon);
        ed_maPM=dialog.findViewById(R.id.mpm);
        sptv=dialog.findViewById(R.id.spinner_thanhvien);
        spsach=dialog.findViewById(R.id.spinner_sach_phieumuon);
        tv_ngay=dialog.findViewById(R.id.tv_ngaythue);
        tv_tienthue=dialog.findViewById(R.id.tv_tienthue);
        chk_trasach=dialog.findViewById(R.id.chk_trasach);
        btn_cancel_phieumuon=dialog.findViewById(R.id.btn_cancel_phieumuon);
        btn_them_phieumuon=dialog.findViewById(R.id.btn_them_phieumuon);
        tvdao=new ThanhVienDAO(context);
        list_tv=new ArrayList<>();
        list_tv=(ArrayList<ThanhVienModel>) tvdao.getList();
        tvspnadt=new ThanhVienSpinnerAdapter(context,list_tv);
        sptv.setAdapter(tvspnadt);
        sptv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien=list_tv.get(position).maTV;
                Toast.makeText(context, "Chọn "+list_tv.get(position).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachdao=new SachDAO(context);
        list_sach=new ArrayList<>();
        list_sach=(ArrayList<SachModel>) sachdao.getList();
        sachspnadt=new SachSpinnerAdapter(context,list_sach);
        spsach.setAdapter(sachspnadt);

        spsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach=list_sach.get(position).maSach;
                tienThue=list_sach.get(position).giaThue;
                Toast.makeText(context, "Chọn "+list_sach.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ed_maPM.setEnabled(false);
        if(type!=0){
            ed_maPM.setText(String.valueOf(pm.maPM));
            for(int i=0;i<list_tv.size();i++){
                if(pm.maTV==(list_tv.get(i).maTV)){
                    positionTV=i;
                }}
            sptv.setSelection(positionTV);
            for(int i=0;i<list_sach.size();i++){
                if(pm.maSach==(list_sach.get(i).maSach)){
                    positionSach=i;
                }}
            spsach.setSelection(positionSach);
            tv_ngay.setText("Ngày thuê: "+sdf.format(pm.ngay));
            tv_tienthue.setText("Tiền thuê: "+pm.tienThue);
            if(pm.traSach==1){
                chk_trasach.setChecked(true);
            }else {
                chk_trasach.setChecked(false);
            }
        }
        btn_cancel_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_them_phieumuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pm=new PhieuMuonModel();
                pm.maSach=maSach;
                pm.maTV=maThanhVien;
                pm.ngay= java.sql.Date.valueOf(String.valueOf(now()));
                pm.tienThue=tienThue;
                if(chk_trasach.isChecked()){
                    pm.traSach=1;
                }else {
                    pm.traSach=0;
                }
                if(validate()>0){
                    if(type==0){
                        if(pmdao.insert(pm)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        pm.maPM=Integer.parseInt(ed_maPM.getText().toString());
                        if(pmdao.update(pm)>0){

                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLV();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void xoa(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa phiếu mượn!");
        builder.setMessage("Bạn có muốn xóa phiếu mượn này?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pmdao.delete(id);
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
        builder.show();
    }
    void capNhatLV(){
        list=(ArrayList<PhieuMuonModel>) pmdao.getList();
        adt=new PhieuMuonAdapter(getActivity(),this,list);
        lv_phieumuon.setAdapter(adt);
    }
    public int validate(){
        return 1;

    }
}
