package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.MultiAutoCompleteTextView;

public class SessionManager {

    private static final String PREF_NAME="SessionPREFS";
    private static final String KEY_USERNAME="username";
    private static final String KEY_LAST_LOGIN_TIME="lastLogin";

    public static void loginUser(Context context, String username){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USERNAME,username);
        editor.putLong(KEY_LAST_LOGIN_TIME,System.currentTimeMillis());
        editor.apply();
    }
    public static boolean userLogged(Context context){
        SharedPreferences preferences= context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String username=preferences.getString(KEY_USERNAME,null);
        long lastLogin=preferences.getLong(KEY_LAST_LOGIN_TIME,0);
        long sessionTimeOut=getSessionTimeout();
        return  username !=null &&(System.currentTimeMillis()-lastLogin)<sessionTimeOut;
    }
    private static long getSessionTimeout(){
        return 86400*1000;
    }
    public  static void registerUser(Context context, String username){

        loginUser(context,username);
    }
    public static void logOutUser(Context context){
        SharedPreferences.Editor editor=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE).edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_LAST_LOGIN_TIME);
        editor.apply();
    }

    public static String getLoggedUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, null);
    }
}
