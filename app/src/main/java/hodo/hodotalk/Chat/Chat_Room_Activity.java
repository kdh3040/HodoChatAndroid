package hodo.hodotalk.Chat;
import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;

import java.util.Date;


/**
 * Created by mjk on 2017. 7. 27..
 */

public class Chat_Room_Activity extends AppCompatActivity {

    Button btnSend,btnImg;
    EditText etText;
    RecyclerView recyclerView;
    public Picasso picasso;
    public LruCache picasso_LRuCache;
    DatabaseReference mRef;
    DatabaseReference mRef2;
    MyData myData = MyData.getInstance();
    String nickName,yourNickName,strRoomName;

    FirebaseRecyclerAdapter<Chat_Data, ChatViewHolder> firebaseRecyclerAdapter;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm");
    LinearLayoutManager mLinearLayoutManager;
    FirebaseUser m_FirebaseUser;
    FirebaseAuth m_FirebaseAuth;
    int REQUEST_IMAGE = 1001;


    public static class ChatViewHolder extends RecyclerView.ViewHolder{

        ImageView image_profile,image_sent;
        TextView message;

        TextView sender;
        TextView time;

        public ChatViewHolder(View itemView) {
            super(itemView);
            this.image_profile = (ImageView)itemView.findViewById(R.id.imageView);
            this.image_sent = itemView.findViewById(R.id.iv_sent);
            this.sender = (TextView)itemView.findViewById(R.id.nickname);
            this.message =(TextView)itemView.findViewById(R.id.message);

            this.time = (TextView)itemView.findViewById(R.id.time);

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        btnSend =  (Button)findViewById(R.id.btn_send);
        etText = (EditText)findViewById(R.id.et_text);
        btnImg =(Button)findViewById(R.id.btn_image);
        recyclerView = (RecyclerView) findViewById(R.id.chatroom_RecylerView);
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //listView.setAdapter();


        nickName = myData.getNickName();
        m_FirebaseAuth = FirebaseAuth.getInstance();
        m_FirebaseUser = m_FirebaseAuth.getCurrentUser();

        Intent intent = getIntent();

        strRoomName =  intent.getStringExtra("RoomName");

        int idx = strRoomName.indexOf("_");
        yourNickName = strRoomName.substring(idx+1);

        Log.d("hngpic",yourNickName+"");
        mRef = FirebaseDatabase.getInstance().getReference().child("ChatRoom").child(strRoomName);
       //mRef2 =FirebaseDatabase.getInstance().getReference().child("chat").child(yourNickName+"_"+nickName);

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

                if(chat_message.getimage_URL() != null){
                    Picasso.with(getApplicationContext())
                            .load(chat_message.getimage_URL().toString())
                            .into(viewHolder.image_sent);


                }else{
                    viewHolder.message.setText(chat_message.getmessage());
                }
                Date mDate = new Date(chat_message.gettime());
                String date= mFormat.format(mDate);
                viewHolder.sender.setText(chat_message.getfrom()+" "+date+"index:"+position);


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

        //채팅창 메시지 보내기 버튼
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etText.getText().toString();
                long nowTime =System.currentTimeMillis();
                if(etText.getText() == null){
                    return;
                }else{
                    Chat_Data chat_Data = new Chat_Data(nickName,yourNickName,message,nowTime,null);
                    mRef.push().setValue(chat_Data);
                   //mRef2.push().setValue(chat_Data);
                    etText.setText("");

                }
            }
        });


        //채팅창 이미지 추가 버튼
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                if(data != null){


                    final Uri uri = data.getData();





                    StorageReference storageReference = FirebaseStorage.getInstance()
                            .getReference("images")
                            .child(strRoomName)
                            .child(uri.getLastPathSegment());


                    putImageInStorage(storageReference, uri);


                }
            }
        }
    }

    private void putImageInStorage(StorageReference storageReference, Uri uri){

        storageReference.putFile(uri).addOnCompleteListener(Chat_Room_Activity.this,
                new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            long nowTime =System.currentTimeMillis();

                            Chat_Data chat_data = new Chat_Data(nickName,yourNickName,null,nowTime,task.getResult().getMetadata().getDownloadUrl().toString());

                            mRef.push().setValue(chat_data);


                        }else{

                        }
                    }
                });

    }
}



