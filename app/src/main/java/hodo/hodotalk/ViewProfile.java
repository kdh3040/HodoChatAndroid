package hodo.hodotalk;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Service.PurchaseHeart;
import hodo.hodotalk.Util.HeartFunc;
import hodo.hodotalk.Util.HoDoDefine;
import hodo.hodotalk.Util.TransformValue;

public class ViewProfile extends AppCompatActivity {

    public  static TransformValue cTrans = TransformValue.getInstance();

    private HoDoDefine m_Def = HoDoDefine.getInstance();
    private MyData stmyData = MyData.getInstance();
    private HeartFunc fHeartFunc = HeartFunc.getInstance();

    private UserData stTargetData;
    private Button btnSendHeart;

    private String strImg;
    private ImageView imgProfile;

    private TextView txtProfile[] = new TextView[5];
    private FavoriteData_Group stFavoriteGroup = FavoriteData_Group.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InitData();

        Intent intent = getIntent();
        stTargetData = (UserData)intent.getExtras().getSerializable("Target");
        //String sss = intent.getStringExtra("Name");

        btnSendHeart = (Button)findViewById(R.id.Btn_Profile_SendHeart);
        btnSendHeart.setText("♡ 좋아요 보내기");

        txtProfile[0] = (TextView)findViewById(R.id.text_Iam);
        txtProfile[1] = (TextView)findViewById(R.id.text_job);
        txtProfile[2] = (TextView)findViewById(R.id.text_body);
        txtProfile[3] = (TextView)findViewById(R.id.text_Rel);
        txtProfile[4] = (TextView)findViewById(R.id.text_Blood);

        imgProfile = (ImageView)findViewById(R.id.image_profile);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.Btn_Profile_SendHeart:
                        AlertDialog.Builder newdlg  = fHeartFunc.SendHeart(stTargetData, ViewProfile.this);
                        newdlg.show();
                        //ClickBtnSendHeart();
                        break;

                }
            }
        };

        btnSendHeart.setOnClickListener(listener);

        ViewProfileIMG();
        ViewProfile();
    }

    private void InitData() {
        strImg = null;
        UserData stTargetData = new UserData();
    }

    public void ViewProfile()
    {
        txtProfile[0].setText("  " + cTrans.Transform_Age(stTargetData.getAge())+"이고 "+cTrans.Transform_Loc(stTargetData.getLocation())+"에 살고있어요.");
        txtProfile[1].setText("  " + cTrans.Transform_job(stTargetData.getJob())+"입니다.");
        txtProfile[2].setText("  " + cTrans.Transform_Body(stTargetData.getBody())+"입니다");
        txtProfile[3].setText("  " + cTrans.Transform_Rel(stTargetData.getReligion())+"입니다");
        txtProfile[4].setText("  " + cTrans.Transform_Blood(stTargetData.getBlood())+"입니다");
    }

    public void ViewProfileIMG()
    {
        Picasso.with(getApplicationContext())
                .load(stTargetData.getImage())
                .into(imgProfile);
    }
}
