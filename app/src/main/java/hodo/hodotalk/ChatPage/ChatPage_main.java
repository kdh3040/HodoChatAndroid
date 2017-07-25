package hodo.hodotalk.ChatPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.R;

public class ChatPage_main extends AppCompatActivity {

    //private FavoriteData_Group m_favoriteData_group = FavoriteData_Group.getInstance();
    //private MyData m_MyData = MyData.getInstance();

    ListView m_listView;
    Button button ;
    EditText m_EditText;
    ChatPage_Adapter m_Adapter;

    FirebaseDatabase m_FirebaseDatabase;
    DatabaseReference m_DatabaseReference;
    DatabaseReference m_DatabaseReference2;

    ChildEventListener m_ChildEventListener;
    private String TAG ="hodo";
    MyData m_Mydata = MyData.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatpage_main);


        Intent intent = getIntent();
        final String sender = m_Mydata.getNickName();
        final String getter = intent.getStringExtra("TargetNick");


        Log.i("hodo","hh");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_listView=(ListView)findViewById(R.id.list_message);
        m_Adapter = new ChatPage_Adapter(this, 0);
        m_listView.setAdapter(m_Adapter);



        button=(Button)findViewById(R.id.btn_send);
        m_EditText=(EditText)findViewById(R.id.edit_message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = m_EditText.getText().toString();
                if(!TextUtils.isEmpty(message)){
                    m_EditText.setText("");
                    ChatPage_ChatData chatData = new ChatPage_ChatData();

                    chatData.senderName = sender;
                    chatData.getterName = getter;
                    chatData.message = message;
                    //chatData.time = System.currentTimeMillis();
                    m_DatabaseReference.push().setValue(chatData);
                    m_DatabaseReference2.push().setValue(chatData);



                }

            }
        });

        m_FirebaseDatabase = FirebaseDatabase.getInstance();

        m_DatabaseReference = m_FirebaseDatabase.getReference(sender+"_"+getter);
        m_DatabaseReference2 = m_FirebaseDatabase.getReference(getter+"_"+sender);



        m_ChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ChatPage_ChatData chatPage_chatData = dataSnapshot.getValue(ChatPage_ChatData.class);

                chatPage_chatData.firebaseKey = dataSnapshot.getKey();
                //Log.i("hodo",chatPage_chatData.firebaseKey);
                m_Adapter.add(chatPage_chatData);

                m_listView.smoothScrollToPosition(m_Adapter.getCount());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String firebaseKey = dataSnapshot.getKey();

                int count = m_Adapter.getCount();
                for (int i = 0; i < count; i++) {
                    if (m_Adapter.getItem(i).firebaseKey.equals(firebaseKey)) {
                        m_Adapter.remove(m_Adapter.getItem(i));
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        m_DatabaseReference.addChildEventListener(m_ChildEventListener);
    }

}
