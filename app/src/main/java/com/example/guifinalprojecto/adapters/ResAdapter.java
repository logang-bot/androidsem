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

public class ResAdapter extends BaseAdapter {
    private ArrayList<structRests> listRest;
    private Context contextR;

    public ResAdapter(ArrayList<structRests> data, Context contextR) {
        listRest = data;
        this.contextR = contextR;
    }
    @Override
    public int getCount() {
        return listRest.size();
    }

    @Override
    public Object getItem(int i) {
        return listRest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(contextR).inflate(R.layout.item_myrest,null);
        ImageView imgRest=(ImageView) view.findViewById(R.id.imageRest);
        TextView nombreRest=(TextView) view.findViewById(R.id.nombreRest);
        TextView callRest=(TextView) view.findViewById(R.id.callRest);
        nombreRest.setText(listRest.get(i).getNombre());
        callRest.setText(listRest.get(i).getCalle());

       Glide.with(contextR)
                .load(RetrofitClient.BASE_URL + listRest.get(i).getFoto())
                .centerCrop()
                .into(imgRest);
        return view;
    }
}
