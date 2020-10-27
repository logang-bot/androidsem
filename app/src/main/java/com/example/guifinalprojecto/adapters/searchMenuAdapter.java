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

import de.hdodenhof.circleimageview.CircleImageView;

public class searchMenuAdapter extends BaseAdapter {
    private ArrayList<structMenu> listdata;
    private Context context;

    public searchMenuAdapter(ArrayList<structMenu> data, Context context){
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
            v = inflater.inflate(R.layout.item_searchmenu, null);
            /*TextView namemenu = view.findViewById(R.id.searchMnombre);
            TextView nameres = view.findViewById(R.id.searchMnameRes);
            TextView precio = view.findViewById(R.id.searchMprecio);
            ImageView imageView= view.findViewById(R.id.searchMmenuimg);
            CircleImageView logoView= view.findViewById(R.id.searchMlogoR);
            namemenu.setText(listdata.get(i).getNombre());
            nameres.setText(listdata.get(i).getResta());
            precio.setText(listdata.get(i).getPrecio());
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + listdata.get(i).getFoto())
                    .centerCrop()
                    .into(imageView);
            Glide.with(context)
                    .load(RetrofitClient.BASE_URL + listdata.get(i).getImgresta())
                    .centerCrop()
                    .into(logoView);*/
        }
        else{
            v = (View) view;
        }
        TextView namemenu = v.findViewById(R.id.searchMnombre);
        TextView nameres = v.findViewById(R.id.searchMnameRes);
        TextView precio = v.findViewById(R.id.searchMprecio);
        ImageView imageView = v.findViewById(R.id.searchMmenuimg);
        CircleImageView logoView = v.findViewById(R.id.searchMlogoR);
        namemenu.setText(listdata.get(i).getNombre());
        nameres.setText(listdata.get(i).getResta());
        precio.setText("Precio: $" + listdata.get(i).getPrecio());
        Glide.with(context)
                .load(RetrofitClient.BASE_URL + listdata.get(i).getFoto())
                .centerCrop()
                .into(imageView);
        Glide.with(context)
                .load(RetrofitClient.BASE_URL + listdata.get(i).getImgresta())
                .centerCrop()
                .into(logoView);
        return v;
    }
}
