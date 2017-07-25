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
        private MyData m_stMyData = MyData.getInstance();

    public FavoritData()
        {
            Email = null; // email 주소에서 @ 이전까지의 값.
            Img = null;
            NickName = null;     // 닉네임
            Token = null;
            Date = null;
        }

        public  void SetData(int _FavoriteIdx, String _Email, String _Token, String _Img,  String _NickName)
        {
            Email = _Email;
            Token = _Token;
            Img = _Img;
            NickName = _NickName;

            int idx = m_stMyData.getEmail().indexOf("@");
            String tempStr =  m_stMyData.getEmail().substring(0, idx);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference table;
            if(m_stMyData.getGender() == 0 )
                table = database.getReference("Favorite/WOMAN/" + tempStr);
            else
                table = database.getReference("Favorite/MAN/" + tempStr);

            DatabaseReference user = table.child(Integer.toString(_FavoriteIdx));
            //DatabaseReference user = table.child(NickName);
            user.child("Email").setValue(Email);
            user.child("Token").setValue(Token);
            user.child("NickName").setValue(NickName);
            //user.child("Img").setValue(Img);
            user.child("Img").setValue("DummY");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String str_date = df.format(new Date());

            user.child("Date").setValue(str_date);
            Date = str_date;

     }

    public  void GetDataFromFCB(int _idx)
    {
        int idx = m_stMyData.getEmail().indexOf("@");
        String tempStr =  m_stMyData.getEmail().substring(0, idx);
        DatabaseReference ref1;
        if(m_stMyData.getGender() == 0)
            ref1 = FirebaseDatabase.getInstance().getReference().child("Favorite").child("WOMAN").child(tempStr);
        else
            ref1 = FirebaseDatabase.getInstance().getReference().child("Favorite").child("MAN").child(tempStr);


            ref1.child(Integer.toString(_idx)).addListenerForSingleValueEvent(new ValueEventListener() {
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
