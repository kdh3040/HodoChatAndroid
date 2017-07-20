package hodo.hodotalk.Data;

import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by User0 on 2017-07-20.
 */

public class UserData_Group {

    private HoDoDefine cDef = HoDoDefine.getInstance();
    public UserData m_stUserData[] = new UserData[cDef.getDownloadCnt()];

    private static UserData_Group _Instance;
    public static UserData_Group getInstance()
    {
        if(_Instance == null)
            _Instance = new UserData_Group();

        return  _Instance;
    }

    private UserData_Group()
    {
        for(int i = 0; i< cDef.getDownloadCnt(); i++)
            m_stUserData[i] = new UserData();
    }
}
