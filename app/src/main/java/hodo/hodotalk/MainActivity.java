package hodo.hodotalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.MainPage.Choice;
import hodo.hodotalk.MainPage.Connect;
import hodo.hodotalk.MainPage.Main;
import hodo.hodotalk.MainPage.Matching;
import hodo.hodotalk.MyPage.CardList;
import hodo.hodotalk.MyPage.EditProfile;
import hodo.hodotalk.MyPage.FAQ;
import hodo.hodotalk.MyPage.HeartCharge;
import hodo.hodotalk.MyPage.PopView;
import hodo.hodotalk.MyPage.QA;
import hodo.hodotalk.MyPage.Setting;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    // 메인 페이지
    private  Main cMain;
    private  Choice cChoice;
    private  Connect cConnect;
    private  Matching cMatching;

    // 마이 페이지
    private  CardList cCardList;
    private  FAQ cFAQ;
    private  HeartCharge cHeartCharge;
    private  PopView cPopView;
    private  QA cQA;
    private  Setting cSetting;

    static int cnt = 8;
    public static UserData stUserData[] = new UserData[cnt];
    public static MyData stMyData = MyData.getInstance();
    public static RecvData stRecvData = new RecvData ();


    private static final int RC_SIGN_IN = 1001;

    // Firebase - Authentication
    private FirebaseAuth mAuth;
    private String TAG = "@@@Firebase : ";
    private  String MyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.d("!!!!!", "App start----");

        // 오른쪽 밑에 우편 모양
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(MainActivity.this, ChatPage_main.class);
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Log.d("!!!!!", "firebase start----");
        InitData_firebase();
        InitProfile_firebase();
        Log.d("!!!!!", "firebase End----");
    }

    public  void InitData_firebase()
    {
        for(int i=0; i< cnt; i++) {
            stUserData[i] = UserData.getInstance();
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Account").child("ID");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {

                            stRecvData = fileSnapshot.getValue(RecvData.class);
                            stUserData[i++].SetData(stRecvData.Email, stRecvData.Token, stRecvData.Img, stRecvData.Gender, stRecvData.NickName, stRecvData.Age, stRecvData.Blood,
                                    stRecvData.Location, stRecvData.Religion, stRecvData.Job, stRecvData.Body);
                        }


                        initTab();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private  void initTab()
    {

        cMain = Main.newInstance();
        cChoice = Choice.newInstance();
        cConnect = Connect.newInstance();
        cMatching = Matching.newInstance();

        cCardList = CardList.newInstance();
        cFAQ = FAQ.newInstance();
        cHeartCharge  = HeartCharge.newInstance();
        cPopView = PopView.newInstance();
        cQA = QA.newInstance();
        cSetting = Setting.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                //.replace(R.id.fl_activity_main_container, cMain).commit();
                .replace(R.id.fl_activity_main_container, cMain).commit();


        TabHost tabhost = (TabHost) findViewById(R.id.tab_host);
        tabhost.setup();

        TabHost.TabSpec tab1 = tabhost.newTabSpec("1").setContent(R.id.tab1).setIndicator("메인");
        TabHost.TabSpec tab2 = tabhost.newTabSpec("2").setContent(R.id.tab2).setIndicator("매칭");
        TabHost.TabSpec tab3 = tabhost.newTabSpec("3").setContent(R.id.tab3).setIndicator("초이스");
        TabHost.TabSpec tab4 = tabhost.newTabSpec("4").setContent(R.id.tab4).setIndicator("접속중");

        tabhost.addTab(tab1);
        tabhost.addTab(tab2);
        tabhost.addTab(tab3);
        tabhost.addTab(tab4);


        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch (s) {
                    case "1":
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cMain).commit();
                        break;
                    case "2":
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cMatching).commit();
                        break;
                    case "3":
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cChoice).commit();
                        break;
                    case "4":
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cConnect).commit();
                        break;

                }

            }
        });

    }

    public  void InitProfile_firebase()//@NonNull final String userID, @NonNull final LoadUserCallback callback)
    {
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            int idx = mAuth.getCurrentUser().getEmail().indexOf("@");
            MyID =  mAuth.getCurrentUser().getEmail().substring(0, idx);
        } else {
            Log.d(TAG, "Log out State");
            finish();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Account").child("ID");

        ref.child(MyID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stRecvData = dataSnapshot.getValue(RecvData.class);
                stMyData.SetData(stRecvData.Email, stRecvData.Token, stRecvData.Img, stRecvData.Gender, stRecvData.NickName, stRecvData.Heart, stRecvData.Age, stRecvData.Blood,
                        stRecvData.Location, stRecvData.Religion, stRecvData.Job, stRecvData.Body);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public int GetMyHeart()
    {
        return  stMyData.GetHeart();
    }

    public void SetMyHeart(int _Heart)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table = database.getReference("Account/ID");
        DatabaseReference user = table.child(stMyData.getNickName());
        user.child("Heart").setValue(_Heart);
        stMyData.SetData_Heart(_Heart);
    }

    public void UpdateMyProfile(int _Age, int _Blood, int _Location, int _Religion, int _Job, int _body)
    {
        stMyData.UpdateMyProfile(_Age, _Blood, _Location, _Religion,_Job, _body);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cardlist) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cCardList).commit();
        } else if (id == R.id.nav_heartCharge) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cHeartCharge).commit();
        } else if (id == R.id.nav_popView) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cPopView).commit();
        } else if (id == R.id.nav_Setting) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cSetting).commit();
        } else if (id == R.id.nav_FAQ) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cFAQ).commit();
        } else if (id == R.id.nav_QA) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_activity_main_container, cQA).commit();
        }else if (id == R.id.nav_google_signin) {
           // signIn();
        }else if (id == R.id.nav_google_signout) {
           // signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
