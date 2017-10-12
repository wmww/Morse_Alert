package net.widap.morsealert;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainSettings extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);
        prefs = getSharedPreferences("morse_alert_main", MODE_PRIVATE);

        Switch globalToggle = (Switch) findViewById(R.id.global_toggle);
        globalToggle.setChecked(prefs.getBoolean("global_toggle", true));
        globalToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("global_toggle", isChecked);
                editor.apply();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
