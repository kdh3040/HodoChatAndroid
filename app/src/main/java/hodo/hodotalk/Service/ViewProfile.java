package hodo.hodotalk.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import hodo.hodotalk.R;

public class ViewProfile extends AppCompatActivity {

    private Button btnSendHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String sss = intent.getParcelableExtra("Name");

        btnSendHeart = (Button)findViewById(R.id.Btn_Profile_SendHeart);
        btnSendHeart.setText("♡ 좋아요 보내기");

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.Btn_Profile_SendHeart:
                        SendHeart();
                        break;

                }
                //  UpdateStatus();
            }
        };

        btnSendHeart.setOnClickListener(listener);
    }

    public  void SendHeart()
    {

    }
}
