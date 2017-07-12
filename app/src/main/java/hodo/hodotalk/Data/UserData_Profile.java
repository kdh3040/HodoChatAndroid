package hodo.hodotalk.Data;

public class UserData_Profile {
    public String userEmailID; // email 주소에서 @ 이전까지의 값.
    public String fcmToken;     // 토큰

    public int    Gender;       // 성별 (1: 여자, 2: 남자)

    public String Nickname;     // 닉네임
    public int    Age;          // 나이 ( 1 ~ 99)
    public int    Blood;        // 혈액형 (1: A형, 2: B형 3: O형 4: AB형)
    public int    Location;     // 지역 ( 1: 서울, 2: 경기도 3: 부산 4: 인천 5: 경남 6: 경북 7: 대구 8: 전북 9: 전남 10: 광주 11: 대전 12: 울산 13: 강원 14: 충북 15: 충남 16 : 세종 17: 제주)
    public int    Religion;     // 종교 ( 0: 무교 1: 불교 2; 기독 4: 천주 5: 원불 6: 유교 7: 이슬람)
    public int    Job;           // 직업
    public int    Body;          // 체형 (1: 마른 2: 슬림탄탄 3: 보통 4: 통통 5: 근육 6: 건장)
}
