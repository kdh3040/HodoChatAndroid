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

public class JoinActivity extends AppCompatActivity {

    private Button btnAge;
    private  Button btnBlood;
    private  Button btnLoc;
    private  Button btnRel;
    private  Button btnJob;
    private  Button btnBody;

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

        adapterBlood = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapterBlood.addAll("A형", "B형", "O형", "AB형");


        adapterBlood.notifyDataSetChanged();

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.btnAge:
                        CreateListDialog();
                        break;
                   /* case R.id.btnMatching:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cMatching).commit();
                        break;
                    case R.id.btnChoice:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cChoice).commit();
                        break;
                    case R.id.btnConnect:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cConnect).commit();
                        break;*/
                }
            }
        };

        btnAge.setOnClickListener(listener);
        btnBlood.setOnClickListener(listener);
        btnLoc.setOnClickListener(listener);
        btnRel.setOnClickListener(listener);
        btnJob.setOnClickListener(listener);
        btnBody.setOnClickListener(listener);
    }

    int nChiceAge = 0;

    public int CreateListDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("혈액형");     //타이틀
        alert.setIcon(R.drawable.ic_menu_gallery); //아이콘


        //어답터 , 클릭이벤트 설정
        alert.setAdapter(adapterBlood, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String menu = adapterBlood.getItem(which);
                Toast.makeText(JoinActivity.this, "선택한 혈액형 : "+menu, Toast.LENGTH_SHORT).show();
                nChiceAge = which;
            }
        });
        alert.show();

        return  nChiceAge;

    }


}

