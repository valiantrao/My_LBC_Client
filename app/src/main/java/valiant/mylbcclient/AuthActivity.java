package valiant.mylbcclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.utils.Config;

public class AuthActivity extends AppCompatActivity {

    private AppCompatEditText enter_key, enter_secret;
    private AppCompatButton authanticate;
    private AVLoadingIndicatorView avi;
    private Python python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

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
                loadData();
            }
        });
    }

    private void loadData(){
        authanticate.setVisibility(View.GONE);
        avi.smoothToShow();
        Needle.onBackgroundThread().execute(new UiRelatedTask<String>() {

            @Override
            protected String doWork() {
                return python.getModule("hh").callAttr("Auth", Config.LBC_KEY, Config.LBC_SECRET).toString();
            }

            @Override
            protected void thenDoUiRelatedWork(String result) {
                avi.smoothToHide();
                authanticate.setVisibility(View.VISIBLE);

                try {
                    JSONObject data = new JSONObject(result);
                    String username = data.getString("");
                } catch (JSONException e) {
                    Log.e("Json Error:", String.valueOf(e));
                }
            }
        });
    }
}