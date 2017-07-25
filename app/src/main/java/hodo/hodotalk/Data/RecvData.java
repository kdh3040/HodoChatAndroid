package hodo.hodotalk.Data;

/**
 * Created by boram on 2017-07-18.
 */

public class RecvData {

    public int    Age;          // 나이 ( 1 ~ 99)
    public int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    public int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
    public String Email; // email 주소에서 @ 이전까지의 값.
    public int    Gender;       // 성별 (1: 여자, 2: 남자)
    public int    Heart;
    public String Img;
    public int    Job;           // 직업
    public int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    public String NickName;     // 닉네임
    public int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    public String Token;     // 토큰
    public  int Favorite;


    public RecvData()
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
        Favorite = 0;
    }

    public  void SetDataAll(String _Email, String _Token, String _Img, int _Gender, String _NickName, int _Heart, int _Age, int _Blood, int _Loc, int _Rel, int _Job, int _Body)
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

}
