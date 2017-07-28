package hodo.hodotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hodo.hodotalk.Chat.Chat_UserList_Acitiviy;
import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.Data.UserData_Group;
import hodo.hodotalk.MainPage.Choice;
import hodo.hodotalk.MainPage.Connect;
import hodo.hodotalk.MainPage.Main;
import hodo.hodotalk.MainPage.Matching;
import hodo.hodotalk.MyPage.EditProfile;
import hodo.hodotalk.MyPage.MyPage_CardList;
import hodo.hodotalk.Service.PurchaseHeart;
import hodo.hodotalk.Util.HoDoDefine;
import hodo.hodotalk.Util.TransformValue;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public  static  TransformValue cTrans =  TransformValue.getInstance();


    private int nMyGender;


    // 메인 페이지
    private  Main cMain;
    private  Choice cChoice;
    private  Connect cConnect;
    private  Matching cMatching;

    // 마이 페이지


    // 왼쪽 네비게이션
    private LinearLayout nav_layout;
    private ImageView nav_header_id_Image;
    private  TextView nav_header_id_text;
    private Button nav_header_btn_editProfile;


    public static UserData_Group m_UserGroup = UserData_Group.getInstance();

    public static MyData stMyData = MyData.getInstance();
    public static FavoriteData_Group stFavorite = FavoriteData_Group.getInstance();
    private HoDoDefine cDef = HoDoDefine.getInstance();
    static int cnt;

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

        Intent intent = getIntent();
        nMyGender = intent.getIntExtra("MyGender", 0);

        // 오른쪽 밑에 우편 모양
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Chat_UserList_Acitiviy.class));

                /*Context context = getApplicationContext();
                stMyData.setHeart(stMyData.getHeart() + 100);
                Toast toast = Toast.makeText(context, "현재 보유 하트 " + stMyData.getHeart(), Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cnt = cDef.getDownloadCnt();
        //stUserData[] = new UserData[cnt];

        Log.d("!!!!!", "firebase start----");
        InitProfile_firebase();

        NavigationView naviView = (NavigationView)findViewById(R.id.nav_view);
        naviView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        nav_layout = (LinearLayout) nav_header_view.findViewById(R.id.nav_layout);
        nav_header_id_Image = (ImageView) nav_header_view.findViewById(R.id.myProfile);
        nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.myname);
        nav_header_btn_editProfile = (Button) nav_header_view.findViewById(R.id.EditProfile);

        nav_header_btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });

        Log.d("!!!!!", "firebase End----");
    }

    public  void InitData_firebase()
    {


        DatabaseReference ref;
        if(stMyData.getGender() == 0)
            ref = FirebaseDatabase.getInstance().getReference().child("Account").child("MAN");
        else
            ref = FirebaseDatabase.getInstance().getReference().child("Account").child("WOMAN");

        //ref.limitToFirst(8).addListenerForSingleValueEvent(
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                            RecvData stRecvData = new RecvData ();
                            stRecvData = fileSnapshot.getValue(RecvData.class);
                            if(stRecvData != null) {

                                 UserData stUserData = new UserData();
                                stUserData.SetData(stRecvData.Email, stRecvData.Token, stRecvData.Img, stRecvData.Gender, stRecvData.NickName, stRecvData.Age, stRecvData.Blood,
                                        stRecvData.Location, stRecvData.Religion, stRecvData.Job, stRecvData.Body, stRecvData.RecvHeart, stRecvData.RecvInter);

                                m_UserGroup.arrUsers.add(stUserData);
                            }
                        }
                        initTab();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        Toast toast = Toast.makeText(getApplicationContext(), "유져 데이터 cancelled", Toast.LENGTH_SHORT);
                    }
                });
    }

    private  void initTab()
    {

        cMain = Main.newInstance();
        cChoice = Choice.newInstance();
        cConnect = Connect.newInstance();
        cMatching = Matching.newInstance();

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

                        startActivity(new Intent(getApplicationContext(), Chat_UserList_Acitiviy.class));
                        /*getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_activity_main_container, cConnect).commit();*/
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

        DatabaseReference ref;
        if(nMyGender == 0)
            ref = FirebaseDatabase.getInstance().getReference().child("Account").child("WOMAN");
        else
            ref = FirebaseDatabase.getInstance().getReference().child("Account").child("MAN");

        DatabaseReference ref1, ref2;
        ref1 = FirebaseDatabase.getInstance().getReference().child("Account").child("WOMAN");
        ref2 = FirebaseDatabase.getInstance().getReference().child("Account").child("MAN");
 /*       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1, ref2;
        ref1 = FirebaseDatabase.getInstance().getReference().child("Account").child("WOMAN");*/

        //ref.child(MyID).addListenerForSingleValueEvent(new ValueEventListener() {
        ref1.child(MyID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RecvData stRecvData = new RecvData();
                stRecvData = dataSnapshot.getValue(RecvData.class);
                if (stRecvData != null) {
                    stMyData.SetData(stRecvData.Email, stRecvData.Token, stRecvData.Img, stRecvData.Gender, stRecvData.NickName, stRecvData.Heart, stRecvData.Age, stRecvData.Blood,
                            stRecvData.Location, stRecvData.Religion, stRecvData.Job, stRecvData.Body, stRecvData.SendHeart, stRecvData.RecvHeart,stRecvData.SendInter,stRecvData.RecvInter);

                    nav_header_id_text.setText(stMyData.getNickName() );


                    Picasso.with(getApplicationContext())
                            .load(stRecvData.Img)
                            .into( nav_header_id_Image);

//                    nav_layout.setBackground();


                    stMyData.GetHeartRoomList(stMyData.getGender(), stMyData.getEmail());
                    stMyData.GetChatRoomList(stMyData.getGender(), stMyData.getEmail());

                    InitData_firebase();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "마이 데이터 cancelled", Toast.LENGTH_SHORT);
            }
        });

        ref2.child(MyID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RecvData stRecvData = new RecvData ();
                stRecvData = dataSnapshot.getValue(RecvData.class);
                if(stRecvData != null) {
                    stMyData.SetData(stRecvData.Email, stRecvData.Token, stRecvData.Img, stRecvData.Gender, stRecvData.NickName, stRecvData.Heart, stRecvData.Age, stRecvData.Blood,
                            stRecvData.Location, stRecvData.Religion, stRecvData.Job, stRecvData.Body, stRecvData.SendHeart, stRecvData.RecvHeart,stRecvData.SendInter,stRecvData.RecvInter);

                    nav_header_id_text.setText(stMyData.getNickName() + "\n" + stMyData.getEmail());

                    stMyData.GetHeartRoomList(stMyData.getGender(), stMyData.getEmail());
                    stMyData.GetChatRoomList(stMyData.getGender(), stMyData.getEmail());

                    stFavorite.GetSendHeartData(stRecvData.SendHeart);
                    stFavorite.GetRecvHeartData(stRecvData.RecvHeart);
                    stFavorite.GetSendInterData(stRecvData.SendInter);
                    stFavorite.GetRecvInterData(stRecvData.RecvInter);
                    InitData_firebase();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public int GetMyHeart()
    {
        return  stMyData.getHeart();
    }

    public void SetMyHeart(int _Heart)
    {

        stMyData.setHeart(_Heart);
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
            Intent intent = new Intent(MainActivity.this, MyPage_CardList.class);
            startActivity(intent);
        } else if (id == R.id.nav_heartCharge) {
            Intent intent = new Intent(MainActivity.this, PurchaseHeart.class);
            startActivity(intent);
        } else if (id == R.id.nav_Setting) {

        } else if (id == R.id.nav_FAQ) {

        } else if (id == R.id.nav_QA) {

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
