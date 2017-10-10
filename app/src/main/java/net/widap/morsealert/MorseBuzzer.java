package net.widap.morsealert;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.provider.Settings;

/*
CODE:
. : dot
- : dash
  (space): space between letters
/ : space between words
| : invalid character or other error
 */

class MorseBuzzer {
    private Vibrator vibrator;
    private String text;

    private int dotTime = 200;
    private int dashTime = dotTime * 3;
    private int smallSpaceTime = dotTime;
    private int letterSpaceTime = dashTime;
    private int wordSpaceTime = dotTime * 7;
    private int extraLongTime = 1000;

    MorseBuzzer(String text, Service service) {
        this.text = text;
        vibrator = (Vibrator) service.getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            // Whats a thread gotta do to get some sleep?
        }
    }

    private void buzzFor(int time) {
        vibrator.vibrate(dotTime);
        sleepFor(time);
    }

    private String getCodeFor(char c) {
        switch (c) {
            case 'a': return ".-";
            case 'b': return "-...";
            case 'c': return "-.-.";
            case 'd': return "-..";
            case ' ': return "/";
            default: return "|";
        }
    }

    private String getCodeFor(String text) {
        text = text.toLowerCase();
        String out = "";
        for (int i = 0; i < text.length(); i++) {
            if (i > 0) {
                out += " ";
            }
            char c = text.charAt(i);
            out += getCodeFor(c);
        }
        return out;
    }

    private void buzzCode(char c) {
        switch (c) {
            case '.': buzzFor(dotTime);
            case '-': buzzFor(dashTime);
            case '|': buzzFor(extraLongTime);
            default: vibrator.vibrate(extraLongTime);
        }
    }

    private void buzzCode(String code) {
        for (int i = 0; i < text.length(); i++){
            if (i != 0) {
                sleepFor(smallSpaceTime);
            }
            char c = text.charAt(i);
            buzzCode(c);
        }
    }

    void go() {
        System.out.println("buzzing text: " + text);
        String code = getCodeFor(text);
        System.out.println("buzzing code: " + code);
        buzzCode(code);
    }
}
