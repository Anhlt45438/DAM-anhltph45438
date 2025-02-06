package fpoly.anhltph45438.dam_anhltph45438.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import fpoly.anhltph45438.dam_anhltph45438.DBHelper.DBHelper;
import fpoly.anhltph45438.dam_anhltph45438.Model.ThanhVienModel;

public class ThanhVienDAO {
    private DBHelper mydb;
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context){
        mydb=new DBHelper(context);
        db=mydb.getWritableDatabase();
    }
    public long insert(ThanhVienModel tv){
        ContentValues values=new ContentValues();
        values.put("hoTen",tv.hoTen);
        values.put("namSinh",tv.namSinh);
        return db.insert("ThanhVien",null,values);
    }

    public int update(ThanhVienModel tv){

        ContentValues values=new ContentValues();
        values.put("hoTen",tv.hoTen);
        values.put("namSinh",tv.namSinh);
        return db.update("Thanhvien",values,"maTV=?",new String[]{String.valueOf(tv.maTV)});
    }

    public int delete(String id){

        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    public ArrayList<ThanhVienModel> getList(){

        String sql="select * from ThanhVien";
        return getData(sql);
    }

    public ThanhVienModel getID(String id){

        String sql="select * from ThanhVien where maTV=?";
        ArrayList<ThanhVienModel> list=getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private ArrayList<ThanhVienModel> getData(String sql, String...selectionArgs){
        ArrayList<ThanhVienModel> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            ThanhVienModel tv=new ThanhVienModel();
            tv.maTV=Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            tv.hoTen=c.getString(c.getColumnIndex("hoTen"));
            tv.namSinh=c.getString(c.getColumnIndex("namSinh"));
            list.add(tv);
        }
        return list;
    }
}
