package hodo.hodotalk.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hodo.hodotalk.ChatPage.ChatPage_main;
import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.RecvHeart;
import hodo.hodotalk.R;

public class MyPage_CardList extends AppCompatActivity {


    private LinearLayout SendHeartLayout;
    private LinearLayout RecvHeartLayout;
    private MyData m_Mydata = MyData.getInstance();
    private String strMyID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page__card_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SendHeartLayout = (LinearLayout)findViewById(R.id.SendHeart);
        RecvHeartLayout = (LinearLayout)findViewById(R.id.RecvHeart);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = null;

        int idx = m_Mydata.getEmail().indexOf("@");
        strMyID = m_Mydata.getEmail().substring(0, idx);

        if(m_Mydata.getGender() == 0)
            databaseRef = database.getReference("CardList/WOMAN/" + strMyID);
        else
            databaseRef = database.getReference("CardList/MAN/" + strMyID);

        RecvHeartData(databaseRef);
        SendHeartData(databaseRef);
        RecvInterData(databaseRef);
        SendInterData(databaseRef);

    }

    private void RecvHeartData(DatabaseReference databaseRef) {
        databaseRef.child("RecvHeart").addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int saa =0;
                RecvHeart cRecvCard = dataSnapshot.getValue(RecvHeart.class);

                final String strNick = cRecvCard.NickName;
                final String strImg = cRecvCard.Img;

                LinearLayout item1 = new LinearLayout(getApplicationContext());
                Button   newTextView1 = new Button(getApplicationContext());
                item1.setOrientation(LinearLayout.VERTICAL);
                newTextView1.setText(strNick);
                newTextView1.setId(i + 1);
                Log.i("TAG: NickName", strNick + "   "  + strImg);

                newTextView1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), ChatPage_main.class);
                        intent.putExtra("TargetNick", strNick);
                        startActivity(intent);
                    }
                });

                LinearLayout.LayoutParams item_param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                item_param1.setMargins(5,5,5,5);

                item1.setLayoutParams(item_param1);
                item1.addView(newTextView1);
                RecvHeartLayout.addView(item1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int saa =0;
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                int saa =0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                int saa =0;
            }
        });

    }
    private void SendHeartData(DatabaseReference databaseRef) {

        databaseRef.child("SendHeart").addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int saa =0;

                RecvHeart cRecvCard = dataSnapshot.getValue(RecvHeart.class);

                final String strNick = cRecvCard.NickName;
                final String strImg = cRecvCard.Img;

                LinearLayout item = new LinearLayout(getApplicationContext());
                Button   newTextView = new Button(getApplicationContext());
                item.setOrientation(LinearLayout.VERTICAL);
                newTextView.setText(strNick);
                newTextView.setId(i + 1);
                Log.i("TAG: NickName", strNick + "   "  + strImg);

                newTextView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), ChatPage_main.class);
                        intent.putExtra("TargetNick", strNick);
                        startActivity(intent);
                    }
                });

                LinearLayout.LayoutParams item_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                item_param.setMargins(5,5,5,5);

                item.setLayoutParams(item_param);
                item.addView(newTextView);
                SendHeartLayout.addView(item);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int saa =0;
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                int saa =0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                int saa =0;
            }
        });
    }
    private void RecvInterData(DatabaseReference databaseRef) {
    }
    private void SendInterData(DatabaseReference databaseRef) {
    }

}

