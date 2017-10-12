package net.widap.morsealert;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("NotificationService created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification data) {
        //System.out.println("buzzing text: " + text + " from " + data.getPackageName())
        CharSequence tickerTextChars = data.getNotification().tickerText;
        String tickerText = "";
        if (tickerTextChars != null) {
            tickerText = tickerTextChars.toString();
        }
        String text = MessageTransform.transformMessage(tickerText, data.getPackageName(), this);
        if (text != null) {
            new MorseBuzzer(text, this).go();
        } else {
            System.out.println("got null for notification from " + data.getPackageName());
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification data) {}
}