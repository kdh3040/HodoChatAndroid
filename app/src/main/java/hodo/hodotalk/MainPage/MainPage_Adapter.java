package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.TransformValue;

/**
 * Created by boram on 2017-07-13.
 */

public class MainPage_Adapter extends RecyclerView.Adapter<MainPage_Adapter.ViewHolder> {
    public List<UserData> items;

    static int cnt = 8;
    int Listcnt = 4;

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;


    public TransformValue _TR = new TransformValue();
    public static UserData stUserData[] = new UserData[cnt];

    public MainPage_Adapter() {
        super();

        items = new ArrayList<UserData>();

        for(int i=0; i< cnt; i++) {
            stUserData[i] = UserData.getInstance();
        }

        SetData_Firebase();
    }


    public void DelDataList()
    {
        items.clear();
    }

    public void AddData(int _idx)
    {
        for(int i=0; i< Listcnt; i++) {
            items.add(stUserData[i + _idx*4]);
        }
        notifyDataSetChanged();
    }

    public  void  SetData_Firebase()
    {
        for(int i=0; i< Listcnt; i++) {
            items.add(stUserData[i]);
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
        UserData stUserData = items.get(i);


        String _Main = stUserData.getNickName() + "\n" + "(" + _TR.Transform_Loc(stUserData.getLocation()) + ", " + _TR.Transform_Age(stUserData.getAge()) + ")"
                + "\n" + _TR.Transform_job(stUserData.getJob()) + "\n" + _TR.Transform_Body(stUserData.getBody()) + ", " + _TR.Transform_Blood(stUserData.getBlood());
        viewHolder.tvNature.setText(_Main);

        //viewHolder.imgThumbnail.setImageResource(R.drawable.ic_menu_gallery);

        //viewHolder.tvNature.setText(nature.getNickName());
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        try {
            String _url = stUserData.getImage();
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

    public String SelectUser(int position) {
        String rtValue = null;
        if(stUserData[position].getNickName() !=null)
        {
            rtValue = stUserData[position].getNickName();
            Log.d("####", stUserData[position].getNickName() );
        }
            return  rtValue;
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