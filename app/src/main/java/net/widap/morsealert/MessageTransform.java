package net.widap.morsealert;

class MessageTransform {
    private static String[] packageWhitelist = new String[] {
            "com.facebook.orca", // facebook messenger
            "net.dinglisch.android.taskerm", // tasker
    };

    static String transformMessage(String body, String sourcePackage) {
        System.out.println("transforming " + body + " from " + sourcePackage);
        Boolean packageGood = false;
        for (String i : packageWhitelist) {
            if (sourcePackage.equals(i)) {
                packageGood = true;
                break;
            }
        }
        if (packageGood) {
            return body;
        }
        return null;
    }
}
