package hodo.hodotalk.Data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boram on 2017-07-19.
 */

    public class FavoritData {

        private FirebaseAuth mAuth;
        private String Email; // email 주소에서 @ 이전까지의 값.
        private String Img;
        private String NickName;     // 닉네임
        private MyData m_stMyData = MyData.getInstance();

    public FavoritData()
        {
            Email = null; // email 주소에서 @ 이전까지의 값.
            Img = null;
            NickName = null;     // 닉네임
        }

        public  void SetData(String _Email, String _Img,  String _NickName)
        {
            Email = _Email;
            Img = _Img;
            NickName = _NickName;

            int idx = m_stMyData.getEmail().indexOf("@");
            String tempStr =  m_stMyData.getEmail().substring(0, idx);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference table;
            if(m_stMyData.getGender() == 0 )
                table = database.getReference("Favorite/WOMAN");
            else
                table = database.getReference("Favorite/MAN");

            DatabaseReference user = table.child(tempStr);
            user.child("Email").setValue(_Email);
            user.child("NickName").setValue(_NickName);
            user.child("Img").setValue(_Img);

        }

        public FavoritData GetDataAll()
        {
            FavoritData rtClass = new FavoritData();

            rtClass.Email = Email;
            rtClass.NickName = NickName;

            return  rtClass;
        }


        public String getNickName() {
            return NickName;
        }

    public String getImg() {
        return Img;
    }
    }
