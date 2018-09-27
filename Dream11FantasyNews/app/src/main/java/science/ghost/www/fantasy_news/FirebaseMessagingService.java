package science.ghost.www.fantasy_news;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    public FirebaseMessagingService(){

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("Mesagge","Received");
        Map<String, String> dataa = remoteMessage.getData();
        Integer match_id = 0;
        Integer type = 0;

        if (remoteMessage.getData().size() > 0) {
            JSONObject data = new JSONObject(remoteMessage.getData());
        }


            String title = dataa.get("title");
            String message = dataa.get("body");
            String clickaction = dataa.get("click_action");

            match_id =Integer.parseInt(dataa.get("mid"));
            type =Integer.parseInt(dataa.get("type"));


            Log.i("EEEEEEEEEEEE",""+match_id);
            sendNotification(title,message,clickaction,match_id,type);



    }

    @Override
    public void onDeletedMessages() {

    }

    private void sendNotification(String title,String message,String clickaction,Integer mid,Integer type) {

        Intent intent;
        if(clickaction.equals("SOMEACTIVITY")){
            intent = new Intent(this,singlematch.class).putExtra("id",mid).putExtra("type",type);
            Log.i("cheating error",""+mid);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }else if(clickaction.equals("MAINACTIVITY")){
            intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }else{
            intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.not)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
