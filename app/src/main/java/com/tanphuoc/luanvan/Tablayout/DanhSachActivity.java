package com.tanphuoc.luanvan.Tablayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tanphuoc.luanvan.Adapter.AdapterATM;
import com.tanphuoc.luanvan.Adapter.AdapterTX;
import com.tanphuoc.luanvan.R;

public class DanhSachActivity extends AppCompatActivity {
    TextView txtTongds;
    ListView lvDanhSach;
    AdapterATM adapterATM;
    AdapterTX adapterTX;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);

        addControl();
        Data();
    }

    private void addControl() {
        lvDanhSach = (ListView) findViewById(R.id.lvdanhsach);
        txtTongds = (TextView) findViewById(R.id.txtTongTram);
    }

    private void Data() {
        try {
            if (MapsActivity.vitri == 0) {
                adapterATM = new AdapterATM(DanhSachActivity.this, MapsActivity.listATMDanhSach, MapsActivity.arrayListNH);
                txtTongds.setText("Có " + MapsActivity.listATMDanhSach.size() + " Trạm ATM");
                lvDanhSach.setAdapter(adapterATM);
            } else if (MapsActivity.vitri == 1) {
                adapterTX = new AdapterTX(DanhSachActivity.this, MapsActivity.listXangDanhSach);
                txtTongds.setText("Có " + MapsActivity.listXangDanhSach.size() + " Trạm Xăng");
                lvDanhSach.setAdapter(adapterTX);
            }
        } catch (Exception e) {
            Toast.makeText(DanhSachActivity.this, "bạn chưa chọn tìm kiếm trạm nên không có danh sách trạm ", Toast.LENGTH_SHORT).show();
            txtTongds.setText("Bạn chưa chọn tìm kiếm trạm");
            e.printStackTrace();
        }
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Exit");
//        builder.setMessage("Bạn có muốn đăng xuất không?");
//        builder.setCancelable(false);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        return super.onKeyDown(keyCode, event);
//    }
}
