package com.tanphuoc.luanvan.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tanphuoc.luanvan.Moudle.Quan;
import com.tanphuoc.luanvan.R;

import java.util.ArrayList;

public class AdapterQuan extends BaseAdapter{
    Activity context;
    ArrayList<Quan> list;

    public AdapterQuan(Activity context, ArrayList<Quan> list) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.spn_quan,null);
//        TextView txtTenQuan= (TextView) view.findViewById(R.id.txtSpnQuan);
        CheckBox cbTenQuan = (CheckBox) view.findViewById(R.id.cbQuan);
        final Quan quan=list.get(position);
//        txtTenQuan.setText(quan.getTenQuan());
        cbTenQuan.setText(quan.getTenQuan());
        return view;
    }
}
