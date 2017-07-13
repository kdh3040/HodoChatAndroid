package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-13.
 */

public class MainPage_Adapter extends RecyclerView.Adapter<MainPage_Adapter.ViewHolder> {
    private List<MainPage_Object> items;

    int cnt = 4;
    MainPage_Object nature[] = new MainPage_Object[cnt];

    public MainPage_Adapter() {
        super();

        items = new ArrayList<MainPage_Object>();
        SetData();
    }

    public  void  SetData()
    {
        Random random = new Random(); //랜덤 클래스의 객체를 생성합니다.

        for(int i=0; i< cnt; i++)
        {
            int a = random.nextInt(400);
            nature[i] = new MainPage_Object();
            nature[i].SetData("email"+i, "Token"+i, a, "Nickname"+i, a, a, a, a,a, a);
            items.add(nature[i]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mainpage_main_cardview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog().build());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MainPage_Object nature = items.get(i);

        String[] url = new String[cnt];
        url[0] = "http://imgnews.naver.com/image/5291/2017/05/19/0000620923_001_20170519112758962.jpeg";
        url[1] = "http://cafefiles.naver.net/20131124_111/chzhmilk02_1385276243287y9nPi_JPEG/-556183727.jpg";
        url[2] = "http://imgnews.naver.com/image/076/2016/11/15/2016111601001307700088082_99_20161115110407.jpg";
        url[3] = "http://post.phinf.naver.net/MjAxNzA2MDZfOTMg/MDAxNDk2NzE3NzIyNzcz._U7ZYmsGuQYsAC-gsou0-SZSyntiixvoQPmOoAJ4MyAg.k4gpEsQayga2Qg4WWsJIUYfT90y1Gz7hOXt5nMxLbPog.PNG/IltlOiytiQYta927AVWckSu-lpA4.jpg";

        String _Main = nature.getNickName() + "\n" + "(" + nature.getLocation() + ", " + nature.getAge() + ")" + "\n" + nature.getJob() + "\n" + nature.getBody() + ", " + nature.getBlood();
        viewHolder.tvNature.setText(_Main);

        //viewHolder.tvNature.setText(nature.getNickName());
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        try {
            String _url = url[i];
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imagebuff.close();
            viewHolder.imgThumbnail.setImageBitmap(bm);
        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvNature;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView) itemView.findViewById(R.id.tv_des_nature);

        }
    }
}