package hodo.hodotalk.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.R;

/**
 * Created by mjk on 2017. 7. 28..
 */

public class Chat_UserList_Acitiviy extends AppCompatActivity {

    ListView listView;


    private UserData_Group userData_group = UserData_Group.getInstance();
    //DatabaseReference mDataRef;
    ArrayList<UserData> arrayList = new ArrayList<>();

    ListAdapter listAdapter;



    @Override
    protected void onStart() {
        super.onStart();



    }
    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i).getNickName();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View v;
            if(view ==null) {
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                v = inflater.inflate(R.layout.chat_userlist, viewGroup, false);
            }else {
                v = view;
            }
            TextView tv = v.findViewById(R.id.tv_email);

            tv.setText((String)getItem(i));
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Chat_Room_Activity.class);
                    intent.putExtra("nickName",(String)getItem(i));
                    startActivity(intent);
                }
            });
            return v;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_current_user);

        arrayList = userData_group.arrUsers;




        listView = (ListView)findViewById(R.id.listview);

        //mDataRef = FirebaseDatabase.getInstance().getReference().child("Account").child("MAN");
        listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);


    }


}
