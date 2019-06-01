package com.rja.login;

import android.content.Context;

import androidx.annotation.Nullable;

/**
 * Created by rjaylward on 2019-06-01
 */
public class Preferences {
    private static final String NAME = "login-Prefs";
    private static final String USERNAME_KEY = "username";
public static void setUsername(Context context, @Nullable String username){
    context.getSharedPreferences(NAME,Context.MODE_PRIVATE)
    .edit()
            .putString(USERNAME_KEY,username)
            .apply();
}
public static String getUsernames(Context context){
   return  context.getSharedPreferences(NAME,Context.MODE_PRIVATE)
            .getString(USERNAME_KEY,null);
}

}
