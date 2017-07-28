package hodo.hodotalk.MainPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.R;
import hodo.hodotalk.Service.PurchaseHeart;
import hodo.hodotalk.ViewProfile;
import hodo.hodotalk.Util.HoDoDefine;
import hodo.hodotalk.Util.ItemClickSupport;


/**
 * Created by boram on 2017-07-12.
 */

public class Main extends Fragment {

  //  UserData_Profile users[] = new UserData_Profile[8];

    public  RecyclerView.LayoutManager mLayoutManager;
    public  RecyclerView.Adapter mAdapter;
    public Button btn_fragment;

    //private MainActivity MV = new MainActivity();

    public MyData stMyData = MyData.getInstance();
    public UserData stTargetData = new UserData();
    private HoDoDefine cDef = HoDoDefine.getInstance();


    public MainPage_Adapter mMainAdapter;
    private  RecyclerView recyclerView;

    private int nAddView = 0;

    private GestureDetector gestureDetector;

    public static Main newInstance() {
        Main fragment = new Main();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mainpage_main, container, false);

        mMainAdapter = new MainPage_Adapter();

        btn_fragment = (Button)rootView.findViewById(R.id.button2);
        btn_fragment.setText("카드 더 보기");

        recyclerView = (RecyclerView)rootView.findViewById(R.id.mainpage_cardview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new MainPage_Adapter());

        btn_fragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nAddView++;

                boolean bCheck = mMainAdapter.DelDataList(nAddView);

                if(bCheck ==true) {
                    mMainAdapter.AddData(nAddView);
                    RefreshItem();
                }
                else
                    nAddView--;

            }
        });

        Log.d("!!!!!", "App End----");

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(Main.this, "클릭한 아이템의 이름은 ", Toast.LENGTH_SHORT).show();
                Log.d("@@@@@", "!!!!!!");
                stTargetData = mMainAdapter.SelectUser(position + nAddView *4);
                final String SendNick = stTargetData.getNickName();
                if(SendNick != null)
                {
                    AlertDialog.Builder newdlg = new AlertDialog.Builder(v.getContext());

                    if(stMyData.getHeart() >= cDef.getHeartCost())
                    {
                        newdlg.setTitle(SendNick + "님의 프로필을 열어볼까요?");
                        newdlg.setMessage("하트 5개가 사용됩니다").setCancelable(false);
                        newdlg.setNegativeButton("사용하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                    stMyData.setHeart(stMyData.getHeart() - cDef.getHeartCost());
                                    ViewProfilePage();
                            }
                        });
                        newdlg.setNeutralButton("하트구매", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ViewPurchaseHeart();
                            }
                        });
                        newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int asdadsad=0;
                            }
                        });
                    }

                    else
                    {
                        newdlg.setTitle("하트가 부족합니다");
                        newdlg.setMessage("상대방의 프로필을 보시려면" +"\n" + "하트 5개가 필요합니다" + "\n" + "하트 충전 화면으로 이동하시겠습니까").setCancelable(false);
                        newdlg.setNegativeButton("충전하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ViewPurchaseHeart();
                            }
                        });
                        newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int asdadsad=0;
                            }
                        });
                    }

                    newdlg.show();
                }
            }


        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
               // Toast.makeText(Main.this, "길게 눌렀구나 ", Toast.LENGTH_SHORT).show();
                Log.d("@@@@@", "$$$$$$");
                return true;
            }
        });

        return rootView;
    }


    public void ViewProfilePage()
    {
        Intent intent = new Intent(getActivity(), ViewProfile.class);
        intent.putExtra("Target", stTargetData);
        startActivity(intent);
    }

    public void ViewPurchaseHeart()
    {
        Intent intent = new Intent(getActivity(), PurchaseHeart.class);
        startActivity(intent);
    }

    public void SendHeart_Dialog()
    {
     /*   DialogFragment util_dialog = new Util_DialogFragment();
        util_dialog.setTargetFragment(Main.this, 0);
        util_dialog.show(getFragmentManager(), "sssssss");*/
    }

    public void RefreshItem()
    {
        recyclerView.removeAllViewsInLayout();
        recyclerView.setAdapter(mMainAdapter);

    }
}

