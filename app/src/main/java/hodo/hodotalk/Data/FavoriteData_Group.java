package hodo.hodotalk.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by boram on 2017-07-19.
 */

public class FavoriteData_Group {

    private HoDoDefine cDef = HoDoDefine.getInstance();
    public FavoritData m_stFavorite[] = new FavoritData[cDef.getDownloadCnt()];

    private static FavoriteData_Group _Instance;
    public static FavoriteData_Group getInstance()
    {
        if(_Instance == null)
            _Instance = new FavoriteData_Group();

        return  _Instance;
    }

    private FavoriteData_Group()
    {
        for(int i = 0; i< cDef.getDownloadCnt(); i++)
            m_stFavorite[i] = new FavoritData();
    }

    public void GetDataFromFCB(int _idx)
    {
        m_stFavorite[_idx].GetDataFromFCB(_idx);
    }


}
