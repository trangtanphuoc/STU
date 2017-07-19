package com.tanphuoc.luanvan.Main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.tanphuoc.luanvan.R;
import com.tanphuoc.luanvan.Tablayout.DanhSachActivity;
import com.tanphuoc.luanvan.Tablayout.MapsActivity;

public class MainActivity extends TabActivity {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControl();
        xulyTab();
    }

    private void xulyTab() {
        //Khởi tạo tab bản đồ
        TabHost.TabSpec mapsec = tabHost.newTabSpec("BanDo");
        //Thiết lập tên tab hiển thị và icon
        mapsec.setIndicator("Bản Đồ", getResources().getDrawable(R.drawable.bando));
        //Thiết lập nôi dung cho tab này là activity MapsActivity.class
        Intent mapIntent = new Intent(this, MapsActivity.class);
        mapsec.setContent(mapIntent);

        //Khởi tạo tab danh sách
        TabHost.TabSpec listec = tabHost.newTabSpec("DanhSach");
        listec.setIndicator("Danh sách các trạm", getResources().getDrawable(R.drawable.list));
        Intent litst = new Intent(this, DanhSachActivity.class);
        litst.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        listec.setContent(litst);


        //Thêm các TabSpec trên vào TabHost
        tabHost.addTab(mapsec); //Thêm tab bản đồ
        tabHost.addTab(listec); //Thêm tab danh sách

    }

    private void addControl() {
        tabHost = getTabHost();
    }

}
