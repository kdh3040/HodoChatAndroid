package hodo.hodotalk.MainPage;

import android.media.session.MediaSession;

import hodo.hodotalk.Data.UserData_Profile;
import hodo.hodotalk.Data.UserData_Url;

/**
 * Created by boram on 2017-07-13.
 */

public class MainPage_Object {

        private String nAge;
        private String description;
        private int thumbnail;


    private String userEmailID; // email 주소에서 @ 이전까지의 값.
    private String fcmToken;     // 토큰
    private String Img;     // 토큰

    private int    Gender;       // 성별 (1: 여자, 2: 남자)

    private String Nickname;     // 닉네임
    private int    Age;          // 나이 ( 1 ~ 99)
    private int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    private int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    private int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    private int    Job;           // 직업
    private int    Body;


    //UserData_Profile _userProfile[]  = new UserData_Profile[5];
    UserData_Profile _userProfile;


        public MainPage_Object()
        {
            _userProfile = new UserData_Profile();
        }


    public void SetData(String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body){
        _userProfile.SetDataAll(_Email, _Token, _Img, _Gender, _NickName, _Age, _Blood, _Loc, _Rel, _Job, _Body);

        userEmailID = _Email; // email 주소에서 @ 이전까지의 값.
        fcmToken = _Token;     // 토큰
        Img    = _Img;
        Gender = _Gender;       // 성별 (1: 여자, 2: 남자)

        Nickname = _NickName;     // 닉네임
        Age = _Age;          // 나이 ( 1 ~ 99)
        Blood = _Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
        Location = _Loc;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
        Religion = _Rel;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
        Job = _Job;           // 직업
        Body = _Body;

    }

    public String getImage() {

        return Img;
    }

    public String getEmail() {

        if(_userProfile != null)
            return _userProfile.GetDataStr(0);

        return null;
    }

    public String getToken() {

        if(_userProfile != null)
            return _userProfile.GetDataStr(1);

        return null;
    }

    public String getNickName() {

        if(_userProfile != null)
            return _userProfile.GetDataStr(2);

        return null;
    }


    public String getGender() {

        if(_userProfile != null)
            return _userProfile.GetDataStr(2);

        return null;
    }

    public int getAge() {

        if(_userProfile != null)
            return _userProfile.GetData(0);

        return 0;
    }

    public int getBlood() {

        if(_userProfile != null)
            return _userProfile.GetData(1);

        return 0;
    }
    public int getLocation() {

        if(_userProfile != null)
            return _userProfile.GetData(2);

        return 0;
    }
    public int getReligion() {

        if(_userProfile != null)
            return _userProfile.GetData(3);

        return 0;
    }

    public int getJob() {

        if(_userProfile != null)
            return _userProfile.GetData(4);

        return 0;
    }
    public int getBody() {

        if(_userProfile != null)
            return _userProfile.GetData(5);

        return 0;
    }


}