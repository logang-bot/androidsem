package com.example.guifinalprojecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guifinalprojecto.adapters.HomeAdapter;
import com.example.guifinalprojecto.adapters.structRests;
import com.example.guifinalprojecto.interfaces.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private HomeFragment root = this;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

////////my code starts here
    //ArrayList<structRests> data = new ArrayList<>();
    @Override
    public void onStart() {
        super.onStart();
        ListView list = this.getActivity().findViewById(R.id.rests);

        //final ArrayList<structRests> datos;

        Call<ArrayList<structRests>> call = RetrofitClient
                .getInstance()
                .getApi().getRests();

        call.enqueue(new Callback<ArrayList<structRests>>() {
            @Override
            public void onResponse(Call<ArrayList<structRests>> call, Response<ArrayList<structRests>> response) {
                ArrayList<structRests> data = response.body();
                HomeAdapter adapter = new HomeAdapter(data, getContext());
                //ArrayAdapter<String> adapter= new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, datos);
                list.setAdapter(adapter);

                //making functional each item
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //animation
                        Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                        animation1.setDuration(4000);
                        view.startAnimation(animation1);
                        //animation
                        Intent intent = new Intent(getContext(), listMenus.class);

                        intent.putExtra("idRest", data.get(i).get_id());
                        intent.putExtra("nameRest" , data.get(i).getNombre());
                        getContext().startActivity(intent);
                         /*Intent intent = new Intent(root, MainDashboard.class);
                intent.putExtra("backupAgentName", root.getApplicationInfo().backupAgentName);
                intent.putExtra("data", "soy la informacion de la actividad MainActivity");
                intent.putExtra("number", 26);
                root.startActivity(intent);*/
                        Toast.makeText(getContext(),data.get(i).getNit() , Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<structRests>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}