package hodo.hodotalk.MainPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-12.
 */

public class Main extends Fragment {

    public static Main newInstance(){
        Main fragment = new Main();
        return fragment;
    }


    public Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mainpage_main, container, false);
    }

}