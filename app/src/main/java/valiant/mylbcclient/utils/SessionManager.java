package valiant.mylbcclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;


import java.lang.reflect.Type;
import java.util.List;

public class SessionManager {

    Context context;

    SharedPreferences pref;

    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LBC_CLIENT";
    private static final String LBC_KEY = "KEY";
    private static final String LBC_SECRET = "SECRET";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void setLAuth(String key, String secret){
        editor.putString(LBC_KEY, key);
        editor.putString(LBC_SECRET, secret);
        editor.apply();
    }

    public String getLbcKey(){
        return pref.getString(LBC_KEY,null);
    }

    public String getLbcSecret(){
        return pref.getString(LBC_SECRET,null);
    }
}