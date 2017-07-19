package com.tanphuoc.luanvan.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tanphuoc.luanvan.Moudle.TramXang;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.huongdanduongdi.HDDDActivity;

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

        TextView tentram = (TextView) convertView.findViewById(R.id.txtTenTramXang);
        TextView diachi = (TextView) convertView.findViewById(R.id.txtDiaChiTramXang);
        ImageButton imgbtn = (ImageButton) convertView.findViewById(R.id.imgtimduongTX);

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
        return convertView;
    }
}
