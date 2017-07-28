package hodo.hodotalk.Util;

/**
 * Created by boram on 2017-07-19.
 */

public class HoDoDefine {

    public static int nHeartCost;
    public static int nDownloadCnt;
    public static int nViewListCnt;

    public static int nNotMatching;
    public static int nMatching;
    public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String SERVER_KEY = "AAAA1uJPLGE:APA91bG9TpgOzLvUuVkDSmZKdcyodQyT3yMuTeKusV9tXMc7k4EmZiss5FMW2rAtO5PFTSuFvy5nxOarw3bR2Gdk-5rwWSC19to7qBmEpfoUXh8USHZ9VianXYXq3D6pNyGKHKrcquAP";

    private static HoDoDefine _Instance;
    public static HoDoDefine getInstance()
    {
        if(_Instance == null)
            _Instance = new HoDoDefine();

        return  _Instance;
    }

    private HoDoDefine()
    {
        nDownloadCnt = 50;
        nViewListCnt = 4;
        nHeartCost =5;

        nNotMatching = 0;
        nMatching = 1;
    }

    public int getHeartCost() {return  nHeartCost;}
    public int getDownloadCnt() {return  nDownloadCnt;}
    public int getViewListCnt() {return  nViewListCnt;}


}
