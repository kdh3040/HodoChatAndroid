package hodo.hodotalk.Data;

import java.util.ArrayList;

import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by User0 on 2017-07-20.
 */

public class UserData_Group {

    private HoDoDefine cDef = HoDoDefine.getInstance();
  //  public UserData m_stUserData[] = new UserData[cDef.getDownloadCnt()];

    public ArrayList<UserData> arrUsers = new ArrayList<>();

    private static UserData_Group _Instance;
    public static UserData_Group getInstance()
    {
        if(_Instance == null)
            _Instance = new UserData_Group();

        return  _Instance;
    }

    private UserData_Group()
    {

    }
}
