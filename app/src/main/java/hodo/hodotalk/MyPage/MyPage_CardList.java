package hodo.hodotalk.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hodo.hodotalk.Chat.Chat_UserList_Acitiviy;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvHeart;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.HeartFunc;
import hodo.hodotalk.Util.HoDoDefine;

public class MyPage_CardList extends AppCompatActivity {

    private HoDoDefine m_Def = HoDoDefine.getInstance();

    private LinearLayout SendHeartLayout;
    private LinearLayout RecvHeartLayout;
    private MyData m_Mydata = MyData.getInstance();
    private HeartFunc m_HeartFunc = HeartFunc.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page__card_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SendHeartLayout = (LinearLayout)findViewById(R.id.SendHeart);
        RecvHeartLayout = (LinearLayout)findViewById(R.id.RecvHeart);

        SetHeartRoom();
    }

    private void SetHeartRoom() {
        int nRoomCnt = m_Mydata.arrHeartRoomList.size();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = null;
        databaseRef = database.getReference("HeartRoom");

        for (int i = 0; i < nRoomCnt; i++) {
            final int finalI = i;
            databaseRef.child(m_Mydata.arrHeartRoomList.get(i)).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    int saa = 0;
                    RecvHeart cRecvCard = dataSnapshot.getValue(RecvHeart.class);

                    final String strDate = cRecvCard.Date;
                    final String strMyNick = cRecvCard.MyNickName;
                    final String strTargetNick = cRecvCard.TargetNickName;
                    final String strMyImg = cRecvCard.MyImg;
                    final String strTargetImg = cRecvCard.TargetImg;
                    final String strMyToken = cRecvCard.MyToken;
                    final String strTargetToken = cRecvCard.TargetToken;

                    boolean bPickMe = true;
                    if(strMyNick.equals(m_Mydata.getNickName()))
                        bPickMe = false;

                    LinearLayout item1 = new LinearLayout(getApplicationContext());
                    Button newTextView1 = new Button(getApplicationContext());
                    item1.setOrientation(LinearLayout.VERTICAL);
                    //newTextView1.setText(m_Mydata.arrRoomList.get(finalI));
                    newTextView1.setText(strMyNick + " 님이 " + strTargetNick + "님에게 보낸 하트") ;
                    newTextView1.setId(finalI + 1);


                    final int position = finalI;
                    final boolean finalPickMe = bPickMe;
                    newTextView1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            AlertDialog.Builder newdlg = new AlertDialog.Builder(MyPage_CardList.this);
                            if(finalPickMe == false) {
                                newdlg.setTitle(strTargetNick + "님에게 하트를 선물 할까요");
                                newdlg.setMessage("상대방에게 하트를 추가로 보냅니다").setCancelable(false);
                                newdlg.setNegativeButton("사용하기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        m_Mydata.SendHeartItem(strTargetToken);
                                        //m_HeartFunc.SendHeartToFCM(stTargetData);
                                     //   m_Mydata.MakeChatRoomList(m_Mydata.getEmail(), strTargetNick, m_Mydata.getGender());
                                    }
                                });
                                newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                      //  m_Mydata.MakeChatRoomList(m_Mydata.getEmail(), strTargetNick, m_Mydata.getGender());
                                    }
                                });
                            }

                            else
                            {
                                newdlg.setTitle(strMyNick + "님의 하트를 받습니다");
                                newdlg.setMessage("하트를 받으면 채팅 화면으로 넘어갑니다").setCancelable(false);
                                newdlg.setNegativeButton("채팅하기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //m_Mydata.MakeChatRoomList();
                                        m_Mydata.SendHeartItem(strTargetToken);
                                        m_Mydata.MakeChatRoomList(m_Mydata.getEmail(), strMyNick, m_Mydata.getGender());
                                        startActivity(new Intent(getApplicationContext(), Chat_UserList_Acitiviy.class));
                                    }

                                });
                                newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        int asdadsad = 0;
                                    }
                                });

                            }
                            newdlg.show();

                            //Toast toast = Toast.makeText(getApplicationContext(), "현재 버튼 " + m_Mydata.arrRoomList.get(finalI), Toast.LENGTH_SHORT);
                            //toast.show();
                       /* Intent intent = new Intent(getApplicationContext(), ChatPage_main.class);
                        intent.putExtra("TargetNick", strNick);
                        startActivity(intent);*/
                        }
                    });

                    LinearLayout.LayoutParams item_param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    item_param1.setMargins(5, 5, 5, 5);

                    item1.setLayoutParams(item_param1);
                    item1.addView(newTextView1);

                    if(finalPickMe == false)
                        SendHeartLayout.addView(item1);
                    else
                        RecvHeartLayout.addView(item1);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    int saa = 0;
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    int saa = 0;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    int saa = 0;
                }
            });

        }

    }

}

