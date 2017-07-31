package hodo.hodotalk.Data;

import android.media.session.MediaSession;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import hodo.hodotalk.R;
import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by boram on 2017-07-18.
 */

public class MyData {

    private int    Age;          // 나이 ( 1 ~ 99)
    private int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    private int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
    private String Index; // 고유 아이디
    private String Email; // email 주소에서 @ 이전까지의 값.
    private int    Gender;       // 성별 (1: 여자, 2: 남자)
    private int    Heart;
    private String Img;
    private int    Job;           // 직업
    private int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    private String NickName;     // 닉네임
    private int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    private String Token;     // 토큰
    private int    SendHeart;     // 하트 보낸 횟수
    private int    RecvHeart;     // 하트 받은 횟수
    private int    SendInter;     // 관심 보낸 횟수
    private int    RecvInter;     // 관심 받은 횟수
    private String  PushKey;     // 하트 보낸 횟수

    private  RecvHeart m_HeartRoomObj = new RecvHeart();
    private HoDoDefine m_Def = HoDoDefine.getInstance();

    public ArrayList<String> arrHeartRoomList = new ArrayList<>();
    //public ArrayList<String> arrChatRoomList = new ArrayList<>();
    public ArrayList<ChatRoomData> arrChatRoomList = new ArrayList<>();

    private static MyData  _Instance;

    public static MyData getInstance()
    {
        if(_Instance == null)
            _Instance = new MyData();

        return  _Instance;
    }


    private MyData()
    {
        Index = null;
        Age =0;          // 나이 ( 1 ~ 99)
        Blood =0;         // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
        Body =0;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
        Email = null; // email 주소에서 @ 이전까지의 값.
        Gender = 0;       // 성별 (1: 여자, 2: 남자)
        Heart = 0;
        Img = null;
        Job = 0;           // 직업
        Location = 0;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
        NickName = null;     // 닉네임
        Religion = 0;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
        Token = null;     // 토큰
        SendHeart = 0;     // 하트 보낸 횟수
        RecvHeart= 0;     // 하트 받은 횟수
        SendInter= 0;     // 관심 보낸 횟수
        RecvInter= 0;     // 관심 받은 횟수
        PushKey = null;
    }

    public  void SetData(String Idx, String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Heart, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body, int _SendHeart, int _RecvHeart, int _SendInter, int _RecvInter)
    {
        Index = Idx;
        Email = _Email;
        Token = _Token;
        Img = _Img;
        Gender = _Gender;
        NickName = _NickName;
        Age = _Age;
        Blood = _Blood;
        Location = _Loc;
        Religion = _Rel;
        Job = _Job;
        Body = _Body;
        Heart = _Heart;
        SendHeart = _SendHeart;     // 하트 보낸 횟수
        RecvHeart= _RecvHeart;     // 하트 받은 횟수
        SendInter= _SendInter;     // 관심 보낸 횟수
        RecvInter= _RecvInter;     // 관심 받은 횟수
    }

    public  MyData GetDataAll()
    {
        MyData rtClass = new MyData();

        rtClass.Email = Email;
        rtClass.Token = Token;
        rtClass.Gender = Gender;
        rtClass.NickName = NickName;
        rtClass.Age = Age;
        rtClass.Blood = Blood;
        rtClass.Location = Location;
        rtClass.Religion = Religion;
        rtClass.Job = Job;
        rtClass.Heart = Heart;
        rtClass.Body = Body;

        return  rtClass;
    }

    public  void GetHeartRoomList()
    {
        String MyID =  Index;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = null;

        if(Gender == 0)
            databaseRef = database.getReference("HeartRoomList" + "/WOMAN/" + MyID);
        else
            databaseRef = database.getReference("HeartRoomList" + "/MAN/" +  MyID);

        databaseRef.addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int saa =0;
                String strRoomList = dataSnapshot.getValue(String.class);
                if(!arrHeartRoomList.contains(strRoomList))
                    arrHeartRoomList.add(strRoomList);
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

    public  boolean MakeHeartRoomList(String MyIdx, String TargetIdx, int MyGender)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        table = database.getReference("HeartRoomList");
        DatabaseReference user, targetuser;
        if(MyGender == 0) {
            user = table.child("WOMAN").child(MyIdx);
            targetuser = table.child("MAN").child(TargetIdx);
        }
        else {
            user = table.child("MAN").child(MyIdx);
            targetuser = table.child("WOMAN").child(TargetIdx);
        }

        String strCheckName = MyIdx + "_" + TargetIdx;
        if(!arrHeartRoomList.contains(strCheckName)) {
            user.push().setValue(MyIdx + "_" + TargetIdx);
            targetuser.push().setValue(MyIdx + "_" + TargetIdx);
            return true;
        }
        else
            return false;
    }

    public  void MakeHeartRoom(String MyIdx, String TargetIdx, String MyNickName, String TargetNickName, String MyImg, String TargetImg, String MyToken, String TargetToken)
    {
        String MyID =  MyIdx;

        String TargetID =  TargetIdx;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        table = database.getReference("HeartRoom");

        DatabaseReference user = table.child(MyID + "_" + TargetID);

        long time = System.currentTimeMillis();
        SimpleDateFormat ctime = new SimpleDateFormat("yyyyMMdd");

        m_HeartRoomObj.MyIndex = MyIdx;
        m_HeartRoomObj.TargetIndex = TargetIdx;
        m_HeartRoomObj.MyNickName = MyNickName;
        m_HeartRoomObj.TargetNickName = TargetNickName;
        m_HeartRoomObj.MyImg = MyImg;
        m_HeartRoomObj.TargetImg = TargetImg;
        m_HeartRoomObj.MyToken = MyToken;
        m_HeartRoomObj.TargetToken = TargetToken;
        m_HeartRoomObj.Date = ctime.format(new Date(time));

        user.push().setValue(m_HeartRoomObj);
    }


    public  void GetChatRoomList()
    {
        //int idx = MyEmail.indexOf("@");
        //STring MyID =  MyEmail.substring(0, idx);
        String MyID = Index;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = null;

        if(Gender == 0)
            databaseRef = database.getReference("ChatRoomList" + "/WOMAN/" + MyID);
        else
            databaseRef = database.getReference("ChatRoomList" + "/MAN/" +  MyID);

        databaseRef.addChildEventListener(new ChildEventListener() {
            int i = 0;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int Check =0;
                //String strRoomList = dataSnapshot.getValue(String.class);
                ChatRoomData RoomList = dataSnapshot.getValue(ChatRoomData.class);

                for(int i = 0; i< arrChatRoomList.size(); i++)
                {
                    if(arrChatRoomList.get(i).RoomName.contains(RoomList.RoomName))
                        Check = 1;
                }

                if(Check != 1)
                    arrChatRoomList.add(RoomList);

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

    public boolean MakeChatRoomList(String TargetNickName, String TargetImg, String MyIdx, String TargetIdx, int MyGender){
       // int idx = MyEmail.indexOf("@");
       // String MyID =  MyEmail.substring(0, idx);

        /*idx = TargetEmail.indexOf("@");
        String TargetID =  TargetEmail.substring(0, idx);
*/
        String MyID = MyIdx;
        String TargetID = TargetIdx;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        table = database.getReference("ChatRoomList");
        DatabaseReference user, targetuser;

        if(MyGender == 0) {
            user = table.child("WOMAN").child(MyID);
            targetuser = table.child("MAN").child(TargetID);
        }
        else {
            user = table.child("MAN").child(MyID);
            targetuser = table.child("WOMAN").child(TargetID);
        }

        String strCheckName = MyID + "_" + TargetID;

        ChatRoomData tempData = new ChatRoomData();
        tempData.RoomName = strCheckName;

        tempData.MyImg = getImg();
        tempData.TargetImg =TargetImg;

        tempData.MyNickName = getNickName();
        tempData.TargetNickName =TargetNickName;

        int Check = 0;
        for(int i = 0; i< arrChatRoomList.size(); i++)
        {
            if(arrChatRoomList.get(i).RoomName.contains(strCheckName))
                Check = 1;
        }

        if(Check != 1) {
            user.push().setValue(tempData);
            targetuser.push().setValue(tempData);
            return true;
        }
        else
            return  false;
    }

    public void SendHeartItem(String strTargetToken) {

        try {
            // FMC 메시지 생성 start
            JSONObject root = new JSONObject();
            JSONObject notification = new JSONObject();
            JSONObject data = new JSONObject();
            //notification.put("body", stmyData.getNickName()+"님이 좋아요를 보냈습니다" + "@" + temp);
            //notification.put("body", stmyData.getNickName()+"님이 좋아요를 보냈습니다");
            notification.put("body", getNickName()+"님이 하트 선물을 하였습니다");
            notification.put("title", "호도톡");
            root.put("notification", notification);
            root.put("to", strTargetToken);
            root.put("data", data);
            // FMC 메시지 생성 end

            URL Url = new URL(m_Def.FCM_MESSAGE_URL);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.addRequestProperty("Authorization", "key=" + m_Def.SERVER_KEY);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(root.toString().getBytes("utf-8"));
            os.flush();
            conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIndex() {return  Index;}

    public String getEmail() {return  Email;}

    public int getGender() {return  Gender;}

    public void setHeart(int heart) {
        Heart = heart;

        //int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  getIndex();
        int nGrage = Integer.parseInt(tempStr) / 100;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN/"+Integer.toString(nGrage));
        else
            table = database.getReference("Account/MAN/"+Integer.toString(nGrage));

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("Heart", Heart);
        user.updateChildren(updateMap);

//        user.child("Heart").setValue(Heart);
    }
    public int getHeart()
    {
        return Heart;
    }

    public String getNickName() {
        return NickName;
    }
    public String getImg() {
        return Img;
    }

    public void UpdateMyProfile(String img, int age, int blood, int location, int religion, int job, int body) {
        Img= img;
        Age = age;
        Blood = blood;
        Location = location;
        Religion = religion;
        Job = job;
        Body = body;
    }

    public int getAge() {
        return Age;
    }

    public int getBlood() {
        return Blood;
    }

    public int getLocation() {
        return Location;
    }

    public int getReligion() {
        return Religion;
    }

    public int getJob() {
        return Job;
    }

    public int getBody() {
        return Body;
    }

    public String getToken(){
        return Token;
    }

    public int getSendInter() {
        return  SendInter;
    }
    public int getRecvHeart() {
        return  RecvHeart;
    }
    public int getRecvInter() {
        return  RecvInter;
    }
    public int getSendHeart() {
        return  SendHeart;
    }

}
