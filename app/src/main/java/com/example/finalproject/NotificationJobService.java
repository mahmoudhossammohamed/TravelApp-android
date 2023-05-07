package com.example.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Creates a Notification channel, for OREO and higher
    public void createNotificationChannel() {
        // Define notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Job Service notification",
                            NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Notifications from Job Service");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
    /** implement the job to be done.
     * @param jobParameters
     * @return boolean indicating whether the job needs to continue on a separate thread.
     * If true, the work is offloaded to a different thread.
     */
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //Create the notification channel
        createNotificationChannel();
        //Set up the notification content intent to launch the app when clicked
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your Ticket Successfully Booked!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);
        mNotifyManager.notify(0, builder.build());
        return false;
    }
    /*@param jobParameters
     * @return a boolean that determines what to do if the job is not finished.
     * If the return value is true, the job is rescheduled; otherwise, the job is dropped.
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
