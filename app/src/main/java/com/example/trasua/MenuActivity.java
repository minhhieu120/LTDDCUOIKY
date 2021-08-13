package com.example.trasua;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    DBHelper DB;
    ListView lvThucUong;
    ArrayList<ThucUong> arrayThucUong;
    static ThucUongAdapter thucUongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lvThucUong = (ListView) findViewById(R.id.listview);
        lvThucUong.setClickable(true);
        arrayThucUong = new ArrayList<>();
        thucUongAdapter = new ThucUongAdapter(this,R.layout.layoutthucuong, arrayThucUong);
        lvThucUong.setAdapter(thucUongAdapter);
        DB =new DBHelper(this,"trasua.db",null,1);
        DB.QueryData("CREATE TABLE IF NOT EXISTS ThucUong(tenmon TEXT PRIMARY KEY, mota TEXT, giaban TEXT)");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Trà sữa trân châu', 'thêm trân châu trắng', 'Giá bán: 20.000VND')");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Trà đào cam sả', 'full topping', 'Giá bán: 20.000VND')");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Sữa tươi trân châu đường đen', 'full topping', 'Giá bán: 30.000VND')");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Sữa chua đá', 'full topping', 'Giá bán: 20.000VND')");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Trà sen vàng', 'không kem chese', 'Giá bán: 30.000VND')");
//        DB.QueryData("INSERT INTO ThucUong VALUES('Trà hoa quả nhiệt đới', 'thêm trái cây', 'Giá bán: 29.000VND')");
        GetDataThucUong();
        lvThucUong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowItem(position);
            }
        });
        lvThucUong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(MenuActivity.this);
                adb.setTitle("Xóa?");
                adb.setMessage("Bạn có chắn chắn muốn xóa không?");

                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThucUong c=arrayThucUong.get(position);
                        String ten = c.getTen();
                        DB.QueryData("DELETE FROM ThucUong WHERE tenmon = '"+ten+"'");
                        Toast.makeText(MenuActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        GetDataThucUong();
                    }
                });
                adb.show();
                return false;
            }}); }
    private void GetDataThucUong(){
        Cursor dataMon = DB.GetData("SELECT * FROM ThucUong");
        arrayThucUong.clear();
        while (dataMon.moveToNext()){
            String ten = dataMon.getString(0);
            String mota = dataMon.getString(1);
            String gia = dataMon.getString(2);
            arrayThucUong.add(new ThucUong(ten,mota,gia,R.drawable.img2));
        }
        thucUongAdapter.notifyDataSetChanged();
    }
    public void them (View View){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.addthucuong);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                dialog.cancel();
            }
        });
        Button btnthem = (Button) dialog.findViewById(R.id.btnthem);
        EditText addten = (EditText) dialog.findViewById(R.id.editTextADD);
        EditText addmota = (EditText) dialog.findViewById(R.id.editTextMota);
        EditText addgia = (EditText) dialog.findViewById(R.id.editTextGia);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
               String tenmon = addten.getText().toString();
                String  chitiet = addmota.getText().toString();
                String gia = addgia.getText().toString();
                if (tenmon.equals("") || chitiet.equals("") || gia.equals("")) {
                    Toast.makeText(MenuActivity.this, "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                }else {
                    DB.QueryData("INSERT INTO ThucUong VALUES('"+ tenmon+"', '"+chitiet+"', 'Giá bán: "+gia+"VND')");
                    Toast.makeText(MenuActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    GetDataThucUong();
                } }});
        dialog.show();}
    private void ShowItem(int position){
        ThucUong c=arrayThucUong.get(position);
        Intent i = new Intent( this, ChiTietActivity.class);
        i.putExtra("ten",c.getTen());
        i.putExtra("gia",c.getGia());
        i.putExtra("mota",c.getMota());
        i.putExtra("hinh",c.getImg());
        startActivity(i);
    }
    public void DialogXoa(){
        androidx.appcompat.app.AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có chắc chắn muốn xóa không?");
        dialogxoa.show();
    }}