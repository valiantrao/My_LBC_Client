package valiant.mylbcclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.utils.Config;
import valiant.mylbcclient.utils.SessionManager;

public class AuthActivity extends AppCompatActivity {

    private AppCompatEditText enter_key, enter_secret;
    private AppCompatButton authanticate;
    private AVLoadingIndicatorView avi;
    private Python python;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        sessionManager = new SessionManager(this);

        avi = findViewById(R.id.avi);
        enter_key = findViewById(R.id.enter_key);
        enter_secret = findViewById(R.id.enter_secret);

        authanticate = findViewById(R.id.authanticate);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        python = Python.getInstance();

        authanticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(enter_key.getText()) || !TextUtils.isEmpty(enter_secret.getText())){
                    loadData(enter_key.getText().toString(), enter_secret.getText().toString());
                } else {
                    Toast.makeText(AuthActivity.this, "Enter LBC key and secret please!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData(String key, String secret){
        authanticate.setVisibility(View.GONE);
        avi.smoothToShow();
        Needle.onBackgroundThread().execute(new UiRelatedTask<String>() {

            @Override
            protected String doWork() {
                return python.getModule(Config.LBC_API).callAttr("Auth", Config.LBC_KEY, Config.LBC_SECRET).toString();
            }

            @Override
            protected void thenDoUiRelatedWork(String result) {
                avi.smoothToHide();
                authanticate.setVisibility(View.VISIBLE);

                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject data = object.getJSONObject("data");
                    String username = data.getString(Config.username);
                    String url = data.getString(Config.url);
                    String created_at = data.getString(Config.created_at);
                    String identity_verified_at = data.getString(Config.identity_verified_at);

                    sessionManager.setLAuth(Config.LBC_KEY, Config.LBC_SECRET, username, url, created_at, identity_verified_at);
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                } catch (JSONException e) {
                    Log.e("Json Error:", String.valueOf(e));
                }
            }
        });
    }
}