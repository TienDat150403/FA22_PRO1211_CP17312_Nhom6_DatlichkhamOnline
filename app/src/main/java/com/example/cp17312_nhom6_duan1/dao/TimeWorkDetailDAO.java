package com.example.cp17312_nhom6_duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cp17312_nhom6_duan1.database.MyDbHelper;
import com.example.cp17312_nhom6_duan1.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class TimeWorkDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbhelper;

    public TimeWorkDetailDAO(Context context){
        dbhelper = new MyDbHelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(TimeWorkDetailDTO.colTime,dtoTimeWorkDetail.getTime());
        val.put(TimeWorkDetailDTO.colTimework_id,dtoTimeWorkDetail.getTimework_id());

        long res  =db.insert(TimeWorkDetailDTO.nameTable,null,val);
        return res;
    }
    public int deleteRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};
        int res = db.delete(TimeWorkDetailDTO.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(TimeWorkDetailDTO.colTime,dtoTimeWorkDetail.getTime());
        val.put(TimeWorkDetailDTO.colTimework_id,dtoTimeWorkDetail.getTimework_id());
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};

        int res = db.update(TimeWorkDetailDTO.nameTable,val,"id = ?",check);
        return res;
    }

    public ArrayList<TimeWorkDetailDTO> selectAll(){
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO dtoTimeWorkDetail = new TimeWorkDetailDTO();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

    public ArrayList<TimeWorkDetailDTO> selectTimeWorkDetailByTimeWorkId(int idTimeWork){
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
        String where = "timework_id = ?";
        String[] whereArgs = new String[]{idTimeWork+""};
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO dtoTimeWorkDetail = new TimeWorkDetailDTO();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

}