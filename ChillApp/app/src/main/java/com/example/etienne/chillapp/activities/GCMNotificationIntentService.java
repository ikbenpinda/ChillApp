package com.example.etienne.chillapp.activities;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.etienne.chillapp.Config;
import com.example.etienne.chillapp.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMNotificationIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public GCMNotificationIntentService() {
		super("GcmIntentService");
	}

	public static final String TAG = "GCMNotificationIntentService";

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {

				for (int i = 0; i < 3; i++) {
					Log.i(TAG, "Working... " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}

				}
				Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

				sendNotification("An invitation has been sent by: "
						+ extras.get(Config.REGISTER_NAME) + " to do: "
						+ extras.get(Config.MESSAGE_ACTIVITY)+ " location:"
                        + extras.get(Config.MESSAGE_PLACE) + " on:"
                        + extras.get(Config.DATE) + " at:"
                        + extras.get(Config.TIME));
				Log.i(TAG, "Received: " + extras.toString());
			}
		}
        //

		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(String msg) {
		Log.d(TAG, "Preparing to send notification...: " + msg);
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        long[] vibrate = { 0, 100, 200, 300 };
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this)//.setSmallIcon(R.drawable.ic_stat_icon) //icon set
				.setContentTitle("ChillApp Notification")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg)
                .setVibrate(vibrate);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		Log.d(TAG, "Notification sent successfully.");
	}
}
