package com.tanphuoc.luanvan.Adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tanphuoc.luanvan.Moudle.NganHang;
import com.tanphuoc.luanvan.Moudle.TramATM;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.huongdanduongdi.HDDDActivity;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.List;


public class AdapterATM extends BaseAdapter {
    Activity context;
    List<TramATM> arrayList;
    List<NganHang> nganHangList;

    public AdapterATM(Activity context, List<TramATM> arrayList,List<NganHang> nganHangList) {
        this.context = context;
        this.arrayList = arrayList;
        this.nganHangList = nganHangList;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_atm, null);

        TextView tentram= (TextView) row.findViewById(R.id.txtTenTramATM);
        TextView diachi= (TextView) row.findViewById(R.id.txtDiaChiTramATM);
        TextView nganhang= (TextView) row.findViewById(R.id.txtNganHangATM);
        ImageButton imgbtn = (ImageButton) row.findViewById(R.id.imgtimduongATM);
        final ImageButton imgbtnYT = (ImageButton) row.findViewById(R.id.imgbtn_YeuThich);



        final TramATM tramATM =arrayList.get(position);
        tentram.setText("Tên trạm:  " + tramATM.getTenTram());
        diachi.setText("Địa chỉ:  " + tramATM.getDiachi());


        for(int j=0;j<nganHangList.size();j++){
            if(nganHangList.get(j).getId()==tramATM.getMaNH()){
                nganhang.setText("Ngân Hàng: " + nganHangList.get(j).getTenNH());
                break;
            }
        }

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, HDDDActivity.class);
                intent.putExtra("diachi", tramATM.getDiachi());
                context.startActivity(intent);
            }
        });

        imgbtnYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtnYT.setBackground(null);
                imgbtnYT.setBackgroundResource(R.drawable.day);
                insert(tramATM);
            }
        });

        return row;
    }

    private void insert(TramATM tramATM){
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRAMATM", tramATM.getMaTramATM());
        contentValues.put("TENTRAMATM", tramATM.getTenTram());
        contentValues.put("DIACHI", tramATM.getDiachi());
        contentValues.put("RATING", tramATM.getRating());
        contentValues.put("LAT", tramATM.getLat());
        contentValues.put("LNG", tramATM.getLng());
        contentValues.put("QUAN", tramATM.getMaQuan());
        contentValues.put("NGANHANG", tramATM.getMaNH());

        SQLiteDatabase database = Database.initDatabase(context, "LUANVANYEUTHICH.sqlite");
        database.insert("TRAMATM",null, contentValues);

        Toast.makeText(context, "Đã Thêm" +  tramATM.getTenTram()  + "vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
    }
}
