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
        }

        public  void SetMyData(int _idx, int _FavoriteIdx, String _Email, int _Gender, String _Token, String _Img, String _NickName)
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
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String str_date = df.format(new Date());

            int idx =  FirebaseAuth.getInstance().getCurrentUser().getEmail().indexOf("@");
            String tempStr =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, idx);

            m_ValueFavorite.SetData(str_date, Email, Img, NickName, Token);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference table;

            if(_Gender == 0 )
                table = database.getReference("CardList/WOMAN/" + tempStr + "/" + idxStr + "/");
            else
                table = database.getReference("CardList/MAN/" + tempStr + "/"+ idxStr+ "/");

            table.child(Integer.toString(_FavoriteIdx)).setValue(m_ValueFavorite);

     }

    public  String GetPushKeyData(String idxStr, int i)
    {
        final String[] rtStr = {null};

        if(m_stMyData.getGender() == 0)
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("WOMAN").child(strID).child("PushKey").child(idxStr);
        else
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("MAN").child(strID).child("PushKey").child(idxStr);

        ref1.child(Integer.toString(i)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String returnSTR = dataSnapshot.getValue(String.class);
                rtStr[0] = returnSTR;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rtStr[0];
    }

    public  void GetSendData(int _idx, int i)
    {
        int idx = m_stMyData.getEmail().indexOf("@");
        strID =  m_stMyData.getEmail().substring(0, idx);

        String idxStr = null;
        switch (_idx)
        {
            case 0:
            {
                idxStr = "/SendHeart/";
                break;
            }
            case 1:
            {
                idxStr = "/RecvHeart/";
                break;
            }
            case 2:
            {
                idxStr = "/RecvInter/";
                break;
            }
            case 3:
            {
                idxStr = "/SendInter/";
                break;
            }
        }


        if(m_stMyData.getGender() == 0)
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("WOMAN").child(strID).child(idxStr).child(Integer.toString(i));
        else
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("MAN").child(strID).child(idxStr).child(Integer.toString(i));




            //ref1.child(strTarget).addListenerForSingleValueEvent(new ValueEventListener() {
            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Favorite_RecvData stRecvData = dataSnapshot.getValue(Favorite_RecvData.class);
                    if(stRecvData != null)
                    {
                        Email = stRecvData.Email;
                        Token = stRecvData.Token;
                        Img = stRecvData.Img;
                        NickName = stRecvData.NickName;
                        Date = stRecvData.Date;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }


    public  void GetRecvData(int _idx, int i)
    {
        int idx = m_stMyData.getEmail().indexOf("@");
        strID =  m_stMyData.getEmail().substring(0, idx);

        String idxStr = null;
        switch (_idx)
        {
            case 0:
            {
                idxStr = "/SendHeart/";
                break;
            }
            case 1:
            {
                idxStr = "/RecvHeart/";
                break;
            }
            case 2:
            {
                idxStr = "/RecvInter/";
                break;
            }
            case 3:
            {
                idxStr = "/SendInter/";
                break;
            }
        }


        if(m_stMyData.getGender() == 0)
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("WOMAN").child(strID).child(idxStr).child(Integer.toString(i));
        else
            ref1 = FirebaseDatabase.getInstance().getReference().child("CardList").child("MAN").child(strID).child(idxStr).child(Integer.toString(i));




        //ref1.child(strTarget).addListenerForSingleValueEvent(new ValueEventListener() {
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Favorite_RecvData stRecvData = dataSnapshot.getValue(Favorite_RecvData.class);
                if(stRecvData != null)
                {
                    Email = stRecvData.Email;
                    Token = stRecvData.Token;
                    Img = stRecvData.Img;
                    NickName = stRecvData.NickName;
                    Date = stRecvData.Date;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

        public FavoritData GetDataAll()
        {
            FavoritData rtClass = new FavoritData();

            rtClass.Email = Email;
            rtClass.NickName = NickName;

            return  rtClass;
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
