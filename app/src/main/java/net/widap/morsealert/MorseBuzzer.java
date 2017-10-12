package net.widap.morsealert;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
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
    private int dashTime = dotTime * 4;
    private int smallSpaceTime = dotTime;
    private int letterSpaceTime = dotTime * 6;
    private int wordSpaceTime = dotTime * 12;
    private int extraLongTime = 1000;
    private AudioManager audioManager;

    MorseBuzzer(String text, Service service) {
        this.text = text;
        vibrator = (Vibrator) service.getSystemService(Context.VIBRATOR_SERVICE);
        audioManager = (AudioManager) service.getSystemService(Context.AUDIO_SERVICE);
    }

    private void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            // Whats a thread gotta do to get some sleep?
        }
    }

    private void buzzFor(int time) {
        vibrator.vibrate(time);
        sleepFor(time);
    }

    private String getCodeFor(char c) {
        switch (c) {
            case ' ': return "/";
            case '.': return "";
            case ',': return "";
            case 'a': return ".-";
            case 'b': return "-...";
            case 'c': return "-.-.";
            case 'd': return "-..";
            case 'e': return ".";
            case 'f': return "..-.";
            case 'g': return "--.";
            case 'h': return "....";
            case 'i': return "..";
            case 'j': return ".---";
            case 'k': return "-.-";
            case 'l': return ".-..";
            case 'm': return "--";
            case 'n': return "-.";
            case 'o': return "---";
            case 'p': return ".--.";
            case 'q': return "--.-";
            case 'r': return ".-.";
            case 's': return "...";
            case 't': return "-";
            case 'u': return "..-";
            case 'v': return "...-";
            case 'w': return ".--";
            case 'x': return "-..-";
            case 'y': return "-.--";
            case 'z': return "--..";
            case '0': return "-----";
            case '1': return ".----";
            case '2': return "..---";
            case '3': return "...--";
            case '4': return "....-";
            case '5': return ".....";
            case '6': return "-....";
            case '7': return "--...";
            case '8': return "---..";
            case '9': return "----.";
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
            case '.': buzzFor(dotTime); break;
            case '-': buzzFor(dashTime); break;
            case ' ': sleepFor(smallSpaceTime); break;
            case '/': sleepFor(wordSpaceTime); break;
            case '|': buzzFor(extraLongTime); break;
            default: buzzFor(extraLongTime); break;
        }
    }

    private void buzzCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                System.out.println("canceling message");
                break;
            }
            if (i != 0) {
                sleepFor(smallSpaceTime);
            }
            char c = code.charAt(i);
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
