package hodo.hodotalk.MainPage;

import android.content.Context;
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

import com.bumptech.glide.Glide;
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
import hodo.hodotalk.Util.HeartFunc;
import hodo.hodotalk.Util.HoDoDefine;
import hodo.hodotalk.Util.TransformValue;
import hodo.hodotalk.ViewProfile;

/**
 * Created by boram on 2017-07-12.
 */

public class Matching extends Fragment {

    private  Bitmap bm;
    public Button btn_NextMatching;
    public Button btn_SendHeart;

    private ImageView MatchingImg;
    private TextView MatchingText;

    private HeartFunc fHeartFunc = HeartFunc.getInstance();
    private UserData_Group m_UserData = UserData_Group.getInstance();
    private MyData m_MyData = MyData.getInstance();
    private HoDoDefine m_Def = HoDoDefine.getInstance();
    private TransformValue _TR = TransformValue.getInstance();

    private FavoriteData_Group stFavoriteGroup = FavoriteData_Group.getInstance();
    static int nSel = 0;

    private Context context;

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

    public  void  SetData(int idx)
    {

        // TODO : 2 빼야댐



        Log.d("@#### ", "random : " + idx);

        DrawMatingProfile(idx);
        DrawMatingProfileImg(idx);
    }

    private void DrawMatingProfile(int idx) {
        String _Main = m_UserData.arrUsers.get(idx).getNickName() + "\n"  + _TR.Transform_Loc(m_UserData.arrUsers.get(idx).getLocation()) + ", " + _TR.Transform_Age(m_UserData.arrUsers.get(idx).getAge())
                +  _TR.Transform_job(m_UserData.arrUsers.get(idx).getJob()) + "\n" + _TR.Transform_Body(m_UserData.arrUsers.get(idx).getBody()) + ", " + _TR.Transform_Blood(m_UserData.arrUsers.get(idx).getBlood());

        MatchingText.setText(_Main);

    }

    private void DrawMatingProfileImg(int idx) {
        Glide.with(context)
                .load(m_UserData.arrUsers.get(idx).getImage())
                .into(MatchingImg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_matching, container, false);

        Random random = new Random();
        nSel= random.nextInt(m_UserData.arrUsers.size());

        MatchingText = (TextView)rootView.findViewById(R.id.ProfileText);
        MatchingImg= (ImageView) rootView.findViewById(R.id.Profileimage);

        btn_SendHeart= (Button)rootView.findViewById(R.id.BtnSendHeart);
        btn_SendHeart.setText("좋아요 보내기");

        btn_NextMatching = (Button)rootView.findViewById(R.id.BtnNextMatching);
        btn_NextMatching .setText("더 보기");

        Button.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.BtnSendHeart:
                        AlertDialog.Builder newdlg  = fHeartFunc.SendHeart(m_UserData.arrUsers.get(nSel), rootView.getContext());
                        newdlg.show();
                        break;
                    case R.id.BtnNextMatching:
                        NextMatching();
                        break;

                }
            }
        };

        context = rootView.getContext();
        btn_SendHeart.setOnClickListener(listener);
        btn_NextMatching.setOnClickListener(listener);

        SetData(nSel);

        Log.d("!!!!!", "App End----");

        return rootView;
    }

    private void NextMatching() {
        Random random = new Random();
        nSel= random.nextInt(m_UserData.arrUsers.size());
        SetData(nSel);
        refreshView();
    }

}