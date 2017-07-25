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
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Util.TransformValue;

public class ViewProfile extends AppCompatActivity {

    public  static TransformValue cTrans = TransformValue.getInstance();

    private MyData stmyData = MyData.getInstance();
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
                        ClickBtnSendHeart();
                        break;

                }
                //  UpdateStatus();
            }
        };

        btnSendHeart.setOnClickListener(listener);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog().build());

        ViewProfileIMG();
        ViewProfile();
    }

    private void InitData() {
        strImg = null;
        UserData stTargetData = new UserData();
    }

    public  void ClickBtnSendHeart()
    {
        AlertDialog.Builder newdlg = new AlertDialog.Builder(ViewProfile.this);
        newdlg.setTitle(stTargetData.getNickName() + "님에게 좋아요를 보낼까요?");
        newdlg.setMessage("상대방이 좋아요를 수락하면" +"\n" + "채팅을 시작할 수 있습니다" + "\n" + "(하트 5개가 사용됩니다)").setCancelable(false);
        newdlg.setNegativeButton("사용하기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(stmyData.getHeart() > 5) {
                    stmyData.setHeart(stmyData.getHeart() - 5);
                    SaveFavoritePage();
                    SendHeartToFCM();
                    stmyData.setFavorite(stmyData.getFavorite()+1);


                }
            }

        });
        newdlg.setNeutralButton("하트구매", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int asdadsad=0;
            }
        });
        newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int asdadsad=0;
            }
        });

        newdlg.show();
    }

    private void SaveFavoritePage() {
        stFavoriteGroup.m_stFavorite[stmyData.getFavorite()].SetData(stmyData.getFavorite(), stTargetData.getEmail(), stTargetData.getToken(), stTargetData.getImage(), stTargetData.getNickName());
    }

    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAA1uJPLGE:APA91bG9TpgOzLvUuVkDSmZKdcyodQyT3yMuTeKusV9tXMc7k4EmZiss5FMW2rAtO5PFTSuFvy5nxOarw3bR2Gdk-5rwWSC19to7qBmEpfoUXh8USHZ9VianXYXq3D6pNyGKHKrcquAP";
    private void SendHeartToFCM() {

        try {
            // FMC 메시지 생성 start
            JSONObject root = new JSONObject();
            JSONObject notification = new JSONObject();
            notification.put("body", stmyData.getNickName()+"님이 좋아요를 보냈습니다");
            notification.put("title", getString(R.string.app_name));
            root.put("notification", notification);
            root.put("to", stTargetData.getToken());
            // FMC 메시지 생성 end

            URL Url = new URL(FCM_MESSAGE_URL);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(root.toString().getBytes("utf-8"));
            os.flush();
            conn.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            String _url = stTargetData.getImage();
            URL imageURL = new URL(_url);
            URLConnection ucon = imageURL.openConnection();
            ucon.connect();
            BufferedInputStream imagebuff = new BufferedInputStream(ucon.getInputStream(), (1024*50));
            Bitmap bm = BitmapFactory.decodeStream(imagebuff);
            imgProfile.setImageBitmap(bm);
            imagebuff.close();

        }         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
