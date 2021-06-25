package com.example.homework1android2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    public static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init( Context cntxt ){
        context = cntxt;
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void addProperty( String name, String value ){
        if( settings == null ){
            init();
        }
        editor.putString( name, value );
        editor.commit();
    }

    public static String getProperty( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }
     public static void setShowOnBoard (){
        if (settings == null){
            init();
        }
        editor.putBoolean("onboard", true);
        editor.apply();
     }
     public  static  boolean getShowBoard (){
        if (settings == null){
            init();
        }
        return settings.getBoolean("onboard", false);
     }
}
