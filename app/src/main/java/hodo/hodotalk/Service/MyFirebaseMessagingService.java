package hodo.hodotalk.Service;


import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import hodo.hodotalk.Data.FavoriteData_Group;
import hodo.hodotalk.Data.MyData;
import hodo.hodotalk.Data.RecvData;
import hodo.hodotalk.Data.RecvHeart;
import hodo.hodotalk.LoginActivity;
import hodo.hodotalk.MainActivity;
import hodo.hodotalk.R;

/**
 * Created by boram on 2017-07-19.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final static String TAG = "FCM_MESSAGE";

    private FavoriteData_Group m_CardList = FavoriteData_Group.getInstance();
    private MyData m_MyData = MyData.getInstance();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            String body = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Notification Body: " + body);

            //Map<String, String> data = remoteMessage.getData();



            //SetTargetData(0, data.get("Email"), data.get("Img"), data.get("NickName"));

            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            }

            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            }

                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder;

            if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
               notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                        .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                        .setContentText(body); // Firebase Console 에서 사용자가 전달한 메시지내용
            }

            else
            {
              notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                        .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                        .setContentText(body) // Firebase Console 에서 사용자가 전달한 메시지내용
                        .setContentIntent(pending);
            }

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(0x1001, notificationBuilder.build());

/*            Intent intent = new Intent(Intent.ACTION_VIEW);*/



            //Intent intent = new Intent(this, LoginActivity.class);
            //startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


        }
    }


    /*public  void SetTargetData(int _idx, String Email,  String _Img, String _NickName)
    {

        String idxStr = null;
        switch (_idx)
        {
            case 0:
            {
                idxStr = "/RecvHeart/";
                break;
            }
            case 1:
            {
                idxStr = "/RecvInter/";
                break;
            }
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table;

        String tempStr = m_MyData.getEmail();
        int idx = tempStr.indexOf("@");
        String tempStr2 =  tempStr.substring(0, idx);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String str_date = df.format(new Date());

        if(m_MyData.getGender() == 0)
            table = database.getReference("CardList/WOMAN/" + tempStr2 + idxStr);
        else
            table = database.getReference("CardList/MAN/" + tempStr2 + idxStr);

        RecvHeart cRecvData = new RecvHeart();

        cRecvData.Email = Email;
        cRecvData.Img = _Img;
        cRecvData.NickName = _NickName;
        cRecvData.Date = str_date;

        DatabaseReference user = table;
        user.push().setValue(cRecvData);

        m_MyData.SetRecvHeart(m_MyData.getRecvHeart()+1);
    }*/

}