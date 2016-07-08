package com.example.admin.qlnvcustomlistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Mainchitiet extends AppCompatActivity {
    TextView tvName,tvClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainchitiet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tvName=(TextView)findViewById(R.id.tvName);
        tvClass=(TextView)findViewById(R.id.tvClass);
        Intent z=getIntent();
        String ten=z.getStringExtra("name");
        String lop=z.getStringExtra("class");
        tvName.setText(ten);
        tvClass.setText(lop);
    }

}
