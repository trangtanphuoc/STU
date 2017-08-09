package com.tanphuoc.luanvan.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tanphuoc.luanvan.Moudle.TramXang;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.huongdanduongdi.HDDDActivity;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.List;



public class AdapterTXYT extends BaseAdapter {
    Activity context;
    List<TramXang> arrayList;

    public AdapterTXYT(Activity context, List<TramXang> arrayList) {
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
        convertView = inflater.inflate(R.layout.yt_tx,null );

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
                del(String.valueOf(tramXang.getMaTramXang()));
            }
        });
        return convertView;
    }

    private void del(String id) {
        SQLiteDatabase database = Database.initDatabase(context, "LUANVANYEUTHICH.sqlite");
        database.delete("TRAMXANG", "MATRAMXANG = ?", new String[]{id + ""});
        arrayList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM TRAMXANG", null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String diachi = cursor.getString(2);
            double rating = cursor.getDouble(3);
            double lat = cursor.getDouble(4);
            double lng = cursor.getDouble(5);
            int quan = cursor.getInt(6);

            arrayList.add(new TramXang(ma, ten,diachi,(float) rating,lat,lng,quan));
        }
        notifyDataSetChanged();
    }
}
