package hodo.hodotalk;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Util.TransformValue;

public class JoinActivity extends AppCompatActivity {

    private ImageView ImgProfile;

    private Button btnJoin;
    private Button btnImg;
    private Button btnNickName;
    private Button btnAge;
    private  Button btnBlood;
    private  Button btnLoc;
    private  Button btnRel;
    private  Button btnJob;
    private  Button btnBody;
    private  Button btnGender;

    private  static String strChoiceNickName;
    private  static int nChoiceAge;
    private  static int nChoiceBlood;
    private  static int nChoiceLoc;
    private  static int nChoiceRel;
    private  static int nChoiceJob;
    private  static int nChoiceBody;
    private  static int nChoiceGender;
    private  static String strToken ;
    private  static String strImgUri ;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterAge;
    ArrayAdapter<String> adapterBlood;
    ArrayAdapter<String> adapterGender;
    ArrayAdapter<String> adapterLoc;
    ArrayAdapter<String> adapterRel;
    ArrayAdapter<String> adapterJob;
    ArrayAdapter<String> adapterBody;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://hodotalk.appspot.com/");
    private TransformValue cTrans = TransformValue.getInstance();
    private MyData m_stMyData = MyData.getInstance();
    private String TAG = "@@@Firebase : ";


    private File file;
    private  String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InitProfile_firebase();

        path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"/TEST_TEXT_WRITE";

        ImgProfile = (ImageView)findViewById(R.id.imageProfile);

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnNickName = (Button) findViewById(R.id.btnNickName);
        btnAge = (Button) findViewById(R.id.btnAge);
        btnBlood = (Button) findViewById(R.id.btnBlood);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnRel = (Button) findViewById(R.id.btnReligion);
        btnJob = (Button) findViewById(R.id.btnjob);
        btnBody = (Button) findViewById(R.id.btnbody);
        btnGender = (Button) findViewById(R.id.btnGender);
        btnImg = (Button) findViewById(R.id.btnImage);

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);

        adapterAge = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBlood = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterLoc = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterRel = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterJob = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBody = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterGender= new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);

        adapterAge.addAll("10대", "20대", "30대", "40대");
        adapterBlood.addAll("A형", "B형", "O형", "AB형");
        adapterLoc.addAll("서울", "경기도","부산", "인천", "경남", "경북", "대구", "전북", "전남", "광주", "대전", "울산", "강원", "충북", "충남", "세종", "제주");
        adapterRel.addAll("무교","불교","기독","천주", "원불교","유교", "이슬람");
        adapterJob.addAll("학생", "교수","사장", "회장", "가수", "트수", "백수", "공무원");
        adapterBody.addAll("빼뺴","마른","보통","근육", "건장","뚱뚱", "트수");
        adapterGender.addAll("여성","남성");

        btnJoin.setText("가입하기");
        btnNickName.setText("닉네임을 정해주세요");
        btnAge.setText("나이를 정해주세요");
        btnBlood.setText("혈액형을 정해주세요");
        btnLoc.setText("지역을 정해주세요");
        btnRel.setText("종교를 정해주세요");
        btnJob.setText("직업을 정해주세요");
        btnBody.setText("체형을 정해주세요");
        btnGender.setText("성별을 정해주세요");
        btnImg.setText("프로필 사진 등록");

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
                    case R.id.btnJoin:
                        SetData_Firebase();
                        break;
                    case R.id.btnNickName:
                        //CreateListDialog(adapterAge, 0);
                        CreateTextViewDialog();
                        break;
                    case R.id.btnAge:
                        CreateListDialog(adapterAge, 0);
                        break;
                    case R.id.btnBlood:
                        CreateListDialog(adapterBlood, 1);
                        break;
                    case R.id.btnLoc:
                        CreateListDialog(adapterLoc ,2);
                        break;
                    case R.id.btnReligion:
                        CreateListDialog(adapterRel, 3);
                        break;
                    case R.id.btnjob:
                        CreateListDialog(adapterJob, 4);
                        break;
                    case R.id.btnbody:
                        CreateListDialog(adapterBody, 5);
                        break;
                    case R.id.btnGender:
                        CreateListDialog(adapterGender, 6);
                        break;
                    case R.id.btnImage:
                        LoadImage(view);
                        break;
                }
              //  UpdateStatus();
            }

        };

        btnNickName.setOnClickListener(listener);
        btnAge.setOnClickListener(listener);
        btnBlood.setOnClickListener(listener);
        btnLoc.setOnClickListener(listener);
        btnRel.setOnClickListener(listener);
        btnJob.setOnClickListener(listener);
        btnBody.setOnClickListener(listener);
        btnJoin.setOnClickListener(listener);
        btnGender.setOnClickListener(listener);
        btnImg.setOnClickListener(listener);


       // onTextWriting("test", "testBody");
    }

    private void onTextWriting(String title,String body){
        File file;
        file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        file = new File(path+File.separator+title+".txt");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter buw = new BufferedWriter(new OutputStreamWriter(fos, "UTF8"));
            buw.write(body);
            buw.close();
            fos.close();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
        }catch(IOException e){

        }
    }

    private void LoadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select"), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                ImgProfile.setImageBitmap(scaled);

                UploadImage_Firebase(uri);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void UploadImage_Firebase(Uri file) {

        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Tr(downloadUrl);
            }
        });

    }

    public void Tr(Uri uri)
    {
        strImgUri = uri.toString();
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

        int idx = mAuth.getCurrentUser().getEmail().indexOf("@");
        strChoiceNickName =  mAuth.getCurrentUser().getEmail().substring(0, idx);

    }

    private void UpdateStatus() {

        btnNickName = (Button) findViewById(R.id.btnNickName);
        btnAge = (Button) findViewById(R.id.btnAge);
        btnBlood = (Button) findViewById(R.id.btnBlood);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnRel = (Button) findViewById(R.id.btnReligion);
        btnJob = (Button) findViewById(R.id.btnjob);
        btnGender = (Button) findViewById(R.id.btnGender);
        btnBody = (Button) findViewById(R.id.btnbody);

        btnNickName.setText("닉네임 : "+ strChoiceNickName);
        btnAge.setText("나이 : "+ cTrans.Transform_Age(nChoiceAge));
        btnBlood.setText("혈액형 : "+ cTrans.Transform_Blood(nChoiceBlood));
        btnLoc.setText("지역 : "+ cTrans.Transform_Loc(nChoiceLoc));
        btnRel.setText("종교 : "+ cTrans.Transform_Rel(nChoiceRel));
        btnJob.setText("직업 : "+ cTrans.Transform_job(nChoiceJob));
        btnBody.setText("체형 : "+ cTrans.Transform_Body(nChoiceBody));
        btnGender.setText("성별 : " + cTrans.Transform_Gender(nChoiceGender));
    }

    private void SetData_Firebase(){

        int idx = mAuth.getCurrentUser().getEmail().indexOf("@");
        String tempStr =  mAuth.getCurrentUser().getEmail().substring(0, idx);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;
        if(nChoiceGender == 0 )
            table = database.getReference("Account/WOMAN");
        else
            table = database.getReference("Account/MAN");

        DatabaseReference user = table.child(tempStr);
        user.child("NickName").setValue(strChoiceNickName);
        user.child("Age").setValue(nChoiceAge);
        user.child("Email").setValue(mAuth.getCurrentUser().getEmail());
        user.child("Token").setValue(FirebaseInstanceId.getInstance().getToken());
        user.child("Blood").setValue(nChoiceBlood);
        user.child("Body").setValue(nChoiceBody);
        user.child("Job").setValue(nChoiceJob);
        user.child("Location").setValue(nChoiceLoc);
        user.child("Religion").setValue(nChoiceRel);
        user.child("Gender").setValue(nChoiceGender);
        user.child("Img").setValue(strImgUri);

        m_stMyData.SetData(mAuth.getCurrentUser().getEmail(), FirebaseInstanceId.getInstance().getToken(), strImgUri, nChoiceGender, strChoiceNickName, 0, nChoiceAge, nChoiceBlood, nChoiceLoc, nChoiceRel, nChoiceJob, nChoiceBody, 0);

        Intent intent = new Intent(JoinActivity.this, MainActivity.class);
        intent.putExtra("MyGender", nChoiceGender);
        startActivity(intent);
        finish();

    }

    public void CreateTextViewDialog()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(JoinActivity.this);
        ad.setTitle("닉네임");
        ad.setMessage("닉네임을 정해주세요");

        final EditText et = new EditText(JoinActivity.this);
        ad.setView(et);

        ad.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "Yes Btn Click");

                // Text 값 받아서 로그 남기기
                strChoiceNickName = et.getText().toString();
                UpdateStatus();
                dialog.dismiss();     //닫기
                // Event
            }

        });

// 취소 버튼 설정
        ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"No Btn Click");
                dialog.dismiss();     //닫기
                // Event
            }
        });

// 창 띄우기


        ad.show();
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
                Toast.makeText(JoinActivity.this, "선택한  : "+menu + " asdsad : " + which, Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                        nChoiceAge = which;
                        break;
                    case 1:
                        nChoiceBlood = which;
                        break;
                    case 2:
                        nChoiceLoc = which;
                        break;
                    case 3:
                        nChoiceRel = which;
                        break;
                    case 4:
                        nChoiceJob = which;
                        break;
                    case 5:
                        nChoiceBody = which;
                        break;
                    case 6:
                        nChoiceGender = which;
                        break;
                }

                UpdateStatus();

            }

        });

        alert.show();
    }


}

