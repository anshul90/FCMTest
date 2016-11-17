package cws.com.fcmtest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by cws on 16/11/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Calling method to generate notification
        ownNotifications(remoteMessage.getNotification().getBody());
    }

    public void ownNotifications(String message) {
        //LinkedIn
        Intent intentLinkedin = new Intent(Intent.ACTION_VIEW);
        intentLinkedin.setData(Uri.parse("https://in.linkedin.com/in/proadvisorychampionship"));
        intentLinkedin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TaskStackBuilder stackBuilderLinkedIn = TaskStackBuilder.create(this);
        stackBuilderLinkedIn.addNextIntent(intentLinkedin);
        PendingIntent linkedinPendingIntent = stackBuilderLinkedIn.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //Facebook
        Intent intentFacebook = new Intent(Intent.ACTION_VIEW);
        intentFacebook.setData(Uri.parse("https://www.facebook.com/proadvisorychampionship/"));
        intentFacebook.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TaskStackBuilder stackBuilderFacebook = TaskStackBuilder.create(this);
        stackBuilderFacebook.addNextIntent(intentFacebook);
        PendingIntent facebookPendingIntent = stackBuilderFacebook.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //Twitter
        Intent intentTwitter = new Intent(Intent.ACTION_VIEW);
        intentTwitter.setData(Uri.parse("https://twitter.com/Pro_Advisory"));
        intentTwitter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TaskStackBuilder stackBuilderTwitter = TaskStackBuilder.create(this);
        stackBuilderTwitter.addNextIntent(intentTwitter);
        PendingIntent twitterPendingIntent = stackBuilderTwitter.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //Google
        Intent intentGoogle = new Intent(Intent.ACTION_VIEW);
        intentGoogle.setData(Uri.parse("https://plus.google.com/104802195230813080579"));
        intentGoogle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TaskStackBuilder stackBuilderGoogle = TaskStackBuilder.create(this);
        stackBuilderGoogle.addNextIntent(intentGoogle);
        PendingIntent googlePendingIntent = stackBuilderGoogle.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);


        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Advisory Mandi")
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher,
                        "Facebook", facebookPendingIntent)
                /*.addAction(R.mipmap.ic_launcher,
                        "Google", googlePendingIntent)
                .addAction(R.mipmap.ic_launcher,
                        "Linkedin", linkedinPendingIntent)*/
                .addAction(R.mipmap.ic_launcher,
                        "Twitter", twitterPendingIntent)
                .setContentText(message);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Advisory Mandi");
        bigTextStyle.bigText(message);
        mBuilder.setStyle(bigTextStyle);
        //mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        mNotificationManager.notify(m, mBuilder.build());
    }

}
