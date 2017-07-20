package hodo.hodotalk.Data;

import android.media.session.MediaSession;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
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

    }

    public  void SetData(String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Heart, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body)
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
}
