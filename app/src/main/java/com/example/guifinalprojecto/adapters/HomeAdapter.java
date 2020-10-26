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
import com.example.guifinalprojecto.utils.EndPoints;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    private ArrayList<structRests> listdata;
    private Context context;
    public HomeAdapter(ArrayList<structRests> data, Context context){
        listdata = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_res, null);
            TextView nameRes = view.findViewById(R.id.nameRes);
            TextView calle = view.findViewById(R.id.calleRes);
            ImageView imageView= view.findViewById(R.id.imageView);
            ImageView logoView= view.findViewById(R.id.imgR);
            nameRes.setText(listdata.get(i).getNombre());
            calle.setText(listdata.get(i).getCalle());
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + listdata.get(i).getFoto())
                    .centerCrop()
                    .into(imageView);
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + listdata.get(i).getLogo())
                    .centerCrop()
                    .into(logoView);
        }
        return view;
    }
}
