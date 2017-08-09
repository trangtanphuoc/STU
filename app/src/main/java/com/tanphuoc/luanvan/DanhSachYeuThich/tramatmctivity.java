package com.tanphuoc.luanvan.DanhSachYeuThich;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;

import com.tanphuoc.luanvan.Adapter.AdapterATMYT;
import com.tanphuoc.luanvan.Moudle.NganHang;
import com.tanphuoc.luanvan.Moudle.TramATM;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.ArrayList;

public class tramatmctivity extends AppCompatActivity {
    final String DATABASE_NAME = "LUANVANYEUTHICH.sqlite";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<TramATM> list;
    ArrayList<NganHang> listNH;
    AdapterATMYT adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        addControls();
        readNH();
        readData();
    }
    private void addControls() {
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        listNH = new ArrayList<>();
        adapter = new AdapterATMYT(this, list, listNH);
        listView.setAdapter(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TRAMATM",null);
        cursor.moveToFirst();

        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String diachi = cursor.getString(2);
            double rating = cursor.getDouble(3);
            double lat = cursor.getDouble(4);
            double lng = cursor.getDouble(5);
            int quan = cursor.getInt(6);
            int nganhang = cursor.getInt(7);

            list.add(new TramATM(id, ten,diachi,(float) rating,lat,lng,quan,nganhang));
        }
        adapter.notifyDataSetChanged();
    }
    private void readNH(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NGANHANG",null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);

            listNH.add(new NganHang(ma,ten));
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
