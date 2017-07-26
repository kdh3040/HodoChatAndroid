package hodo.hodotalk.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import hodo.hodotalk.ChatPage.ChatPage_main;
import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.R;

public class MyPage_CardList extends AppCompatActivity {


    private LinearLayout groupLayout;
    private LinearLayout dynamicLayout;
    private FavoriteData_Group m_Favorite = FavoriteData_Group.getInstance();
    private MyData m_Mydata = MyData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page__card_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        int getNumber = m_Mydata.getFavorite();

        dynamicLayout = (LinearLayout)findViewById(R.id.dynamicLayout);

        for(int i = 0; i<getNumber; i++) {

            LinearLayout item = new LinearLayout(this.getApplicationContext());
            item.setOrientation(LinearLayout.HORIZONTAL);

            Button newTextView = new Button(getApplicationContext());
            newTextView.setText(m_Favorite.m_stFavorite[i].getNickName());
            item.addView(newTextView);

            newTextView.setId(i + 1);
            final int finalI = i;
            newTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("log", "position :" + finalI);

                    Intent intent = new Intent(getApplicationContext(), ChatPage_main.class);
                    intent.putExtra("TargetNick", m_Favorite.m_stFavorite[finalI].getNickName());
                    startActivity(intent);
                }
            });

            LinearLayout.LayoutParams item_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //
            //
            // item_param.addRule(RelativeLayout.ALIGN_LEFT);
            item_param.setMargins(5,5,5,5);

            item.setLayoutParams(item_param);
            dynamicLayout.addView(item);
        }


    }

}
