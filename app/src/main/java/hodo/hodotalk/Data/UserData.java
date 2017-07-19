package hodo.hodotalk.Data;

import java.io.Serializable;

/**
 * Created by boram on 2017-07-18.
 */

public class UserData implements Serializable{

    private static final long  serialVersionUID = 1L;

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
        Age =20;          // 나이 ( 1 ~ 99)
        Blood =20;         // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
        Body =20;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
        Email = null; // email 주소에서 @ 이전까지의 값.
        Gender = 20;       // 성별 (1: 여자, 2: 남자)
        Heart = 0;
        Img = null;
        Job = 20;           // 직업
        Location = 20;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
        NickName = null;     // 닉네임
        Religion = 20;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
        Token = null;     // 토큰

    }

    public  void SetData(String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body)
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
        rtClass.Heart = Heart;
        rtClass.Body = Body;

        return  rtClass;
    }

    public String getEmail() {
        return Email;
    }

    public String getToken() {
        return Token;
    }

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

    public String getImage() {
        return Img;
    }
}
