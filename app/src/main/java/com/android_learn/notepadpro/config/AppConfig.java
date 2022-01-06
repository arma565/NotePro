package com.android_learn.notepadpro.config;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {

    SharedPreferences sh;
    SharedPreferences.Editor editor;

    public AppConfig(Context context)
    {
        sh = context.getSharedPreferences("setting" , Context.MODE_PRIVATE);
        editor = sh.edit();
    }

    public void setLanguage(String lang)
    {
        editor.putString("language" , lang);
        editor.commit();
    }

    public String getLanguage()
    {
        return sh.getString("language" , "en");
    }
}
