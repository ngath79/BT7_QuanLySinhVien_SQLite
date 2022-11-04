package com.example.quanlysinhvien235;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlysinhvien235.adapter.Database;
import com.example.quanlysinhvien235.adapter.SinhVienAdapter;
import com.example.quanlysinhvien235.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static SinhVienAdapter adapter;
    public static List<SinhVien> sinhviens = new ArrayList<SinhVien>();
    public static Database db;
    public static String idTruyen;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        recyclerView = findViewById(R.id.RecycleViewSV);
        Intent intent = getIntent();
        idTruyen = intent.getStringExtra("idClass235");
        System.out.println(idTruyen.toString().trim());
        db = new Database(this, "SINHVIEN.sqlite", null, 1);
        getDataComputer();

        Button button = (Button) findViewById(R.id.btn_ThemSV);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.sinhvien_new);
                dialog.show();
                TextView tv1 = (TextView) dialog.findViewById(R.id.isIDC);
                TextView tv2 = (TextView) dialog.findViewById(R.id.isNameC);
                TextView tv3 = (TextView) dialog.findViewById(R.id.isIDCl);
                tv3.setText(idTruyen.toString().trim());
                Button btok = (Button) dialog.findViewById(R.id.btn_okC);
                Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelC);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.insertCSV235(tv1.getText().toString().trim(), tv2.getText().toString().trim(), tv3.getText().toString());
                        getDataComputer();
                        dialog.dismiss();

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
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataComputer();
            }
        });
    }

    public void getDataComputer() {
        AddActivity.sinhviens = new ArrayList<SinhVien>();
        Cursor cursor = db.GetData("Select * from SinhVien235 where idClass235 = '"+idTruyen.toString().trim() +"'");
        while (cursor.moveToNext()){
            @SuppressLint("Range") String idC =cursor.getString(cursor.getColumnIndex("idMSV235"));
            @SuppressLint("Range") String nameC = cursor.getString(cursor.getColumnIndex("nameSinhVien235"));
            @SuppressLint("Range") String idCategory = cursor.getString(cursor.getColumnIndex("idClass235"));
            System.out.println(idC+" "+nameC+" "+idCategory);
            sinhviens.add(new SinhVien(idC,nameC,idCategory));
//            computers.add(new Computer(cursor.getString(cursor.getColumnIndex("idComputer")),cursor.getString(cursor.getColumnIndex("nameComputer")),cursor.getString(cursor.getColumnIndex("idCategory"))));
        }
        adapter = new SinhVienAdapter(sinhviens, this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}