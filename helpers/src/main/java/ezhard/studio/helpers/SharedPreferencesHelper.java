package ezhard.studio.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;

public class SharedPreferencesHelper {
    private Context context;
    private String prefType;
    private Gson gson;
    public static HashMap<String, SharedPreferencesHelper> instances = new HashMap<>();
    /**
     * Constructor for SharedPreferencesHelper.
     *
     * @param context  The context used to create SharedPreferences.
     * @param prefType The type or name of the SharedPreferences.
     * @param keepInstance If true, the SharedPreferencesHelper instance is kept in the static map 'instances',  just use the 'prefType' as key to get the instance
     */
    public SharedPreferencesHelper(Context context, String prefType, boolean keepInstance) {
        this.context = context;
        this.prefType = prefType;
        this.gson = new Gson();
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

    /**
     * Sets the object of type T in SharedPreferences with the given key.
     *
     * @param key   The key to identify the object in SharedPreferences.
     * @param value The object of type T to be stored in SharedPreferences.
     * @param <T>   The type of the object.
     * @throws SharedPreferencesHelperException If there is an error while setting the object in SharedPreferences.
     */
    public <T> void setObject(String key, T value) throws SharedPreferencesHelperException {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String json = gson.toJson(value);
            editor.putString(key, json);
            editor.apply();
        } catch (Exception e) {
            throw new SharedPreferencesHelperException("Failed to set object of type " + value.getClass().getSimpleName() + " in SharedPreferences", e);
        }
    }

    /**
     * Retrieves the object of type T from SharedPreferences with the given key.
     *
     * @param key          The key to retrieve the object from SharedPreferences.
     * @param typeOfT      The Type object representing the type T.
     * @param defaultValue The default value to return if the object is not found in SharedPreferences.
     * @param <T>          The type of the object.
     * @return The retrieved object of type T, or the defaultValue if not found.
     * @throws SharedPreferencesHelperException If there is an error while getting the object from SharedPreferences.
     */
    public <T> T getObject(String key, Type typeOfT, T defaultValue) throws SharedPreferencesHelperException {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(prefType, Context.MODE_PRIVATE);
            String json = sharedPreferences.getString(key, null);
            if (json != null) {
                return gson.fromJson(json, typeOfT);
            }
            return defaultValue;
        } catch (Exception e) {
            throw new SharedPreferencesHelperException("Failed to get object of type " + defaultValue.getClass().getSimpleName() + " from SharedPreferences", e);
        }
    }

    /**
     * Custom exception class for handling exceptions in SharedPreferencesHelper.
     */
    public static class SharedPreferencesHelperException extends Exception {
        public SharedPreferencesHelperException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
