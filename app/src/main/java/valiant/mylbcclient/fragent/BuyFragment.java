package valiant.mylbcclient.fragent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.AuthActivity;
import valiant.mylbcclient.MainActivity;
import valiant.mylbcclient.R;
import valiant.mylbcclient.utils.Config;
import valiant.mylbcclient.utils.SessionManager;

public class BuyFragment extends Fragment {

    private Context context;
    private View parentView;
    private AVLoadingIndicatorView avi;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (parentView == null){
            parentView = inflater.inflate(R.layout.buy_fragment, container, false);
        }

        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionManager sessionManager = new SessionManager(context);
        avi = view.findViewById(R.id.avi);
        loadData(sessionManager.getLbcKey(), sessionManager.getLbcSecret());
    }

    private void loadData(String key, String secret){

        avi.smoothToShow();
        Needle.onBackgroundThread().execute(new UiRelatedTask<String>() {

            @Override
            protected String doWork() {
                return MainActivity.python.getModule(Config.LBC_API).callAttr("getBuyAds", key, secret).toString();
            }

            @Override
            protected void thenDoUiRelatedWork(String result) {
                avi.smoothToHide();
                Log.e("result:::", result);
            }
        });
    }
}
