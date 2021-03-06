package hodo.hodotalk.Chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import hodo.hodotalk.Data.ChatRoomData;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.R;

/**
 * Created by mjk on 2017. 7. 28..
 */

public class Chat_UserList_Acitiviy extends AppCompatActivity {

    ListView listView;


    private UserData_Group userData_group = UserData_Group.getInstance();
    private MyData m_MyData = MyData.getInstance();

    //ArrayList<UserData> arrayList = new ArrayList<>();
    //ArrayList<UserData> arrayList = new ArrayList<>();

    MyListAdapter myListAdapter ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_current_user);

      //  arrayList = userData_group.arrUsers;

        listView = (ListView)findViewById(R.id.listview);

        myListAdapter = new MyListAdapter(getApplicationContext());
        listView.setAdapter(myListAdapter);

    }
    class MyListAdapter extends BaseAdapter {

        private Context m_Context;

        public MyListAdapter(Context context) {
            m_Context = context;

        }

        @Override
        public int getCount() {
            Log.d("hngpic",""+  m_MyData.arrChatRoomList.size());
            return m_MyData.arrChatRoomList.size();
        }

        @Override
        public Object getItem(int i) {
            return m_MyData.arrChatRoomList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View v;
            if(view ==null) {
                LayoutInflater inflater = LayoutInflater.from(m_Context);
                v = inflater.inflate(R.layout.chat_userlist, viewGroup, false);
            }else {
                v = view;
            }
            TextView tv = v.findViewById(R.id.tv_email);

            ChatRoomData tempData = new ChatRoomData();
            tempData = (ChatRoomData)getItem(i);

            final String strRoomName = tempData.RoomName;

            String MyID =  tempData.MyNickName;
            String YourID = tempData.TargetNickName;
            tv.setText(MyID+"님과 "+ YourID+"님의 채팅방");

            final ChatRoomData finalTempData = tempData;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Chat_Room_Activity.class);
                    intent.putExtra("RoomName",(String)strRoomName);
                    intent.putExtra("TargetNickName",(String) finalTempData.TargetNickName);
                    intent.putExtra("TargetImg",(String) finalTempData.TargetImg);
                    startActivity(intent);
                }
            });
            return v;
        }
    }


}
