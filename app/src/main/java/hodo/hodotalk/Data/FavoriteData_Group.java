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

    public void SetSendHeartData(int _idx, String _Email, int _Gender, String _Token, String _Img, String _NickName)
    {
        for(int i = 0; i< _idx; i++)
            m_SendHeart[i].SetMyData(0, i, _Email, _Gender, _Token, _Img, _NickName);
    }
    public void SetRecvHeart(int _idx, String _Email, int _Gender, String _Token, String _Img, String _NickName)
    {
        for(int i = 0; i< _idx; i++)
            m_RecvHeart[i].SetMyData(1, i, _Email, _Gender, _Token, _Img, _NickName);
    }
    public void SetRecvInterData(int _idx, String _Email, int _Gender, String _Token, String _Img, String _NickName)
    {
        for(int i = 0; i< _idx; i++)
            m_RecvInter[i].SetMyData(2, i, _Email, _Gender, _Token, _Img, _NickName);
    }

    public void SetSendInterData(int _idx, String _Email, int _Gender, String _Token, String _Img, String _NickName)
    {
        for(int i = 0; i< _idx; i++)
            m_SendInter[i].SetMyData(3, i, _Email, _Gender, _Token, _Img, _NickName);
    }


    public void GetSendHeartData(int _idx)
    {
        for(int i = 0; i< _idx; i++)
            m_SendHeart[i].GetSendData(0, i);
    }

    public void GetRecvHeart(int _idx)
    {
        for(int i = 0; i< _idx; i++)
            m_RecvHeart[i].GetRecvData(1, i);
    }
    public void GetRecvInterData(int _idx)
    {
        for(int i = 0; i< _idx; i++)
            m_RecvInter[i].GetRecvData(2, i);
    }

    public void GetSendInterData(int _idx)
    {
        for(int i = 0; i< _idx; i++)
            m_SendInter[i].GetSendData(3, i);
    }



}
