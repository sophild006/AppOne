package com.wx.myproject.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wwq on 2017/3/23.
 */

public class PreferenceHelper {

    public static SharedPreferences getInstance(Context  context){
        return context.getSharedPreferences("my_sharedpreference",Context.MODE_PRIVATE);
    }

    public static long getLong(String key){
        return getInstance(GlobalContext.getContext()).getLong(key,0);
    }
    public static void setLong(String key,long value){
        getInstance(GlobalContext.getContext()).edit().putLong(key,value).commit();
    }
    public static String getString(String key){
        return getInstance(GlobalContext.getContext()).getString(key,"");
    }
    public static void setString(String key,String value){
        getInstance(GlobalContext.getContext()).edit().putString(key,value).commit();
    }

    public static int getInt(String key){
        return getInstance(GlobalContext.getContext()).getInt(key,0);
    }
    public static void setInt(String key,int value){
        getInstance(GlobalContext.getContext()).edit().putInt(key,value).commit();
    }

    public static boolean getBoolean(String key){
        return getInstance(GlobalContext.getContext()).getBoolean(key,false);
    }
    public static void setBoolean(String key,boolean value){
        getInstance(GlobalContext.getContext()).edit().putBoolean(key,value).commit();
    }

}
