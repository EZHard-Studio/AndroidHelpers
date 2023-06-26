package ezhard.studio.helpers;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.HashMap;

/**
 * Helper class for managing notifications.
 */
public class NotificationHelper {
    private Context context;
    private String channelId;
    public static final HashMap<String,NotificationHelper> instances = new HashMap<>();

    public enum ChannelImportance{
        DEFAULT(    NotificationManager.IMPORTANCE_DEFAULT),
        MIN(        NotificationManager.IMPORTANCE_MIN),
        LOW(        NotificationManager.IMPORTANCE_LOW),
        HIGH(       NotificationManager.IMPORTANCE_HIGH),
        MAX(        NotificationManager.IMPORTANCE_MAX),
        NONE(        NotificationManager.IMPORTANCE_NONE),
        UNSPECIFIED(        NotificationManager.IMPORTANCE_UNSPECIFIED);

        private int importance;


        ChannelImportance(int importance) {
            this.importance = importance;
        }

        public int getImportance() {
            return importance;
        }
    }
    /**
     * Constructs a NotificationHelper object.
     *
     * @param context            The context.
     * @param channelId          The ID of the notification channel.
     * @param channelName        The name of the notification channel.
     * @param channelDescription The description of the notification channel.
     *
     * @instances static map that holds evey constructed NotificationHelper object. you can accsess each instance by using  instances.get(CHANNEL_ID)
     */
    public NotificationHelper(Context context, String channelId, String channelName, String channelDescription, ChannelImportance importance) {
        this.context = context;
        this.channelId = channelId;
        createNotificationChannel(channelId, channelName, channelDescription, importance);
        instances.put(channelId,this);
    }
    @SuppressLint("WrongConstant") //enum ensures the channelImportance is as needed.
    private void createNotificationChannel(String channelId, String channelName, String channelDescription, ChannelImportance importance) {
        int channelImportance = importance.getImportance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

            // Check if the channel already exists
            NotificationChannel existingChannel = notificationManager.getNotificationChannel(channelId);
            if (existingChannel != null) {
                // Update the channel name and description if it exists
                existingChannel.setName(channelName);
                existingChannel.setDescription(channelDescription);
                existingChannel.setImportance(channelImportance);
                notificationManager.createNotificationChannel(existingChannel);
            } else {
                // Create a new channel if it doesn't exist
                NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance);
                channel.setDescription(channelDescription);
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    public enum NotificationPriority{
        DEFAULT(NotificationCompat.PRIORITY_DEFAULT),
        MIN(NotificationCompat.PRIORITY_MIN),
        LOW(NotificationCompat.PRIORITY_LOW),
        HIGH(NotificationCompat.PRIORITY_HIGH),
        MAX(NotificationCompat.PRIORITY_MAX);

        private int priority;
        NotificationPriority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
    /**
     * Displays a notification.
     * 
     * @param icon           The icon resource ID of the notification.
     * @param title           The title content of the notification.
     * @param body           The body content of the notification.
     * @param notificationId The ID of the notification.
     * @param priority       The priority of the notification.
     */
    public void showNotification(int icon, String title,String body, int notificationId, NotificationPriority priority) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(icon)
                .setContentTitle("title")
                .setContentText(body)
                .setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE)
                .setPriority(priority.getPriority())
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }

    /**
     * Checks if a notification channel with the given ID exists.
     *
     * @param context    The context.
     * @param channelId  The ID of the notification channel to check.
     * @return True if the notification channel exists, false otherwise.
     */
    public static boolean isChannelIdExists(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            NotificationChannel existingChannel = notificationManager.getNotificationChannel(channelId);
            return existingChannel != null;
        }
        return false;
    }

    /**
     * Cancels a notification with the given ID.
     *
     * @param context          The context.
     * @param notificationId   The ID of the notification to cancel.
     */
    public static void cancelNotification(Context context, int notificationId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(notificationId);
    }

    /**
     * Deletes a notification channel with the given ID.
     *
     * @param context    The context.
     * @param channelId  The ID of the notification channel to delete.
     */
    public static void deleteNotificationChannel(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.deleteNotificationChannel(channelId);
        }
    }
    public static void requestNotificationPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }

    }
}

