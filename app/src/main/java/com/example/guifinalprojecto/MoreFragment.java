package com.example.guifinalprojecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.ResAdapter;
import com.example.guifinalprojecto.adapters.searchMenuAdapter;
import com.example.guifinalprojecto.adapters.structMenu;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {

    private MoreFragment root = this;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }
    ////////my code starts here
    @Override
    public void onStart(){
        super.onStart();
        //to avoid keyboard moves fragment up
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //--to avoid keyboard moves fragment up
        GridView listM = this.getActivity().findViewById(R.id.mysearchMenu);
        ImageView imgstatm = this.getActivity().findViewById(R.id.imgstatusmore);
        TextView txtstatm = this.getActivity().findViewById(R.id.textstatusmore);
        imgstatm.setAlpha(50);

        FloatingActionButton search = this.getActivity().findViewById(R.id.searchMsearchbtn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText searchText = root.getActivity().findViewById(R.id.searchMsearchfield);
                String searchword = searchText.getText().toString().trim();
                Call<ArrayList<structMenu>> call = RetrofitClient
                        .getInstance()
                        .getApi().getSearchMenu(searchword);

                call.enqueue(new Callback<ArrayList<structMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<structMenu>> call, Response<ArrayList<structMenu>> response) {

                        ArrayList<structMenu> data = response.body();
                        if(data.size()==0){
                            imgstatm.setImageResource(R.drawable.disappoin);
                            txtstatm.setText("No Results Found");
                        }
                        else{
                            imgstatm.setImageResource(0);
                            txtstatm.setText("");
                        }
                        searchMenuAdapter adapter = new searchMenuAdapter(data, getContext());
                        listM.setAdapter(adapter);

                        listM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //animation
                                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                                animation1.setDuration(4000);
                                view.startAnimation(animation1);
                                //animation
                                Intent intent = new Intent(getContext(), listMenus.class); //cambiar vista
                                intent.putExtra("idRest", data.get(i).getId_rest());
                                intent.putExtra("nameRest",data.get(i).getResta());
                                root.startActivity(intent);
                                //Toast.makeText(getContext(),data.get(i).getNit() , Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<ArrayList<structMenu>> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //to avoid keyboard moves fragment up
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //--to avoid keyboard moves fragment up
    }
}