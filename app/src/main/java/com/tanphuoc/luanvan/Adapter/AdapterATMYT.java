package com.tanphuoc.luanvan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tanphuoc.luanvan.Moudle.NganHang;
import com.tanphuoc.luanvan.Moudle.TramATM;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.huongdanduongdi.HDDDActivity;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.List;

public class AdapterATMYT extends BaseAdapter{
    Activity context;
    List<TramATM> arrayList;
    List<NganHang> nganHangList;

    public AdapterATMYT(Activity context, List<TramATM> arrayList,List<NganHang> nganHangList) {
        this.context = context;
        this.arrayList = arrayList;
        this.nganHangList = nganHangList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.yt_atm, null);

        TextView tentram= (TextView) row.findViewById(R.id.txtTenTramATM);
        TextView diachi= (TextView) row.findViewById(R.id.txtDiaChiTramATM);
        TextView nganhang= (TextView) row.findViewById(R.id.txtNganHangATM);
        ImageButton imgbtn = (ImageButton) row.findViewById(R.id.imgtimduongATMYT);
        final ImageButton imgbtnYT = (ImageButton) row.findViewById(R.id.imgbtn_YeuThich);

        final TramATM tramATM =arrayList.get(i);
        tentram.setText("Tên trạm:  " + tramATM.getTenTram());
        diachi.setText("Địa chỉ:  " + tramATM.getDiachi());
        nganhang.setText("");

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
                imgbtnYT.setBackgroundResource(R.drawable.rong_sua);
                del(String.valueOf(tramATM.getMaTramATM()));
            }
        });

        return row;
    }

    private void del(String id) {
        SQLiteDatabase database = Database.initDatabase(context, "LUANVANYEUTHICH.sqlite");
        database.delete("TRAMATM", "MATRAMATM = ?", new String[]{id + ""});
        arrayList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM TRAMATM", null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String diachi = cursor.getString(2);
            double rating = cursor.getDouble(3);
            double lat = cursor.getDouble(4);
            double lng = cursor.getDouble(5);
            int quan = cursor.getInt(6);
            int nganhang = cursor.getInt(7);

            arrayList.add(new TramATM(ma, ten,diachi,(float) rating,lat,lng,quan,nganhang));
        }
        notifyDataSetChanged();
    }
}
