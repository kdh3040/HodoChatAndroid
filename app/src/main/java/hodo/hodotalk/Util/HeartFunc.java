package hodo.hodotalk.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.UserData;
import hodo.hodotalk.R;
import hodo.hodotalk.Service.PurchaseHeart;
import hodo.hodotalk.ViewProfile;

/**
 * Created by boram on 2017-07-28.
 */


public class HeartFunc {


    private MyData m_MyData = MyData.getInstance();
    private HoDoDefine m_Def = HoDoDefine.getInstance();

    private static HeartFunc _Instance;
    public static HeartFunc getInstance()
    {
        if(_Instance == null)
            _Instance = new HeartFunc();

        return  _Instance;
    }

    private HeartFunc()
    {

    }

    public AlertDialog.Builder SendHeart(final UserData stTargetData, final Context StartContext)
    {
        AlertDialog.Builder newdlg = new AlertDialog.Builder(StartContext);

        if(m_MyData.getHeart() > 5) {
            newdlg.setTitle(stTargetData.getNickName() + "님에게 좋아요를 보낼까요?");
            newdlg.setMessage("상대방이 좋아요를 수락하면" +"\n" + "채팅을 시작할 수 있습니다" + "\n" + "(하트 5개가 사용됩니다)").setCancelable(false);
            newdlg.setNegativeButton("사용하기", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if(m_MyData.getHeart() > 5) {
                        m_MyData.setHeart(m_MyData.getHeart() - 5);
                        //stmyData.MakeHeartRoom(stmyData.getEmail(), stTargetData.getEmail(), stmyData.getNickName(), stTargetData.getNickName(), stmyData.getImg(), stTargetData.getImage());
                        if(m_MyData.MakeHeartRoomList(m_MyData.getIndex(), stTargetData.getIndex(),m_MyData.getGender())) {
                            m_MyData.MakeHeartRoom(m_MyData.getIndex(), stTargetData.getIndex(), m_MyData.getNickName(), stTargetData.getNickName(), "MyImg", "TagerIMG", m_MyData.getToken(), stTargetData.getToken());
                            SendHeartToFCM(stTargetData);
                        }
                    }
                }

            });
            newdlg.setNeutralButton("하트구매", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(StartContext, PurchaseHeart.class);
                    StartContext.startActivity(intent);
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
            newdlg.setMessage("상대방이 좋아요를 보내려면" +"\n" + "하트 5개가 필요합니다" + "\n" + "하트 충전 화면으로 이동하시겠습니까").setCancelable(false);
            newdlg.setNegativeButton("충전하기", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(StartContext, PurchaseHeart.class);
                    StartContext.startActivity(intent);
                }

            });
            newdlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    int asdadsad=0;
                }
            });
        }

        //newdlg.show();
        return  newdlg;
    }



    public void SendHeartToFCM(final UserData stTargetData) {

        try {

            // FMC 메시지 생성 start
            JSONObject root = new JSONObject();
            JSONObject notification = new JSONObject();
            JSONObject data = new JSONObject();
            //notification.put("body", stmyData.getNickName()+"님이 좋아요를 보냈습니다" + "@" + temp);
            //notification.put("body", stmyData.getNickName()+"님이 좋아요를 보냈습니다");
            notification.put("body", m_MyData.getNickName()+"님이 좋아요를 보냈습니다");
            notification.put("title", "호도톡");
            data.put("Email", stTargetData.getEmail());
            data.put("Img", stTargetData.getImage());
            data.put("NickName", m_MyData.getNickName());
            root.put("notification", notification);
            root.put("to", stTargetData.getToken());
            root.put("data", data);
            // FMC 메시지 생성 end

            URL Url = new URL(m_Def.FCM_MESSAGE_URL);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.addRequestProperty("Authorization", "key=" + m_Def.SERVER_KEY);
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


}
