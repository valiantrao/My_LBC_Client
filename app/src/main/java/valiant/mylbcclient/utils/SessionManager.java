package valiant.mylbcclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

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

    public void setLAuth(String key, String secret, String username, String url, String created_at, String identity_verified_at){
        editor.putString(LBC_KEY, key);
        editor.putString(LBC_SECRET, secret);
        editor.putString(Config.username, username);
        editor.putString(Config.url, url);
        editor.putString(Config.created_at, created_at);
        editor.putString(Config.identity_verified_at, identity_verified_at);
        editor.apply();
    }

    public String getLbcKey(){
        return pref.getString(LBC_KEY,null);
    }

    public String getLbcSecret(){
        return pref.getString(LBC_SECRET,null);
    }

    public String getUsername(){
        return pref.getString(Config.username,null);
    }
    public String getUrl(){
        return pref.getString(Config.url,null);
    }
    public String getCreated_at(){
        return pref.getString(Config.created_at,null);
    }
    public String getIdentity_verified_at(){
        return pref.getString(Config.identity_verified_at,null);
    }
}