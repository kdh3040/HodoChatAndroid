package hodo.hodotalk.MainPage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.BooleanResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.HoDoDefine;
import hodo.hodotalk.Util.TransformValue;

/**
 * Created by boram on 2017-07-13.
 */

public class MainPage_Adapter extends RecyclerView.Adapter<MainPage_Adapter.ViewHolder> {
    public List<UserData> items;

    public Picasso picasso;
    public LruCache picasso_LRuCache;

    static int cnt;
    int Listcnt;

    private HoDoDefine cDef = HoDoDefine.getInstance();
    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;

    private Bitmap bm;

    public TransformValue _TR = TransformValue.getInstance();
    private MainActivity cMA = new MainActivity();
    private static UserData_Group m_UserData = UserData_Group.getInstance();
    private MyData stMydata = MyData.getInstance();

    public MainPage_Adapter() {
        super();

        items = new ArrayList<UserData>();
       cnt = cDef.getDownloadCnt();
        Listcnt = cDef.getViewListCnt();
        //Listcnt = m_UserData.arrUsers.size();

        if(Listcnt > m_UserData.arrUsers.size())
            Listcnt = m_UserData.arrUsers.size();

        SetData_Firebase();
    }


    public boolean DelDataList(int _idx)
    {
        if(m_UserData.arrUsers.size() <=  _idx*Listcnt )
            return false;

        items.clear();

        if(bm != null)
            bm.recycle();

       // picasso_LRuCache.clear();

        return  true;
    }

    public boolean AddData(int _idx)
    {
        for(int i=0; i< Listcnt; i++) {
            //items.add(stUserData.m_stUserData[i + _idx*Listcnt]);
            if(m_UserData.arrUsers.size() <=  _idx*Listcnt )
                return false;

            items.add(m_UserData.arrUsers.get(i + _idx*Listcnt));
        }

        notifyDataSetChanged();
        return  true;
    }

    public  void  SetData_Firebase()
    {
        for(int i=0; i< Listcnt; i++) {

            items.add(m_UserData.arrUsers.get(i));
        }
    }

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mainpage_main_cardview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final UserData stUserData = items.get(i);

        String _Main = stUserData.getNickName() + "\n" + "(" + _TR.Transform_Loc(stUserData.getLocation()) + ", " + _TR.Transform_Age(stUserData.getAge()) + ")"
                + "\n" + _TR.Transform_job(stUserData.getJob()) + "\n" + _TR.Transform_Body(stUserData.getBody()) + ", " + _TR.Transform_Blood(stUserData.getBlood());
        viewHolder.tvNature.setText(_Main);

      DrawBitMapUrl(viewHolder, stUserData.getImage());

    }

    private Boolean DrawBitMapUrl(final ViewHolder viewHolder, final String image) {
        Boolean rtValue = false;

        Picasso.with(context)
                .load(image)
                .into(viewHolder.imgThumbnail);

        return rtValue;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public UserData SelectUser(int position) {
        UserData rtClass= null;
        rtClass = m_UserData.arrUsers.get(position);

        return  rtClass;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvNature;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView) itemView.findViewById(R.id.tv_des_nature);

           // ImageView picassoImageView = (ImageView) findViewById(R.id.img_thumbnail);
            context = itemView.getContext();

            picasso_LRuCache = new LruCache(context);
            picasso = new Picasso.Builder(context)
                    .memoryCache(picasso_LRuCache)
                    .build();
        }
    }

    public void RecycleBMP()
    {
        bm.recycle();
    }


}