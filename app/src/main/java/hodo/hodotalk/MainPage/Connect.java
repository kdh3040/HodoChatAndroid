package hodo.hodotalk.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import hodo.hodotalk.Chat.Chat_UserList_Acitiviy;
import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-12.
 */

public class Connect extends Fragment {

    private Button btnStartChat;
    private TextView tvIam;
    private ImageView ImgFavorie;

    private FavoriteData_Group m_stFavorite = FavoriteData_Group.getInstance();

    public static Connect newInstance(){
        Connect fragment = new Connect();
        return fragment;
    }


    public Connect() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_connect, container, false);

        btnStartChat = (Button)rootView.findViewById(R.id.Btn_Favotie_SendHeart);
        tvIam = (TextView)rootView.findViewById(R.id.text_Favotie_Iam);
        ImgFavorie = (ImageView) rootView.findViewById(R.id.image_favorit_profile);
        btnStartChat.setText("채팅 하기");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.Btn_Favotie_SendHeart:
                        CreateChatRoom();
                        break;
                }
            }
        };

        btnStartChat.setOnClickListener(listener);
        SetData();

        return rootView;
    }

    private void CreateChatRoom() {

        Intent intent = new Intent(getContext(),Chat_UserList_Acitiviy.class);
        startActivity(intent);


    }

    private void SetData() {
/*        tvIam.setText(m_stFavorite.m_stFavorite[0].getNickName());

        try {
            String _url = m_stFavorite.m_stFavorite[0].getImg();
            URL imageURL = new URL(_url);

            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            ImgFavorie.setImageBitmap(bm);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}