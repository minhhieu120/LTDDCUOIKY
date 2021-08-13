package com.example.trasua;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextRepassword,editTextPassword;
    Button btnDangky;

    String txtName,txtPass,txtPhone,txtMail;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextRepassword = findViewById(R.id.editTextRePassword);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnDangky = findViewById(R.id.btnDangky);
        DB =new DBHelper(this,"trasua.db",null,1);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =editTextEmail.getText().toString();
                String pass=editTextPassword.getText().toString();
                String repass=editTextRepassword.getText().toString();
                String name=editTextName.getText().toString();
                if(email.equals("") || pass.equals("") || repass.equals("") || name.equals("") )
                    Toast.makeText(RegisterActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(email);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(email,pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Password không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }

            }}
        );
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

}