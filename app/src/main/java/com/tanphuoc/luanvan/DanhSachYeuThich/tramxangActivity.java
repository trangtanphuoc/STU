package com.tanphuoc.luanvan.DanhSachYeuThich;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ListView;

import com.tanphuoc.luanvan.Adapter.AdapterTXYT;
import com.tanphuoc.luanvan.Moudle.TramXang;
import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.sqlite.Database;

import java.util.ArrayList;

public class tramxangActivity extends AppCompatActivity {
    final String DATABASE_NAME = "LUANVANYEUTHICH.sqlite";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<TramXang> list;
    AdapterTXYT adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramxang);
        addControls();
        readData();
    }
    private void addControls() {
        listView = (ListView) findViewById(R.id.lvdsyttramxang);
        list = new ArrayList<>();
        adapter = new AdapterTXYT(this, list);
        listView.setAdapter(adapter);
    }

    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TRAMXANG",null);
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

            list.add(new TramXang(id, ten,diachi,(float) rating,lat,lng,quan));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
