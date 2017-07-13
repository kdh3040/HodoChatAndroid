package hodo.hodotalk.Data;

/**
 * Created by boram on 2017-07-12.
 */

public class UserData_Url {
    public String userEmailID; // email 주소에서 @ 이전까지의 값.
    public String fcmToken;
    public String ImgUrl[] = new String[4];


    public UserData_Url()
    {
        userEmailID = null; // email 주소에서 @ 이전까지의 값.
        fcmToken = null;     // 토큰
        ImgUrl = null;
    }

    void SetDataString(int _idx, String _strValue)
    {
        switch (_idx)
        {
            case 0:
                userEmailID = _strValue;
                break;
            case 1:
                fcmToken = _strValue;
                break;
        }
    }


    void GetData(int _idx)
    {

    }

}
