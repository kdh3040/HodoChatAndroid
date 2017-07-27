package hodo.hodotalk.Util;

/**
 * Created by boram on 2017-07-19.
 */

public class HoDoDefine {

    public static int nHeartCost;
    public static int nDownloadCnt;
    public static int nViewListCnt;


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
    }

    public int getHeartCost() {return  nHeartCost;}
    public int getDownloadCnt() {return  nDownloadCnt;}
    public int getViewListCnt() {return  nViewListCnt;}


}
