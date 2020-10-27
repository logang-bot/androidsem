package com.example.guifinalprojecto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guifinalprojecto.R;
import com.example.guifinalprojecto.interfaces.RetrofitClient;

import java.util.ArrayList;

public class menusAdapter  extends BaseAdapter {
    private ArrayList<structMenu> listMenus;
    private Context contextM;

    public menusAdapter(ArrayList<structMenu> data, Context contextM) {
        listMenus = data;
        this.contextM = contextM;
    }

    @Override
    public int getCount() {
        return listMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return listMenus.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(contextM).inflate(R.layout.item_menus,null);
        ImageView imgMenu=(ImageView) view.findViewById(R.id.menu_image);
        TextView nombreMenu=(TextView) view.findViewById(R.id.item_name_menu);
        TextView precioMenu = (TextView) view.findViewById(R.id.item_precio_menu);
        nombreMenu.setText(listMenus.get(i).getNombre());

        /*Glide.with(contextM)
                .load(RetrofitClient.BASE_URL + listMenus.get(i).getFoto())
                .centerCrop()
                .into(imgMenu);*/
        return view;
    }
}
