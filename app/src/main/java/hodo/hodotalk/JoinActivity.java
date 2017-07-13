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

import java.util.Timer;
import java.util.TimerTask;

public class JoinActivity extends AppCompatActivity {

    private Button btnAge;
    private  Button btnBlood;
    private  Button btnLoc;
    private  Button btnRel;
    private  Button btnJob;
    private  Button btnBody;

    private  static String nChoiceAge;
    private  static String nChoiceBlood;
    private  static String nChoiceLoc;
    private  static String nChoiceRel;
    private  static String nChoiceJob;
    private  static String nChoiceBody;

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

        btnAge.setText("나이 : "+ nChoiceAge);
        btnBlood.setText("혈액형 : "+ nChoiceBlood);
        btnLoc.setText("지역 : "+ nChoiceLoc);
        btnRel.setText("종교 : "+ nChoiceRel);
        btnJob.setText("직업 : "+ nChoiceJob);
        btnBody.setText("체형 : "+ nChoiceBody);

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

        btnAge.setOnClickListener(listener);
        btnBlood.setOnClickListener(listener);
        btnLoc.setOnClickListener(listener);
        btnRel.setOnClickListener(listener);
        btnJob.setOnClickListener(listener);
        btnBody.setOnClickListener(listener);

    }

    private void UpdateStatus() {

        btnAge = (Button) findViewById(R.id.btnAge);
        btnBlood = (Button) findViewById(R.id.btnBlood);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnRel = (Button) findViewById(R.id.btnReligion);
        btnJob = (Button) findViewById(R.id.btnjob);
        btnBody = (Button) findViewById(R.id.btnbody);

        btnAge.setText("나이 : "+ nChoiceAge);
        btnBlood.setText("혈액형 : "+ nChoiceBlood);
        btnLoc.setText("지역 : "+ nChoiceLoc);
        btnRel.setText("종교 : "+ nChoiceRel);
        btnJob.setText("직업 : "+ nChoiceJob);
        btnBody.setText("체형 : "+ nChoiceBody);
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
                        nChoiceAge = menu;
                        break;
                    case 1:
                        nChoiceBlood = menu;
                        break;
                    case 2:
                        nChoiceLoc = menu;
                        break;
                    case 3:
                        nChoiceRel = menu;
                        break;
                    case 4:
                        nChoiceJob = menu;
                        break;
                    case 5:
                        nChoiceBody = menu;
                        break;
                }

                UpdateStatus();
            }

        });

        alert.show();
    }


}

