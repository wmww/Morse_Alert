package net.widap.morsealert;

public class MessageTransform {
    public static String transformMessage(String body, String sourcePackage) {
        System.out.println("transforming " + body + " from " + sourcePackage);
        if (sourcePackage == "com.facebook.orca") {
            return body;
        }
        return null;
    }
}
