package hodo.hodotalk.MainPage;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hodo.hodotalk.Data.UserData_Profile;
import hodo.hodotalk.JoinActivity;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;


/**
 * Created by boram on 2017-07-12.
 */

public class Main extends Fragment {

  //  UserData_Profile users[] = new UserData_Profile[8];

    public  RecyclerView.LayoutManager mLayoutManager;
    public  RecyclerView.Adapter mAdapter;
    public Button btn_fragment;

    public MainPage_Adapter mMainAdapter;
    private  RecyclerView recyclerView;
    public Main() {
        // Required empty public constructor
    }

    public static Main newInstance() {
        Main fragment = new Main();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_main, container, false);

        mMainAdapter = new MainPage_Adapter();

        btn_fragment = (Button)rootView.findViewById(R.id.button2);
        btn_fragment.setText("카드 더 보기");

        recyclerView = (RecyclerView)rootView.findViewById(R.id.mainpage_cardview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new MainPage_Adapter());

        btn_fragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mMainAdapter.SetData_Firebase();
                recyclerView.setAdapter(new MainPage_Adapter());
            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog().build());

        Log.d("!!!!!", "App End----");

        return rootView;
    }
}

