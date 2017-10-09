package com.example.erunn.yalomovieplayer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by erunn on 2017-10-09.
 */

public class SharedPrefUtil {
    public static final String APP_SHARED_PREFS = "thisApp.SharedPreference";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setSharedTest(String test) {
        editor.putString("language", test);
        editor.commit();
    }

    public String getSharedTest() {
        return sharedPreferences.getString("language", "none"); // "test"는 키, "defValue"는 키에 대한 값이 없을 경우 리턴해줄 값 }


    }
}
