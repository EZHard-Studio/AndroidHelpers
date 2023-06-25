package ezhard.studio.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import java.util.HashMap;

public class SharedPreferencesHelper {
    private Context context;
    private String prefType;

    public static HashMap<String, SharedPreferencesHelper> instances = new HashMap<>();
    
    public SharedPreferencesHelper(Context context, String prefType, boolean keepInstance) {
        this.context = context;
        this.prefType = prefType;
        if(keepInstance)
            instances.put(prefType,this);
    }


    public void setInt(String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void setString(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public void setLong(String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void setFloat(String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key, float defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
