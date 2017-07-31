package hodo.hodotalk.MyPage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;
import hodo.hodotalk.Util.TransformValue;

public class EditProfile extends AppCompatActivity {

    private ImageView ProfileImage;

    private  Button btnImage;
    private Button btnJoin;
    private Button btnAge;
    private  Button btnBlood;
    private  Button btnLoc;
    private  Button btnRel;
    private  Button btnJob;
    private  Button btnBody;

    private  static String strChoiceNickName;
    private  static int strChoiceAge;
    private  static int strChoiceBlood;
    private  static int strChoiceLoc;
    private  static int strChoiceRel;
    private  static int strChoiceJob;
    private  static int strChoiceBody;
    private  static String strToken ;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterAge;
    ArrayAdapter<String> adapterBlood;
    ArrayAdapter<String> adapterLoc;
    ArrayAdapter<String> adapterRel;
    ArrayAdapter<String> adapterJob;
    ArrayAdapter<String> adapterBody;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;



    private String TAG = "@@@Firebase : ";
    private  String[] arrStr = new String[2];


    public static TransformValue _TV = TransformValue.getInstance();
    public static MyData stMyData = MyData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProfileImage = (ImageView)findViewById(R.id.EditProfile_image);

        btnImage = (Button)findViewById(R.id.EditProfile_btnImage);
        btnJoin = (Button) findViewById(R.id.EditProfile_btnJoin);
        btnAge = (Button) findViewById(R.id.EditProfile_btnAge);
        btnBlood = (Button) findViewById(R.id.EditProfile_btnBlood);
        btnLoc = (Button) findViewById(R.id.EditProfile_btnLoc);
        btnRel = (Button) findViewById(R.id.EditProfile_btnReligion);
        btnJob = (Button) findViewById(R.id.EditProfile_btnjob);
        btnBody = (Button) findViewById(R.id.EditProfile_btnbody);


        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);

        adapterAge = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBlood = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterLoc = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterRel = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterJob = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBody = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);


        Setting_MyData();
        ViewProfileIMG();

        adapterAge.addAll("10대", "20대", "30대", "40대");
        adapterBlood.addAll("A형", "B형", "O형", "AB형");
        adapterLoc.addAll("서울", "경기도","부산", "인천", "경남", "경북", "대구", "전북", "전남", "광주", "대전", "울산", "강원", "충북", "충남", "세종", "제주");
        adapterRel.addAll("무교","불교","기독","천주", "원불교","유교", "이슬람");
        adapterJob.addAll("학생", "교수","사장", "회장", "가수", "트수", "백수", "공무원");
        adapterBody.addAll("빼뺴","마른","보통","근육", "건장","뚱뚱", "트수");

        adapterAge.notifyDataSetChanged();
        adapterBlood.notifyDataSetChanged();
        adapterLoc.notifyDataSetChanged();
        adapterRel.notifyDataSetChanged();
        adapterJob.notifyDataSetChanged();
        adapterBody.notifyDataSetChanged();

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.EditProfile_btnJoin:
                        SetData_Firebase();
                        break;
                    case R.id.EditProfile_btnAge:
                        CreateListDialog(adapterAge, 0);
                        break;
                    case R.id.EditProfile_btnBlood:
                        CreateListDialog(adapterBlood, 1);
                        break;
                    case R.id.EditProfile_btnLoc:
                        CreateListDialog(adapterLoc ,2);
                        break;
                    case R.id.EditProfile_btnReligion:
                        CreateListDialog(adapterRel, 3);
                        break;
                    case R.id.EditProfile_btnjob:
                        CreateListDialog(adapterJob, 4);
                        break;
                    case R.id.EditProfile_btnbody:
                        CreateListDialog(adapterBody, 5);
                        break;
                    case R.id.EditProfile_btnImage:
                        ViewProfileIMG();
                        break;
                }
                //  UpdateStatus();
            }
        };

        btnAge.setOnClickListener(listener);
        btnBlood.setOnClickListener(listener);
        btnLoc.setOnClickListener(listener);
        btnRel.setOnClickListener(listener);
        btnJob.setOnClickListener(listener);
        btnBody.setOnClickListener(listener);
        btnJoin.setOnClickListener(listener);
        btnImage.setOnClickListener(listener);

        InitProfile_firebase();
    }

    public  void Setting_MyData()
    {
        btnJoin.setText("저장하기");
        btnAge.setText("나이 : "+ _TV.Transform_Age(stMyData.getAge()));
        btnBlood.setText("혈액형 : "+ _TV.Transform_Blood(stMyData.getBlood()));
        btnLoc.setText("지역 : "+ _TV.Transform_Loc(stMyData.getLocation()));
        btnRel.setText("종교 : "+ _TV.Transform_Rel(stMyData.getReligion()));
        btnJob.setText("직업 : "+ _TV.Transform_job(stMyData.getJob()));
        btnBody.setText("체형 : "+ _TV.Transform_Body(stMyData.getBody()));



    }

    public  void InitProfile_firebase()
    {
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            Log.d(TAG, "Current User:" + mAuth.getCurrentUser().getEmail());
            // Go to Main Page
            //tvUser.setText("USER:" + mAuth.getCurrentUser().getEmail());
        } else {
            Log.d(TAG, "Log out State");
            finish();
        }

        mUser = mAuth.getCurrentUser();
        mUser.getToken(true)
                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            strToken = task.getResult().getToken();
                            // Send token to your backend via HTTPS
                            // ...
                        } else {
                            // Handle error -> task.getException();
                        }
                    }
                });
    }

    private void UpdateStatus() {

        btnAge = (Button) findViewById(R.id.EditProfile_btnAge);
        btnBlood = (Button) findViewById(R.id.EditProfile_btnBlood);
        btnLoc = (Button) findViewById(R.id.EditProfile_btnLoc);
        btnRel = (Button) findViewById(R.id.EditProfile_btnReligion);
        btnJob = (Button) findViewById(R.id.EditProfile_btnjob);
        btnBody = (Button) findViewById(R.id.EditProfile_btnbody);

        btnAge.setText("나이 : "+ _TV.Transform_Age(strChoiceAge));
        btnBlood.setText("혈액형 : "+ _TV.Transform_Blood(strChoiceBlood));
        btnLoc.setText("지역 : "+ _TV.Transform_Loc(strChoiceLoc));
        btnRel.setText("종교 : "+ _TV.Transform_Rel(strChoiceRel));
        btnJob.setText("직업 : "+ _TV.Transform_job(strChoiceJob));
        btnBody.setText("체형 : "+ _TV.Transform_Body(strChoiceBody));

        ViewProfileIMG();

        stMyData.UpdateMyProfile(strChoiceAge, strChoiceBlood, strChoiceLoc, strChoiceRel, strChoiceJob, strChoiceBody);

    }

    public void ViewProfileIMG()
    {
        Glide.with(getApplicationContext())
                .load(stMyData.getImg())
                .into(ProfileImage);
    }


    private void SetData_Firebase(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        String strUserIdx = stMyData.getIndex();
        int nGrage = Integer.parseInt(strUserIdx) / 100;

        if(stMyData.getGender() == 0)
            table = database.getReference("Account/WOMAN/" + Integer.toString(nGrage));
        else
            table = database.getReference("Account/MAN/" + Integer.toString(nGrage));

        DatabaseReference user = table.child(strUserIdx);
        user.child("Age").setValue(strChoiceAge);
        user.child("Blood").setValue(strChoiceBlood);
        user.child("Body").setValue(strChoiceBody);
        user.child("Job").setValue(strChoiceJob);
        user.child("Location").setValue(strChoiceLoc);
        user.child("Religion").setValue(strChoiceRel);
        String strToken = FirebaseInstanceId.getInstance().getToken();
        user.child("Token").setValue(strToken);
        //user.child("ImageUrl").setValue(strChoice);
    }

    public void CreateListDialog(ArrayAdapter<String> _adapter, final  int i){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("선택하세요");     //타이틀
        alert.setIcon(R.drawable.ic_menu_gallery); //아이콘

        adapter = _adapter;
        //어답터 , 클릭이벤트 설정
        alert.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String menu = adapter.getItem(which);
                //Toast.makeText(JoinActivity.this, "선택한  : "+menu + " asdsad : " + which, Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                        strChoiceAge = which;
                        break;
                    case 1:
                        strChoiceBlood = which;
                        break;
                    case 2:
                        strChoiceLoc = which;
                        break;
                    case 3:
                        strChoiceRel = which;
                        break;
                    case 4:
                        strChoiceJob = which;
                        break;
                    case 5:
                        strChoiceBody = which;
                        break;
                }

                UpdateStatus();

            }

        });

        alert.show();
    }

}

