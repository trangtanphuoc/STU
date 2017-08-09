package com.tanphuoc.luanvan.DanhSachYeuThich;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.tanphuoc.luanvan.R;

public class TabDanhSach extends TabActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_danh_sach);

        addControl();
        xulyTab();
    }

    private void xulyTab() {
        //Khởi tạo tab bản đồ
        TabHost.TabSpec mapsec = tabHost.newTabSpec("Trạm ATM");
        //Thiết lập tên tab hiển thị và icon
        mapsec.setIndicator("Trạm ATM", getResources().getDrawable(R.drawable.bando));
        //Thiết lập nôi dung cho tab này là activity MapsActivity.class
        Intent mapIntent = new Intent(this, tramatmctivity.class);
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mapsec.setContent(mapIntent);

        //Khởi tạo tab danh sách
        TabHost.TabSpec listec = tabHost.newTabSpec("Trạm Xăng");
        listec.setIndicator("Trạm Xăng", getResources().getDrawable(R.drawable.list));
        Intent litst = new Intent(this, tramxangActivity.class);
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
