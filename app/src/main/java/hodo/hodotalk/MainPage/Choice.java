package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.InvalidMarkException;
import java.util.Random;
import java.util.concurrent.Callable;

import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.HoDoDefine;

/**
 * Created by boram on 2017-07-12.
 */

public class Choice extends Fragment {

    private TextView Choice_tv1;
    private TextView Choice_tv2;
    private ImageView Choice_Img1;
    private ImageView Choice_Img2;



    private UserData_Group m_Userdata = UserData_Group.getInstance();
    private HoDoDefine m_Def = HoDoDefine.getInstance();
    private  int m_nIdx[] = new int[2];

    public static Choice newInstance(){
        Choice fragment = new Choice();
        return fragment;
    }


    public Choice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_choice, container, false);

        Choice_tv1 = (TextView)rootView.findViewById(R.id.choice_1);
        Choice_tv2 = (TextView)rootView.findViewById(R.id.choice_2);


        Choice_Img1 = (ImageView)rootView.findViewById(R.id.choiceIMG_1);
        Choice_Img2 = (ImageView)rootView.findViewById(R.id.choiceIMG_2);
        SetData();




        return rootView;
    }

    public  void SetData()
    {
        Random rand = new Random();


        // TODO : 2 빼야댐

        m_nIdx[0] = rand.nextInt(m_Def.getDownloadCnt() / 2);
        m_nIdx[1] = rand.nextInt(m_Def.getDownloadCnt() / 2);

        /*
        try {

            String _url = m_Userdata.m_stUserData[m_nIdx[0]].getImage();
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            Choice_Img1.setImageBitmap(bm);

        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


      /*  try {

            String _url = m_Userdata.m_stUserData[m_nIdx[1]].getImage();
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            Choice_Img2.setImageBitmap(bm);

        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}