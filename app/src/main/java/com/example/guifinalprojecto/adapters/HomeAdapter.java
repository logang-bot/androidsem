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
        View v;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_res, null);
        }
        else{
            v = (View) view;
        }
            //view = LayoutInflater.from(context).inflate(R.layout.item_res, null);
            TextView nameRes = v.findViewById(R.id.nameRes);
            TextView calle = v.findViewById(R.id.calleRes);
            ImageView imageView= v.findViewById(R.id.imageView);
            ImageView logoView= v.findViewById(R.id.imgR);
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
        return v;
    }
}
