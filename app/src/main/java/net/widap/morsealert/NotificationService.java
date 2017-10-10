package net.widap.morsealert;

import android.content.Context;
import android.os.Parcel;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {
    //private NotificationReceiver reciever;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("NotificationService created");
        //reciever = new NotificationReceiver();
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(getApplicationContext().getPackageName() + ".NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
        //registerReceiver(reciever, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(reciever);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification data) {

        System.out.println("notification text: " + data.getNotification().tickerText);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        // VibrationEffect effect = VibrationEffect.createOneShot(500, 255);
        v.vibrate(1000);
        // data.getId()
        // data.getNotification().tickerText
        // data.getPackageName());
        // Intent intent = new  Intent(getApplicationContext().getPackageName() + ".NOTIFICATION_LISTENER_EXAMPLE");
        // intent.putExtra("notification_event","notification from " + data.getPackageName() + "\n");
        // sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification data) {
        //Intent intent = new  Intent(getApplicationContext().getPackageName() + ".NOTIFICATION_LISTENER_EXAMPLE");
        //intent.putExtra("notification_event","notification removed " + data.getPackageName() + "\n");
        //sendBroadcast(intent);
    }

    /*
    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("command").equals("clearall")){
                NotificationService.this.cancelAllNotifications();
            }
            else if(intent.getStringExtra("command").equals("list")){
                Intent i1 = new  Intent(getApplicationContext().getPackageName() + ".NOTIFICATION_LISTENER_EXAMPLE");
                i1.putExtra("notification_event","=====================");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification notification : NotificationService.this.getActiveNotifications()) {
                    Intent intent2 = new  Intent(getApplicationContext().getPackageName() +".NOTIFICATION_LISTENER_EXAMPLE");
                    intent2.putExtra("notification_event",i +" " + notification.getPackageName() + "\n");
                    sendBroadcast(intent2);
                    i++;
                }
                Intent i3 = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
                i3.putExtra("notification_event","===== Notification List ====");
                sendBroadcast(i3);

            }

        }
    }
    */
}