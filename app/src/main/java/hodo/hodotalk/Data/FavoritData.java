package hodo.hodotalk.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by boram on 2017-07-19.
 */

    public class FavoritData {

        private FirebaseAuth mAuth;
        private String Email; // email 주소에서 @ 이전까지의 값.
        private String Token;     // 토큰
        private String Img;
        private String NickName;     // 닉네임
        private  String Date;
        private int Conn;

        private Favorite_RecvData m_ValueFavorite= new Favorite_RecvData();
        private MyData m_stMyData = MyData.getInstance();
        private DatabaseReference ref1;
        private  String strID;

        public FavoritData()
        {
            Email = null; // email 주소에서 @ 이전까지의 값.
            Img = null;
            NickName = null;     // 닉네임
            Token = null;
            Date = null;
            Conn = 0;
        }

        public  void SetMyData(int _idx, int _FavoriteIdx, String _Email, int _Gender, String _Token, String _Img, String _NickName, int _Conn)
        {

            String idxStr = null;
            switch (_idx)
            {
                case 0:
                {
                    idxStr = "SendHeart";
                    break;
                }
                case 1:
                {
                    idxStr = "RecvHeart";
                    break;
                }
                case 2:
                {
                    idxStr = "RecvInter";
                    break;
                }
                case 3:
                {
                    idxStr = "SendInter";
                    break;
                }
            }

            Email = _Email;
            Token = _Token;
            Img = _Img;
            NickName = _NickName;
            Conn = _Conn;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String str_date = df.format(new Date());

            int idx =  FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
            String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

            m_ValueFavorite.SetData(str_date, Email, Img, NickName, Token, Conn);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference table;

            if(_Gender == 0 )
                table = database.getReference("CardList/WOMAN/" + tempStr + "/" + idxStr + "/");
            else
                table = database.getReference("CardList/MAN/" + tempStr + "/"+ idxStr+ "/");

            table.child(Integer.toString(_FavoriteIdx)).setValue(m_ValueFavorite);

     }

    public String getEmail() {
        return Email;
    }
    public String getToken() {
            return Token;
        }
    public String getNickName() {
        return NickName;
    }
    public String getImg() {
        return Img;
    }
    }
