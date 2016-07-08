package com.example.admin.qlnvcustomlistviewdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvDS;
    ArrayList<Nhanvien> mang = new ArrayList<Nhanvien>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvDS = (ListView) findViewById(R.id.listViewDS);
        //ArrayAdapter là trung gian để kết nối listview và data
        adapter = new CustomAdapter(MainActivity.this,mang);
        lvDS.setAdapter(adapter);
        //xem màng hình 2
        lvDS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Mainchitiet.class);
                i.putExtra("name",mang.get(position).getTen().toString());
                i.putExtra("class",mang.get(position).getLop().toString());
                startActivity(i);
            }
        });
        registerForContextMenu(lvDS);
    }

    public void iniArr() {
        mang.add(new Nhanvien("Nam", "CNTT"));
        mang.add(new Nhanvien("Ngoc", "CNTT"));
        mang.add(new Nhanvien("Hoàng", "CNTT"));
        mang.add(new Nhanvien("Sida", "CNTT"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
                if(id==R.id.action_new){
                    dialogNew();
                    return true;
                }
                if(id==R.id.action_exit){
                    exitDialogExit();
                    return true;
                }
        return super.onOptionsItemSelected(item);
    }

    public void exitDialogExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có chắc thoát không?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void dialogNew(){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        final EditText edtTen=(EditText)dialog.findViewById(R.id.edtTen);
        final EditText edtLop=(EditText)dialog.findViewById(R.id.edtLop);
        Button btnok = (Button)dialog.findViewById(R.id.btnok);
        Button btncancel = (Button)dialog.findViewById(R.id.btncancel);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTen.getText().toString().equals("")==true||edtLop.getText().toString().equals("")==true)
                 {
                    Toast.makeText(MainActivity.this,"Bạn cần điền 1 số thông tin  ",Toast.LENGTH_SHORT).show();
                }else{
                Nhanvien o=new Nhanvien(edtTen.getText().toString(),edtLop.getText().toString());
                mang.add(o);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                    Toast.makeText(MainActivity.this,"Bạn đã thêm thành công!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,""+mang.size(),Toast.LENGTH_SHORT).show();
                }

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
//tạo contect menu edit, delete
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuinflater=getMenuInflater();
        menuinflater.inflate(R.menu.menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_edit:
                dialogEdit(info.position);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.action_delete:
                mang.remove(info.position);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);


        }
    }
    public void dialogEdit(final int posesion){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        final EditText edtTen=(EditText)dialog.findViewById(R.id.edtTen);
        final EditText edtLop=(EditText)dialog.findViewById(R.id.edtLop);
        TextView tvTitel=(TextView)dialog.findViewById(R.id.tvTitel);
        Button btnok = (Button)dialog.findViewById(R.id.btnok);
        Button btncancel = (Button)dialog.findViewById(R.id.btncancel);

        btnok.setText("Update");
        tvTitel.setText("Thay đổi thông tin");
        edtTen.setText(mang.get(posesion).getTen().toString());
        edtLop.setText(mang.get(posesion).getLop().toString());

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTen.getText().toString().equals("")==true||edtLop.getText().toString().equals("")==true)
                {
                    Toast.makeText(MainActivity.this,"Bạn cần điền đầy đủ thông tin  ",Toast.LENGTH_SHORT).show();
                }else{
                    Nhanvien o=new Nhanvien(edtTen.getText().toString(),edtLop.getText().toString());
                    mang.set(posesion,o);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this,"Bạn đã sửa thành công!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
