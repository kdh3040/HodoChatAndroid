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

public class Board extends Fragment {

    public static Board newInstance(){
        Board fragment = new Board();
        return fragment;
    }


    public Board() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mypage_cardlist, container, false);
    }

}