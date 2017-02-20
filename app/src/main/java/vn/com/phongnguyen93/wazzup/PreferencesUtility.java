package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

/**
 * Created by tuanhnm on 9/16/16.
 */
public class PreferencesUtility {
    public static final String PRE_KEY_GENRE_LIST = "genre_list";

    public static final String PRE_KEY = "WazzUp";
    private static volatile PreferencesUtility instance;
    private final Context mContext;
    private final SharedPreferences mPreferences;

    public static PreferencesUtility getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferencesUtility.class) {
                if (instance == null) {
                    instance = new PreferencesUtility(context);
                }
            }
        }
        return instance;
    }

    public PreferencesUtility(Context context)  {
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences(PRE_KEY, Context.MODE_PRIVATE);
    }

    public SharedPreferences getmPreferences() {
        return mPreferences;
    }

    public void setObjectValue(String key, Object value) {
        if (key == "" || value == "") {
            return;
        }
        mPreferences.edit().putString(
            key,
            new Gson().toJson(value)
        ).apply();
    }

    public void deleteObjectValues(String... keys) {
        if (keys == null || keys.length < 1) {
            return;
        }
        for (String key: keys) {
            mPreferences.edit().putString(
                    key,
                    null
            ).apply();
        }
    }

    public Object getObjectValue(String key, Class objectClass) {
        return new Gson().fromJson(
            mPreferences.getString(key, ""),
            objectClass
        );
    }

    public void clean() {
        mPreferences.edit().clear().apply();
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public void setString(String key, String values) {
        if (key == "" || values == "") {
            return;
        }
        mPreferences.edit().putString(
            key,
            values
        ).apply();
    }
}
