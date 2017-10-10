package net.widap.morsealert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainSettings extends AppCompatActivity {
    //private NotificationReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);
        //receiver = new NotificationReceiver();
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(getApplicationContext().getPackageName() + ".NOTIFICATION_LISTENER_EXAMPLE");
        //registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    /*
    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("notification: " + intent.getStringExtra("notification_data"));
        }
    }
    */
}
