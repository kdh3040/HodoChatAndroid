package hodo.hodotalk.Data;

import java.util.NoSuchElementException;

public class UserData_Profile {
    private String userEmailID; // email 주소에서 @ 이전까지의 값.
    private String fcmToken;     // 토큰

    private int    Gender;       // 성별 (1: 여자, 2: 남자)

    private String Nickname;     // 닉네임
    private int    Age;          // 나이 ( 1 ~ 99)
    private int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    private int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    private int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    private int    Job;           // 직업
    private int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)


    public UserData_Profile()
    {
        userEmailID = null ; // email 주소에서 @ 이전까지의 값.
        fcmToken = null;     // 토큰

        Gender = 0;       // 성별 (1: 여자, 2: 남자)

        Nickname = null;     // 닉네임
        Age = 0;          // 나이 ( 1 ~ 99)
        Blood = 0;         // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
        Location = 0;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
        Religion = 0;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
        Job = 0;           // 직업
        Body = 0;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
    }

    public  void SetDataAll(String _Email, String _Token, int _Gender, String _NickName, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body)
    {
        userEmailID = _Email;
        fcmToken = _Token;

        Gender = _Gender;
        Nickname = _NickName;
        Age = _Age;
        Blood = _Blood;
        Location = _Loc;
        Religion = _Rel;
        Job = _Job;
        Body = _Body;
    }

    public  UserData_Profile GetDataAll()
    {
        UserData_Profile rtClass = new UserData_Profile();

        rtClass.userEmailID = userEmailID;
        rtClass.fcmToken = fcmToken;
        rtClass.Gender = Gender;
        rtClass.Nickname = Nickname;
        rtClass.Age = Age;
        rtClass.Blood = Blood;
        rtClass.Location = Location;
        rtClass.Religion = Religion;
        rtClass.Job = Job;
        rtClass.Body = Body;

        return  rtClass;
    }

    public  String GetDataStr(int _idx)
    {
        String rtn = null;
        switch (_idx) {
            case 0:
                rtn = userEmailID;
                break;
            case 1:
                rtn = fcmToken;
                break;
            case 2:
                rtn = Nickname;
                break;
        }
        return  rtn;
    }

    public  int GetData(int _idx)
    {
        int rtn = 0;
        switch (_idx)
        {
            case 0:
                rtn = Age;
                break;
            case 1:
                rtn = Blood;
                break;
            case 2:
                rtn = Location;
                break;
            case 3:
                rtn = Religion;
                break;
            case 4:
                rtn = Job;
                break;
            case 5:
                rtn = Body;
                break;
        }
        return rtn;
    }
}
