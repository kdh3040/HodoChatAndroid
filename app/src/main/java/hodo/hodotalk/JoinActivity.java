package hodo.hodotalk;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class JoinActivity extends AppCompatActivity {

    private Button btnJoin;
    private Button btnNickName;
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

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterAge;
    ArrayAdapter<String> adapterBlood;
    ArrayAdapter<String> adapterLoc;
    ArrayAdapter<String> adapterRel;
    ArrayAdapter<String> adapterJob;
    ArrayAdapter<String> adapterBody;

    private  String[] arrStr = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnNickName = (Button) findViewById(R.id.btnNickName);
        btnAge = (Button) findViewById(R.id.btnAge);
        btnBlood = (Button) findViewById(R.id.btnBlood);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnRel = (Button) findViewById(R.id.btnReligion);
        btnJob = (Button) findViewById(R.id.btnjob);
        btnBody = (Button) findViewById(R.id.btnbody);


        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);

        adapterAge = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBlood = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterLoc = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterRel = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterJob = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBody = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);


        adapterAge.addAll("10대", "20대", "30대", "40대");
        adapterBlood.addAll("A형", "B형", "O형", "AB형");
        adapterLoc.addAll("서울", "경기도","부산", "인천", "경남", "경북", "대구", "전북", "전남", "광주", "대전", "울산", "강원", "충북", "충남", "세종", "제주");
        adapterRel.addAll("무교","불교","기독","천주", "원불교","유교", "이슬람");
        adapterJob.addAll("학생", "교수","사장", "회장", "가수", "트수", "백수", "공무원");
        adapterBody.addAll("빼뺴","마른","보통","근육", "건장","뚱뚱", "트수");

        btnJoin.setText("가입하기");
        btnNickName.setText("닉네임 : "+ strChoiceNickName);
        btnAge.setText("나이 : "+ strChoiceAge);
        btnBlood.setText("혈액형 : "+ strChoiceBlood);
        btnLoc.setText("지역 : "+ strChoiceLoc);
        btnRel.setText("종교 : "+ strChoiceRel);
        btnJob.setText("직업 : "+ strChoiceJob);
        btnBody.setText("체형 : "+ strChoiceBody);

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
                        CreateListDialog(adapterAge, 0);
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
    }

    private void UpdateStatus() {

        btnNickName = (Button) findViewById(R.id.btnNickName);
        btnAge = (Button) findViewById(R.id.btnAge);
        btnBlood = (Button) findViewById(R.id.btnBlood);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnRel = (Button) findViewById(R.id.btnReligion);
        btnJob = (Button) findViewById(R.id.btnjob);
        btnBody = (Button) findViewById(R.id.btnbody);

        btnNickName.setText("닉네임 : "+ strChoiceAge);
        btnAge.setText("나이 : "+ strChoiceAge);
        btnBlood.setText("혈액형 : "+ strChoiceBlood);
        btnLoc.setText("지역 : "+ strChoiceLoc);
        btnRel.setText("종교 : "+ strChoiceRel);
        btnJob.setText("직업 : "+ strChoiceJob);
        btnBody.setText("체형 : "+ strChoiceBody);
    }

    private void SetData_Firebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table = database.getReference("Account/ID");
        DatabaseReference user = table.child(strChoiceNickName);
        user.child("NickName").setValue(strChoiceNickName);
        user.child("Age").setValue(strChoiceAge);
        user.child("Blood").setValue(strChoiceBlood);
        user.child("Body").setValue(strChoiceBody);
        user.child("Job").setValue(strChoiceJob);
        user.child("Location").setValue(strChoiceLoc);
        user.child("Religion").setValue(strChoiceRel);
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
                Toast.makeText(JoinActivity.this, "선택한  : "+menu + " asdsad : " + which, Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                        strChoiceNickName = menu;
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

