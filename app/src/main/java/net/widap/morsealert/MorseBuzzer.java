package net.widap.morsealert;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.os.VibrationEffect;

public class MorseBuzzer {
    Vibrator vibrator;
    String text;
    public MorseBuzzer(String text, Service service) {
        this.text = text;
        vibrator = (Vibrator) service.getSystemService(Context.VIBRATOR_SERVICE);
    }

    void buzzLetter(char c) {
        vibrator.vibrate(1000);
    }

    void go() {
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            buzzLetter(c);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                // Whats a thread gotta do to get some sleep?
            }
        }
    }
}
