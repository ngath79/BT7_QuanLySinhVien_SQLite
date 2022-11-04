package com.example.quanlysinhvien235.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysinhvien235.AddActivity;
import com.example.quanlysinhvien235.R;
import com.example.quanlysinhvien235.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {
    private List<SinhVien> sinhVien235s;
    private Context context235;

    public SinhVienAdapter(List<SinhVien> sinhViens, Context context) {
        this.sinhVien235s = sinhViens;
        this.context235 = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sinhvien, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SinhVien computer =sinhVien235s.get(position);
        holder.idmsv235.setText(computer.getIdMSV());
        holder.namesv235.setText(computer.getNameSinhVien());
    }

    @Override
    public int getItemCount() {
        if (sinhVien235s!=null)
            return sinhVien235s.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView idmsv235;
        public TextView namesv235;
        public ImageView delete235,edit235;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            idmsv235 = itemView.findViewById(R.id.idname);
            namesv235 = itemView.findViewById(R.id.namesv);
            delete235 = itemView.findViewById(R.id.btnDel);
            edit235 = itemview.findViewById(R.id.btnEdit);


            delete235.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(view.getContext());
                    alertDiaLog.setTitle("Thông báo");
                    alertDiaLog.setMessage("Bạn có muốn xóa "+ idmsv235.getText().toString().trim()+" không?"    );
                    alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddActivity.db.QueryData("delete from SinhVien235 where idMSV235 ='"+ idmsv235.getText().toString().trim()+"'");
                            AddActivity.sinhviens = new ArrayList<SinhVien>();
                            Cursor cursor = AddActivity.db.GetData("Select * from SinhVien235 where idClass235 = '"+AddActivity.idTruyen.toString().trim() +"'");
                            while (cursor.moveToNext()){
                                @SuppressLint("Range") String idC =cursor.getString(cursor.getColumnIndex("idMSV235"));
                                @SuppressLint("Range") String nameC = cursor.getString(cursor.getColumnIndex("nameSinhVien235"));
                                @SuppressLint("Range") String idCategory = cursor.getString(cursor.getColumnIndex("idClass235"));
                                System.out.println(idC+" "+nameC+" "+idCategory);
                                AddActivity.sinhviens.add(new SinhVien(idC,nameC,idCategory));
                            }
                            AddActivity.adapter = new SinhVienAdapter(AddActivity.sinhviens, view.getContext());
                            GridLayoutManager linearLayoutManager = new GridLayoutManager(view.getContext(), 1);
                            AddActivity.recyclerView.setAdapter(AddActivity.adapter);
                            AddActivity.recyclerView.setLayoutManager(linearLayoutManager);
                        }
                    });
                    alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDiaLog.show();
                }
            });

            edit235.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(view.getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.sinhvien_new);
                    dialog.show();
                    TextView tv1 = (TextView) dialog.findViewById(R.id.isIDC);
                    TextView tv2 = (TextView) dialog.findViewById(R.id.isNameC);
                    TextView tv3 = (TextView) dialog.findViewById(R.id.isIDCl);
                    tv3.setVisibility(View.GONE);
                    Button btok = (Button) dialog.findViewById(R.id.btn_okC);
                    Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelC);

                    btok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AddActivity.db.QueryData("update SinhVien235 set idMSV235 = '"+tv1.getText().toString().trim()+ "',nameSinhVien235='"+ tv2.getText().toString().trim()+ "'  where idMSV235 ='"+idmsv235.getText().toString().trim()+"'");
                            AddActivity.sinhviens = new ArrayList<SinhVien>();
                            Cursor cursor = AddActivity.db.GetData("Select * from SinhVien235 where idClass235 = '"+AddActivity.idTruyen.toString().trim() +"'");
                            while (cursor.moveToNext()){
                                @SuppressLint("Range") String idC =cursor.getString(cursor.getColumnIndex("idMSV235"));
                                @SuppressLint("Range") String nameC = cursor.getString(cursor.getColumnIndex("nameSinhVien235"));
                                @SuppressLint("Range") String idCategory = cursor.getString(cursor.getColumnIndex("idClass235"));
                                System.out.println(idC+" "+nameC+" "+idCategory);
                                AddActivity.sinhviens.add(new SinhVien(idC,nameC,idCategory));
                            }
                            AddActivity.adapter = new SinhVienAdapter(AddActivity.sinhviens, view.getContext());
                            GridLayoutManager linearLayoutManager = new GridLayoutManager(view.getContext(), 1);
                            AddActivity.recyclerView.setAdapter(AddActivity.adapter);
                            AddActivity.recyclerView.setLayoutManager(linearLayoutManager);
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
        }
    }
}
