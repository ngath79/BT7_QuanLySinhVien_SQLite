package com.example.quanlysinhvien235;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlysinhvien235.adapter.Database;
import com.example.quanlysinhvien235.model.Class;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db235;
    List<Class> lop235 = new ArrayList<Class>();
    ArrayAdapter<Class> lopArrayAdapter;
    boolean kt = true;
    ListView lvlop235;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db235 = new Database(this,"SINHVIEN.sqlite",null,1);
        db235.QueryData("create table IF NOT EXISTS Class235(idClass235 VARCHAR(100),nameClass235 nVARCHAR(100))");
        db235.QueryData("create table IF NOT EXISTS SinhVien235(idMSV235 VARCHAR(100),nameSinhVien235 VARCHAR(100),idClass235 VARCHAR(100))");

        Cursor cursor = db235.GetData("select * from Class235");
        if (cursor.getCount()==0){
            db235.insertCL235("LSH01","Lớp sinh hoạt 20T1");
            db235.insertCL235("LSH02","Lớp sinh hoạt 20T2");
            db235.insertCL235("LSH03","Lớp sinh hoạt 20T3");
            db235.insertCL235("LSH04","Lớp sinh hoạt 20CD01");
            db235.insertCL235("LSH05","Lớp sinh hoạt 20CD02");
            db235.insertCL235("LSH06","Lớp sinh hoạt 20CD03");
            db235.insertCL235("LSH07","Lớp sinh hoạt 20TDH01");
            db235.insertCL235("LSH08","Lớp sinh hoạt 20TDH02");
            db235.insertCL235("LSH09","Lớp sinh hoạt 20TDH03");
            db235.insertCL235("LSH10","Lớp sinh hoạt 20HTP01");
            db235.insertCL235("LSH11","Lớp sinh hoạt 20HTP02");
            db235.insertCL235("LSH12","Lớp sinh hoạt 20HTP03");
            db235.insertCL235("LSH13","Lớp sinh hoạt 20XC01");
            db235.insertCL235("LSH14","Lớp sinh hoạt 20XC02");
            db235.insertCL235("LSH15","Lớp sinh hoạt 20XC03");

        }
        cursor = db235.GetData("select * from SinhVien235");
        if (cursor.getCount()==0){
            db235.insertCSV235("2050531200235","Mai Thị Nga","LSH01");


            db235.insertCSV235("MSV01","Nguyễn Thị Thư Trang","LSH03");
            db235.insertCSV235("MSV02","Đặng Thị Anh Vy","LSH03");

            db235.insertCSV235("MSV03","Phan Thi Hoai Anh","LSH02");
            db235.insertCSV235("MSV04","Đặng Thi Thuỳ Dung","LSH02");



            db235.insertCSV235("MSV05","Tán Thị Thanh Liên","LSH04");

            db235.insertCSV235("MSV06","Mai Văn Hải","LSH05");
        }

        lvlop235= (ListView) findViewById(R.id.listviewClass);

        getDataCategory();
        lvlop235.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("idClass235", lop235.get(i).getIdClass());
                System.out.println(lop235.get(i).getIdClass());
                kt=true;
                startActivity(intent);
            }
        });

        lvlop235.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j=i;
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
                alertDiaLog.setTitle("Thông báo");
                alertDiaLog.setMessage("Bạn có muốn xóa "+lop235.get(i).getIdClass()+" không?"    );
                alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db235.QueryData("delete from Class235 where idClass235 ='"+lop235.get(j).getIdClass()+"'");
                        getDataCategory();
                        lopArrayAdapter.notifyDataSetChanged();
                    }
                });
                alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDiaLog.show();
                return true;
            }
        });
        Button button = (Button) findViewById(R.id.btn_themClass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.class_new);
                dialog.show();
                TextView tv1 = (TextView) dialog.findViewById(R.id.isIDCa);
                TextView tv2 = (TextView) dialog.findViewById(R.id.isNameClass);
                Button btok = (Button) dialog.findViewById(R.id.btn_okClass);
                Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelClass);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db235.insertCL235(tv1.getText().toString().trim(),tv2.getText().toString().trim());
                        dialog.dismiss();
                        getDataCategory();
                        lopArrayAdapter.notifyDataSetChanged();
                    }
                });
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
    }

    public void getDataCategory() {
        lop235 = new ArrayList<Class>();
        Cursor cursor = db235.GetData("select * from Class235");
        System.out.println("ABC " + cursor.getCount());
        if (cursor.getCount()>0){
            System.out.println("abc");
            while (cursor.moveToNext()){
                @SuppressLint("Range") String idCa = cursor.getString(cursor.getColumnIndex("idClass235"));
                @SuppressLint("Range") String nameCa = cursor.getString(cursor.getColumnIndex("nameClass235"));
                lop235.add(new Class(idCa,nameCa));
            }
        }
        lopArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,lop235);
        lvlop235.setAdapter(lopArrayAdapter);
        lopArrayAdapter.notifyDataSetChanged();
    }
}