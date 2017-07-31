package hodo.hodotalk.Service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hodo.hodotalk.Chat.Chat_UserList_Acitiviy;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.R;

public class PurchaseHeart extends AppCompatActivity {

    private Button bbbb;
    private MyData m_MyData = MyData.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_heart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bbbb = (Button)findViewById(R.id.button5);

        bbbb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                m_MyData.setHeart(m_MyData.getHeart()+100);

                Toast.makeText(PurchaseHeart.this, "현재 보유 하트 : "+ m_MyData.getHeart() , Toast.LENGTH_SHORT).show();


            }
        });

    }

}
