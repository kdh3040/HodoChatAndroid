package hodo.hodotalk.MainPage;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by boram on 2017-07-12.
 */

public class Matching extends Fragment {

    private  Bitmap bm;
    public Button btn_NextMatching;
    public Button btn_SendHeart;

    private ImageView MatchingImg;
    private TextView MatchingText;

    private UserData_Group m_UserData = UserData_Group.getInstance();
    private MyData m_MyData = MyData.getInstance();
    private HoDoDefine m_Def = HoDoDefine.getInstance();

    private FavoriteData_Group stFavoriteGroup = FavoriteData_Group.getInstance();
    static int nSel = 0;

    public static Matching newInstance(){
        Matching fragment = new Matching();
        return fragment;
    }


    public Matching() {
        nSel = 0;
        // Required empty public constructor

    }

    public void refreshView()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.detach(this).attach(this).commit();
    }

    public  void  SetData()
    {
        Random random = new Random();

        // TODO : 2 빼야댐
        nSel= random.nextInt(m_Def.getDownloadCnt()/2);

        Log.d("@#### ", "random : " + nSel);

        //MatchingImg.setImageBitmap();
           /*    try {
     if(bm != null)
                bm.recycle();

            String _url = m_UserData.m_stUserData[nSel].getImage();
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            MatchingImg.setImageBitmap(bm);

        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_matching, container, false);

        MatchingText = (TextView)rootView.findViewById(R.id.ProfileText);
        MatchingImg= (ImageView) rootView.findViewById(R.id.Profileimage);

        btn_SendHeart= (Button)rootView.findViewById(R.id.BtnSendHeart);
        btn_SendHeart.setText("좋아요 보내기");

        btn_NextMatching = (Button)rootView.findViewById(R.id.BtnNextMatching);
        btn_NextMatching .setText("더 보기");


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.BtnSendHeart:
                        SendHeart(view);
                        break;
                    case R.id.BtnNextMatching:
                        NextMatching();
                        break;

                }
            }
        };

        btn_SendHeart.setOnClickListener(listener);
        btn_NextMatching.setOnClickListener(listener);

        SetData();

        Log.d("!!!!!", "App End----");

        return rootView;
    }

    private void SendHeart(View view) {

    }

    private void NextMatching() {

        refreshView();
    }

}