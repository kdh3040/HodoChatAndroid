package hodo.hodotalk.MyPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import hodo.hodotalk.ChatPage.ChatPage_main;
import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.R;
import hodo.hodotalk.ViewProfile;

/**
 * Created by boram on 2017-07-12.
 */

public class CardList extends Fragment {

    private LinearLayout groupLayout;
    private LinearLayout dynamicLayout;
    private FavoriteData_Group m_Favorite = FavoriteData_Group.getInstance();
    private MyData m_Mydata = MyData.getInstance();

    public static CardList newInstance(){
        CardList fragment = new CardList();
        return fragment;
    }


    public CardList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mypage_cardlist, container, false);

        int getNumber = m_Mydata.getFavorite();


        dynamicLayout = (LinearLayout)rootView.findViewById(R.id.dynamicLayout);



        for(int i = 0; i<getNumber; i++) {

            LinearLayout item = new LinearLayout(this.getContext());
            item.setOrientation(LinearLayout.HORIZONTAL);

            Button newTextView = new Button(getContext());
            newTextView.setText(m_Favorite.m_stFavorite[i].getNickName());
            item.addView(newTextView);

            newTextView.setId(i + 1);
            final int finalI = i;
            newTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("log", "position :" + finalI);

                    Intent intent = new Intent(getActivity(), ChatPage_main.class);
                    intent.putExtra("TargetNick", m_Favorite.m_stFavorite[finalI].getNickName());
                    startActivity(intent);
                }
            });


/*
            TextView newTextView1 = new TextView(getContext());
            newTextView1.setText("동적 텍스트" + (i + 2));
            item.addView(newTextView1);

            TextView newTextView2 = new TextView(getContext());
            newTextView2.setText("동적 텍스트" + (i + 3));
            item.addView(newTextView2);*/

            LinearLayout.LayoutParams item_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //
            //
            // item_param.addRule(RelativeLayout.ALIGN_LEFT);
            item_param.setMargins(5,5,5,5);

            item.setLayoutParams(item_param);
            dynamicLayout.addView(item);
        }


        return rootView;
    }

}