package com.example.trasua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ThucUongAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThucUong> thucUongList;

    public ThucUongAdapter(Context context, int layout, List<ThucUong> thucUongList) {
        this.context = context;
        this.layout = layout;
        this.thucUongList = thucUongList;
    }
    @Override
    public int getCount() {
        return thucUongList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtten,txtmota,txtgia;
        ImageView img;
        ListView lv;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtten= (TextView) convertView.findViewById(R.id.name);
            holder.txtgia= (TextView) convertView.findViewById(R.id.gia);
            holder.txtmota= (TextView) convertView.findViewById(R.id.mota);
            holder.img= (ImageView) convertView.findViewById(R.id.imageHinh);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ThucUong thucUong = thucUongList.get(position);
        holder.txtten.setText(thucUong.getTen());
        holder.txtmota.setText(thucUong.getMota());
        holder.txtgia.setText(thucUong.getGia());
        holder.img.setImageResource(R.drawable.img2);

        return convertView;
    }

}
