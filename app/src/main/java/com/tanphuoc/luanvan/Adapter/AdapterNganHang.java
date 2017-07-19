package com.tanphuoc.luanvan.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tanphuoc.luanvan.Moudle.NganHang;
import com.tanphuoc.luanvan.R;

import java.util.ArrayList;

public class AdapterNganHang extends BaseAdapter{
    Activity context;
    ArrayList<NganHang> list;
    public AdapterNganHang(Activity context, ArrayList<NganHang> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.spn_nganhang,null);

        TextView txtTenNganHang= (TextView) view.findViewById(R.id.txtSpnNH);

        final NganHang nganHang=list.get(position);
        txtTenNganHang.setText(nganHang.getTenNH());

        return view;
    }
}
