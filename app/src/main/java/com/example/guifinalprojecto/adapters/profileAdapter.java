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
import com.example.guifinalprojecto.models.user;

import java.util.ArrayList;

public class profileAdapter extends BaseAdapter {
    private user userdata;
    private Context context;

    public profileAdapter (user data, Context context){
        userdata = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){

            /*
            view = LayoutInflater.from(context).inflate(R.layout.item_res, null);
            TextView nameRes = view.findViewById(R.id.nameRes);
            TextView calle = view.findViewById(R.id.calleRes);
            ImageView imageView= view.findViewById(R.id.imageView);
            nameRes.setText(listdata.get(i).getNombre());
            calle.setText(listdata.get(i).getCalle());
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + listdata.get(i).getLogo())
                    .centerCrop()
                    .into(imageView);
            */

            view = LayoutInflater.from(context).inflate(R.layout.header, null);
            TextView username = view.findViewById(R.id.username);
            ImageView imageView = view.findViewById(R.id.profile);
            username.setText(userdata.getName());
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + userdata.getAvatar())
                    .centerCrop()
                    .into(imageView);

        }
        return view;
    }
}
