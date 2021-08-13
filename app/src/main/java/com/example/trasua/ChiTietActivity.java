package com.example.trasua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChiTietActivity extends AppCompatActivity {
    EditText tenmon, giamon, chitiet;
    ImageView hinh;
    Button edit,quaylai;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        tenmon=(EditText) findViewById(R.id.tenmon);
        giamon=(EditText) findViewById(R.id.giamon);
        chitiet=(EditText) findViewById(R.id.mota);
        hinh=(ImageView)findViewById(R.id.Hinh);
        quaylai=(Button) findViewById(R.id.back);
        String ten=getIntent().getStringExtra("ten");
        String gia=getIntent().getStringExtra("gia");
        String mota=getIntent().getStringExtra("mota");
        tenmon.setText(ten);
        giamon.setText(gia);
        chitiet.setText(mota);
        hinh.setImageResource(R.drawable.img1);
    }
}