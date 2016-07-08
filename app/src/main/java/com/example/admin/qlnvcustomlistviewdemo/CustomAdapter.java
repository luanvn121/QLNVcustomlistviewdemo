package com.example.admin.qlnvcustomlistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/6/2016.
 */
public class CustomAdapter extends ArrayAdapter<Nhanvien> {
    public CustomAdapter(Context context,ArrayList<Nhanvien> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView=inflater.inflate(R.layout.line_list,parent,false);
        TextView tvTen=(TextView)convertView.findViewById(R.id.tvTen);
        TextView tvLop=(TextView)convertView.findViewById(R.id.tvLop);
        ImageView imgIcon=(ImageView)convertView.findViewById(R.id.imgIcon);

        Nhanvien nv=getItem(position);
        if (nv!=null){
            tvTen.setText(nv.getTen().toString());
            if(nv.getTen().equals("nam")){
                imgIcon.setImageResource(R.drawable.icon1);
            }else imgIcon.setImageResource(R.drawable.icon2);
        }
        return convertView;
    }
}
