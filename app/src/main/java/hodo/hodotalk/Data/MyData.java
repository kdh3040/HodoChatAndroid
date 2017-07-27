package hodo.hodotalk.Data;

import android.media.session.MediaSession;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by boram on 2017-07-18.
 */

public class MyData {

    private int    Age;          // 나이 ( 1 ~ 99)
    private int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    private int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
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


    private static MyData  _Instance;

    public static MyData getInstance()
    {
        if(_Instance == null)
            _Instance = new MyData();

        return  _Instance;
    }


    private MyData()
    {
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

    public  void SetData(String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Heart, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body, int _SendHeart, int _RecvHeart, int _SendInter, int _RecvInter)
    {
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

    public String getEmail() {return  Email;}

    public int getGender() {return  Gender;}

    public void setHeart(int heart) {
        Heart = heart;

        int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

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

    public void UpdateMyProfile(int age, int blood, int location, int religion, int job, int body) {
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

    public void SetSendHeart(int _SendHeart) {

        SendHeart = _SendHeart;

        int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("SendHeart", SendHeart);
        user.updateChildren(updateMap);
    }



    public  void SetRecvHeartData(int _idx, String Email,  String _Img, String _NickName)
    {

        String idxStr = null;
        switch (_idx)
        {
            case 0:
            {
                idxStr = "/RecvHeart/";
                break;
            }
            case 1:
            {
                idxStr = "/RecvInter/";
                break;
            }
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        String tempStr = getEmail();
        int idx = tempStr.indexOf("@");
        String tempStr2 =  tempStr.substring(0, idx);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String str_date = df.format(new Date());

        if(getGender() == 0)
            table = database.getReference("CardList/WOMAN/" + tempStr2 + idxStr);
        else
            table = database.getReference("CardList/MAN/" + tempStr2 + idxStr);

        RecvHeart cRecvData = new RecvHeart();

        cRecvData.Email = Email;
        cRecvData.Img = _Img;
        cRecvData.NickName = _NickName;
        cRecvData.Date = str_date;

        DatabaseReference user = table;
        user.push().setValue(cRecvData);

        SetRecvHeart(getRecvHeart()+1);
    }

    public void SetRecvHeart(int _RecvHeart) {

        RecvHeart = _RecvHeart;

        int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("RecvHeart", RecvHeart);
        user.updateChildren(updateMap);
    }


    public void SetSendInter(int _SendInter) {

        SendInter = _SendInter;

        int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("SendInter", SendInter);
        user.updateChildren(updateMap);
    }


    public void SetRecvInter(int _RecvInter) {

        RecvInter = _RecvInter;

        int idx = FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
        String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("RecvInter", RecvInter);
        user.updateChildren(updateMap);
    }

    public void setPushKey(String tempStr, String _idx, int _FavoriteIdx, String favoritePushKey) {

        PushKey = favoritePushKey;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(Gender == 0)
            table = database.getReference("Account/WOMAN" + "/" + tempStr + "/PushKey/" + _idx);
        else
            table = database.getReference("CardList/MAN" + "/" + tempStr + "/PushKey/" + _idx);

        DatabaseReference user = table.child(Integer.toString(_FavoriteIdx));

        user.child("PushKey").setValue(PushKey);



    }
}
