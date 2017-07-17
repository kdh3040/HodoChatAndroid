package hodo.hodotalk.MainPage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-13.
 */

public class MainPage_Adapter extends RecyclerView.Adapter<MainPage_Adapter.ViewHolder> {
    private List<MainPage_Object> items;

    int cnt = 8;
    MainPage_Object nature[] = new MainPage_Object[cnt];
    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;

    public MainActivity _MA = new MainActivity();

    public MainPage_Adapter() {
        super();

        items = new ArrayList<MainPage_Object>();
       // SendMyProfile_FireBaseData();

        SetData_Firebase();
    }

    public  void  SetData_Firebase()
    {

        for(int i=0; i< cnt; i++) {

           // int a = random.nextInt(400);
            nature[i] = new MainPage_Object();
            nature[i] = _MA.UserData[i];
            items.add(nature[i]);
        }


    }

    public  void SendMyProfile_FireBaseData()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table = database.getReference("Account/ID");
        DatabaseReference user = table.child("user");
        user.child("customerID").setValue("apple");
        user.child("customerName").setValue("정소화");
        user.child("customerAge").setValue(20);
        user.child("customerGrade").setValue("gold");
        user.child("customerJob").setValue("학생");
        user.child("customerPoint").setValue(1000);
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
    private String Transform_Age(int _Age)
    {
        String rtValue = null;
        switch (_Age)
        {
            case 0:
                rtValue= "10대";
                break;
            case 1:
                rtValue = "20대";
                break;
            case 2:
                rtValue ="30대";
                break;
            case 3:
                rtValue = "40대";
                break;
            default:
                rtValue = "20대";
                break;

        }
        return rtValue;
    }

    private String Transform_Blood(int _Blood)
    {
        String rtValue = null;
        switch (_Blood)
        {
            case 0:
                rtValue= "A형";
                break;
            case 1:
                rtValue = "B형";
                break;
            case 2:
                rtValue ="O형";
                break;
            case 3:
                rtValue = "AB형";
                break;
            default:
                rtValue = "A형";
                break;

        }
        return rtValue;
    }

    private String Transform_Loc(int _Loc)
    {
        String rtValue = null;
        switch (_Loc)
        {
            case 0:
                rtValue= "서울";
                break;
            case 1:
                rtValue = "경기도";
                break;
            case 2:
                rtValue ="부산";
                break;
            case 3:
                rtValue = "인천";
                break;
            case 4:
                rtValue = "경남";
                break;
            case 5:
                rtValue = "경북";
                break;
            case 6:
                rtValue = "대구";
                break;
            case 7:
                rtValue ="전북";
                break;
            case 8:
                rtValue = "전남";
                break;
            case 9:
                rtValue = "광주";
                break;
            case 10:
                rtValue = "대전";
                break;
            case 11:
                rtValue = "울산";
                break;
            case 12:
                rtValue ="강원";
                break;
            case 13:
                rtValue = "충북";
                break;
            case 14:
                rtValue = "충남";
                break;
            case 15:
                rtValue = "세종";
                break;
            case 16:
                rtValue = "제주";
                break;
            default:
                rtValue = "전북";
                break;
        }
        return rtValue;
    }

    private String Transform_Rel(int _Rel)
    {
        String rtValue = null;
        switch (_Rel)
        {
            case 0:
                rtValue= "무교";
                break;
            case 1:
                rtValue = "불교";
                break;
            case 2:
                rtValue ="기독교";
                break;
            case 3:
                rtValue = "천주교";
                break;
            case 4:
                rtValue = "원불교";
                break;
            case 5:
                rtValue = "유교";
                break;
            case 6:
                rtValue = "이슬람";
                break;
            default:
                rtValue = "무교";
                break;

        }
        return rtValue;
    }

    private String Transform_job(int _Job)
    {
        String rtValue = null;
        switch (_Job)
        {
            case 0:
                rtValue= "학생";
                break;
            case 1:
                rtValue = "교수";
                break;
            case 2:
                rtValue ="사장";
                break;
            case 3:
                rtValue = "회장";
                break;
            case 4:
                rtValue = "가수";
                break;
            case 5:
                rtValue = "트수";
                break;
            case 6:
                rtValue = "백수";
                break;
            case 7:
                rtValue = "공무원";
                break;
            default:
                rtValue = "가수";
                break;

        }
        return rtValue;
    }

    private String Transform_Body(int _Body)
    {
        String rtValue = null;
        switch (_Body)
        {
            case 0:
                rtValue= "마른";
                break;
            case 1:
                rtValue = "슬림탄탄";
                break;
            case 2:
                rtValue ="보통";
                break;
            case 3:
                rtValue = "통통";
                break;
            case 4:
                rtValue = "근육";
                break;
            case 5:
                rtValue = "건장";
                break;
            default:
                rtValue = "근육";
                break;
        }
        return rtValue;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MainPage_Object nature = items.get(i);


        String _Main = nature.getNickName() + "\n" + "(" + Transform_Loc(nature.getLocation()) + ", " + Transform_Age(nature.getAge()) + ")"
                + "\n" + Transform_job(nature.getJob()) + "\n" + Transform_Body(nature.getBody()) + ", " + Transform_Blood(nature.getBlood());
        viewHolder.tvNature.setText(_Main);

        //viewHolder.imgThumbnail.setImageResource(R.drawable.ic_menu_gallery);

        //viewHolder.tvNature.setText(nature.getNickName());
        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        try {
            String _url = nature.getImage();
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