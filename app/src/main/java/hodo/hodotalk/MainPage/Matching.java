package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-12.
 */

public class Matching extends Fragment {

    public Button btn_NextMatching;
    public Button btn_SendHeart;

    private ImageView MatchingImg;
    private TextView MatchingText;

    int cnt = 4;

    static int nSel = 0;
    MainPage_Object nature[] = new MainPage_Object[cnt];

    public static Matching newInstance(){
        Matching fragment = new Matching();
        return fragment;
    }


    String url[] = new String[cnt];

    public Matching() {
        nSel = 0;
        // Required empty public constructor
        SetData();

    }

    public void refreshView()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.detach(this).attach(this).commit();
    }

    public  void  SetData()
    {
        Random random = new Random(); //랜덤 클래스의 객체를 생성합니다.

        for(int i=0; i< cnt; i++)
        {
            int a = random.nextInt(400);
            nature[i] = new MainPage_Object();
            nature[i].SetData("email"+i, "Token"+i, a, "Nickname"+i, a, a, a, a,a, a);
        }

        url[0] = "http://imgnews.naver.com/image/5291/2017/05/19/0000620923_001_20170519112758962.jpeg";
        url[1] = "http://cafefiles.naver.net/20131124_111/chzhmilk02_1385276243287y9nPi_JPEG/-556183727.jpg";
        url[2] = "http://imgnews.naver.com/image/076/2016/11/15/2016111601001307700088082_99_20161115110407.jpg";
        url[3] = "http://post.phinf.naver.net/MjAxNzA2MDZfOTMg/MDAxNDk2NzE3NzIyNzcz._U7ZYmsGuQYsAC-gsou0-SZSyntiixvoQPmOoAJ4MyAg.k4gpEsQayga2Qg4WWsJIUYfT90y1Gz7hOXt5nMxLbPog.PNG/IltlOiytiQYta927AVWckSu-lpA4.jpg";


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_matching, container, false);

        MatchingImg = (ImageView)rootView.findViewById(R.id.Profileimage);
        MatchingText = (TextView)rootView.findViewById(R.id.ProfileText);

        try {
            String _url = url[nSel];
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            MatchingImg.setImageBitmap(bm);
        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MatchingText.setText(nature[nSel].getNickName() + "\n" + nature[nSel].getLocation() + " | " + nature[nSel].getAge()+" | " + nature[nSel].getBody() + " \n " + nature[nSel].getBlood() + " | " + nature[nSel].getJob());
        btn_NextMatching = (Button)rootView.findViewById(R.id.BtnNextMatching);
        btn_NextMatching.setText("매칭 더보기");

        btn_SendHeart = (Button)rootView.findViewById(R.id.BtnSendHeart);
        btn_SendHeart.setText("하트 보내기");


        btn_NextMatching.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nSel +=1;
                refreshView();
            }
        });

        btn_SendHeart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog().build());

        return rootView;
    }

}