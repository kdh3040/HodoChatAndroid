package hodo.hodotalk.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by boram on 2017-07-18.
 */

public class UserData implements Serializable{

    private static final long  serialVersionUID = 1L;

    private String Index; // email 주소에서 @ 이전까지의 값.
    private int    Age;          // 나이 ( 1 ~ 99)
    private int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    private int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
    private String Email; // email 주소에서 @ 이전까지의 값.
    private int    Gender;       // 성별 (1: 여자, 2: 남자)
    private String Img;
    private int    Job;           // 직업
    private int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    private String NickName;     // 닉네임
    private int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    private String Token;     // 토큰
    private int    RecvHeart;       // 성별 (1: 여자, 2: 남자)
    private int    RecvInter;       // 성별 (1: 여자, 2: 남자)

/*
    private static UserData  _Instance;
    public static UserData getInstance()
    {
        if(_Instance == null)
            _Instance = new UserData();

        return  _Instance;
    }*/


    public UserData()
    {
        Index = null;
        Age =0;          // 나이 ( 1 ~ 99)
        Blood =0;         // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
        Body =0;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
        Email = null; // email 주소에서 @ 이전까지의 값.
        Gender = 0;       // 성별 (1: 여자, 2: 남자)
        RecvHeart = 0;
        RecvInter = 0;
        Img = null;
        Job = 0;           // 직업
        Location = 0;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
        NickName = null;     // 닉네임
        Religion = 0;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
        Token = null;     // 토큰

    }

    public  void SetData(String Idx, String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body, int _RecvHeart, int _RecvInter)
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
        RecvHeart = _RecvHeart;
        RecvInter = _RecvInter;
    }

    public  UserData GetDataAll()
    {
        UserData rtClass = new UserData();

        rtClass.Email = Email;
        rtClass.Token = Token;
        rtClass.Gender = Gender;
        rtClass.NickName = NickName;
        rtClass.Age = Age;
        rtClass.Blood = Blood;
        rtClass.Img = Img;
        rtClass.Location = Location;
        rtClass.Religion = Religion;
        rtClass.Job = Job;
        rtClass.Body = Body;

        return  rtClass;
    }

    public String getIndex() {
        return Index;
    }

    public String getEmail() {
        return Email;
    }

    public String getToken() {
        return Token;
    }

    public int getGender(){return  Gender;}

    public String getNickName() {
        return NickName;
    }

    public int getReligion() {
        return Religion;
    }

    public int getLocation() {
        return Location;
    }

    public int getAge() {
        return Age;
    }

    public int getJob() {
        return Job;
    }

    public int getBody() {
        return Body;
    }

    public int getBlood() {
        return Blood;
    }

    public int getRecvHeart() {
        return RecvHeart;
    }

    public int getRecvInter() {
        return RecvInter;
    }

    public String getImage() {
        return Img;
    }

    public void SetRecvHeart(String TargetEmail, int TargetGender, int _RecvHeart) {

        RecvHeart = _RecvHeart;

        int idx = TargetEmail.indexOf("@");
        String tempStr =  TargetEmail.substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        if(TargetGender == 0)
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);

        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("RecvHeart", RecvHeart);
        user.updateChildren(updateMap);
    }


}
