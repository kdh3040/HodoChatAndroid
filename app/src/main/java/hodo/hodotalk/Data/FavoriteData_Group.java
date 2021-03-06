package hodo.hodotalk.Data;

import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by boram on 2017-07-19.
 */

public class FavoriteData_Group {

    private HoDoDefine cDef = HoDoDefine.getInstance();
    public FavoritData m_SendHeart[] = new FavoritData[cDef.getDownloadCnt()];
    public FavoritData m_RecvHeart[] = new FavoritData[cDef.getDownloadCnt()];
    public FavoritData m_SendInter[] = new FavoritData[cDef.getDownloadCnt()];
    public FavoritData m_RecvInter[] = new FavoritData[cDef.getDownloadCnt()];


    private static FavoriteData_Group _Instance;
    public static FavoriteData_Group getInstance()
    {
        if(_Instance == null)
            _Instance = new FavoriteData_Group();

        return  _Instance;
    }

    private FavoriteData_Group()
    {
        for(int i = 0; i< cDef.getDownloadCnt(); i++) {
            m_SendHeart[i] = new FavoritData();
            m_RecvHeart[i] = new FavoritData();
            m_SendInter[i] = new FavoritData();
            m_RecvInter[i] = new FavoritData();
        }
    }
}
