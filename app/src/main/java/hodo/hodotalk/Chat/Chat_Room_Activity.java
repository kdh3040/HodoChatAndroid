package hodo.hodotalk.Chat;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.R;




import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ImageView;


import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;



import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * Created by mjk on 2017. 7. 27..
 */

public class Chat_Room_Activity extends AppCompatActivity {

    Button btnSend;
    EditText etText;
    RecyclerView recyclerView;
    DatabaseReference mRef;
    DatabaseReference mRef2;
    MyData myData = MyData.getInstance();


    FirebaseRecyclerAdapter<Chat_Data, ChatViewHolder> firebaseRecyclerAdapter;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm");
    LinearLayoutManager mLinearLayoutManager;




    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView message;

        TextView sender;
        TextView time;

        public ChatViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView)itemView.findViewById(R.id.imageView);
            this.sender = (TextView)itemView.findViewById(R.id.nickname);
            this.message =(TextView)itemView.findViewById(R.id.message);

            this.time = (TextView)itemView.findViewById(R.id.time);



        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        btnSend =  (Button)findViewById(R.id.btnsend);
        etText = (EditText)findViewById(R.id.ettext);
        recyclerView = (RecyclerView) findViewById(R.id.chatroom_RecylerView);
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //listView.setAdapter();


        final String nickName = myData.getNickName();
        Log.d("hngpic",nickName+"");
        Intent intent = getIntent();

        final String strRoomName =  intent.getStringExtra("RoomName");

        int idx = strRoomName.indexOf("_");
        final String yourNickName = strRoomName.substring(idx+1);

        Log.d("hngpic",yourNickName+"");
        mRef = FirebaseDatabase.getInstance().getReference().child("ChatRoom").child(strRoomName);
       // mRef2 =FirebaseDatabase.getInstance().getReference().child("chat").child(yourNickName+"_"+nickName);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Chat_Data, ChatViewHolder>(
                Chat_Data.class,
                R.layout.chat_room_partview,
                ChatViewHolder.class,
                mRef){
            @Override
            protected Chat_Data parseSnapshot(DataSnapshot snapshot) {

                Chat_Data chat_message = super.parseSnapshot(snapshot);
                if(chat_message != null){
                    chat_message.setId(snapshot.getKey());
                }
                return chat_message;
            }

            @Override
            protected void populateViewHolder(ChatViewHolder viewHolder, Chat_Data chat_message, int position) {
                //Log.d("hngpic","popVH");

                //mProgressBar.setVisibility(ProgressBar.INVISIBLE);

                if(chat_message.getmessage() !=null ){

                    viewHolder.message.setText(chat_message.getmessage());
                    viewHolder.message.setVisibility(TextView.VISIBLE);

                    Date mDate = new Date(chat_message.gettime());
                    String date= mFormat.format(mDate);

                    viewHolder.sender.setText(chat_message.getfrom()+" "+date);

                    ;
                }

            }
        };


        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });


        recyclerView.setAdapter(firebaseRecyclerAdapter);







        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etText.getText().toString();
                long nowTime =System.currentTimeMillis();
                if(etText.getText() == null){
                    return;
                }else{
                    Chat_Data chat_message = new Chat_Data(nickName,yourNickName,message,nowTime);
                    mRef.push().setValue(chat_message);
                   // mRef2.push().setValue(chat_message);
                    etText.setText("");


                }
            }
        });


    }

}



