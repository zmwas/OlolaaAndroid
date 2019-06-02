package com.ololaa.ololaa.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ololaa.ololaa.Constants;
import com.ololaa.ololaa.R;
import com.ololaa.ololaa.common.models.NotificationInfo;

import java.util.Map;

import javax.inject.Inject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Inject
    SharedPrefsWrapper wrapper;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        sendRegistrationToServer(s);

    }

    private void sendRegistrationToServer(String s) {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
        String role = wrapper.getString("role");
        Map<String, String> messageData = remoteMessage.getData();
        NotificationInfo notificationInfo = new NotificationInfo(messageData.get(remoteMessage.getNotification().getBody()));
        notifyMainActivity(notificationInfo);

        if (role.equals("cargoMover")) {
            //updatePrice
            notifyMainActivity(notificationInfo);
        }
    }


    private void notifyMainActivity(NotificationInfo notificationInfo) {
        Intent intent = new Intent();
        intent.setAction(Constants.BROADCAST_MESSAGE_NOTIFICATION_RECEIVED);
        intent.putExtra(Constants.PARAM_NOTIFICATION_INFO, notificationInfo);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
