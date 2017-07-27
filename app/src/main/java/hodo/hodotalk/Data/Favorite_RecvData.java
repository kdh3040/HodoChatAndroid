package hodo.hodotalk.Data;

/**
 * Created by boram on 2017-07-25.
 */

public class Favorite_RecvData {
    String PushKey;
    String Date;
    String Email;
    String Img;
    String NickName;
    String Token;
    int Conn;

    public Favorite_RecvData() {
    }

    public void SetData(String _Date, String _Email, String _Img, String _NickName, String _Token, int _Conn) {
        Date = _Date;
        Email = _Email;
        Img = _Img;
        NickName = _NickName;
        Token = _Token;
        Conn = _Conn;
    }

}
