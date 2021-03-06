package com.tanphuoc.luanvan.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tanphuoc.luanvan.Moudle.TramXang;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.huongdanduongdi.HDDDActivity;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.List;

public class AdapterTX extends BaseAdapter {
    Activity context;
    List<TramXang> arrayList;

    public AdapterTX(Activity context, List<TramXang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_tramxang,null );

        final TextView tentram = (TextView) convertView.findViewById(R.id.txtTenTramXang);
        TextView diachi = (TextView) convertView.findViewById(R.id.txtDiaChiTramXang);
        ImageButton imgbtn = (ImageButton) convertView.findViewById(R.id.imgtimduongTX);
        ImageButton imgbtnYT = (ImageButton) convertView.findViewById(R.id.imgbtn_YeuThich);

         final TramXang tramXang =arrayList.get(position);
        tentram.setText("Tên trạm xăng: " + tramXang.getTenTram());
        diachi.setText("Địa chỉ: " +tramXang.getDiachi());

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, HDDDActivity.class);
                intent.putExtra("diachi", tramXang.getDiachi());
                context.startActivity(intent);
            }
        });
        imgbtnYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert(tramXang);
            }
        });
        return convertView;
    }

    private void insert(TramXang tramXang){
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRAMXANG", tramXang.getMaTramXang());
        contentValues.put("TENTRAMXANG", tramXang.getTenTram());
        contentValues.put("DIACHI", tramXang.getDiachi());
        contentValues.put("RATING", tramXang.getRating());
        contentValues.put("LAT", tramXang.getLat());
        contentValues.put("LNG", tramXang.getLng());
        contentValues.put("QUAN", tramXang.getMaQuan());


        SQLiteDatabase database = Database.initDatabase(context, "LUANVANYEUTHICH.sqlite");
        database.insert("TRAMXANG",null, contentValues);

        Toast.makeText(context, "Đã Thêm " + tramXang.getTenTram()+" vào danh sách yêu thích", Toast.LENGTH_SHORT).show();


    }
}
