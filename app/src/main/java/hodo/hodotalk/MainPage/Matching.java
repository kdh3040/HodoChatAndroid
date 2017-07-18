package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-12.
 */

public class Matching extends Fragment {

    public Button btn_NextMatching;
    public Button btn_SendHeart;

    private ImageView MatchingImg;
    private TextView MatchingText;

    int cnt = 4;

    static int nSel = 0;
    public static Matching newInstance(){
        Matching fragment = new Matching();
        return fragment;
    }


    String url[] = new String[cnt];

    public Matching() {
        nSel = 0;
        // Required empty public constructor
        SetData();

    }

    public void refreshView()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.detach(this).attach(this).commit();
    }

    public  void  SetData()
    {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_matching, container, false);

        Log.d("!!!!!", "App End----");

        return rootView;
    }

}