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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.AuthActivity;
import valiant.mylbcclient.MainActivity;
import valiant.mylbcclient.R;
import valiant.mylbcclient.adapter.BuyListAdapter;
import valiant.mylbcclient.model.BuyAdList;
import valiant.mylbcclient.utils.Config;
import valiant.mylbcclient.utils.SessionManager;

public class BuyFragment extends Fragment {

    private Context context;
    private View parentView;
    private AVLoadingIndicatorView avi;
    private RecyclerView recyclerView;
    private List<BuyAdList> buyAdList;
    private BuyListAdapter buyListAdapter;

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


        recyclerView = view.findViewById(R.id.recyclerView);

        buyAdList = new ArrayList<>();
        buyListAdapter = new BuyListAdapter(context, buyAdList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(buyListAdapter);

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
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject data = object.getJSONObject(Config.data);
                    JSONArray ad_list = data.getJSONArray(Config.ad_list);

                    for (int i = 0; i < ad_list.length(); i++){
                        JSONObject list_object = ad_list.getJSONObject(i);
                        JSONObject list_data = list_object.getJSONObject(Config.data);
                        JSONObject profile = list_data.getJSONObject(Config.profile);

                        // Profile data from profile Object
                        String username = profile.getString(Config.username);
                        String trade_count = profile.getString(Config.trade_count);
                        String feedback_score = profile.getString(Config.feedback_score);
                        String name = profile.getString(Config.name);
                        String last_online = profile.getString(Config.last_online);

                        // ad_data from list_data object
                        boolean visible = list_data.getBoolean(Config.visible);
                        String location_string = list_data.getString(Config.location_string);
                        String countrycode = list_data.getString(Config.countrycode);
                        String city = list_data.getString(Config.city);
                        String trade_type = list_data.getString(Config.trade_type);
                        String online_provider = list_data.getString(Config.online_provider);
                        String first_time_limit_btc = list_data.getString(Config.first_time_limit_btc);
                        String volume_coefficient_btc = list_data.getString(Config.volume_coefficient_btc);
                        boolean sms_verification_required = list_data.getBoolean(Config.sms_verification_required);
                        String currency = list_data.getString(Config.currency);
                        String min_amount = list_data.getString(Config.min_amount);
                        String max_amount = list_data.getString(Config.max_amount);
                        String max_amount_available = list_data.getString(Config.max_amount_available);
                        String ad_id = list_data.getString(Config.ad_id);
                        String temp_price_usd = list_data.getString(Config.temp_price_usd);
                        String temp_price = list_data.getString(Config.temp_price);
                        String created_at = list_data.getString(Config.created_at);
                        String require_feedback_score = list_data.getString(Config.require_feedback_score);
                        String require_trade_volume = list_data.getString(Config.require_trade_volume);
                        String msg = list_data.getString(Config.msg);
                        String bank_name = list_data.getString(Config.bank_name);
                        boolean require_trusted_by_advertiser = list_data.getBoolean(Config.require_trusted_by_advertiser);
                        boolean require_identification = list_data.getBoolean(Config.require_identification);
                        String payment_window_minutes = list_data.getString(Config.payment_window_minutes);
                        String limit_to_fiat_amounts = list_data.getString(Config.limit_to_fiat_amounts);

                        //actions from list_obj
                        String actions =  list_object.getString(Config.actions);

                        BuyAdList item = new BuyAdList(name, Config.getTime(last_online), temp_price, min_amount + "-" +
                                max_amount + " " + currency, payment_window_minutes, online_provider);
                        buyAdList.add(item);
                    }

                    buyListAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e("Json error:", String.valueOf(e));
                }
            }
        });
    }
}
