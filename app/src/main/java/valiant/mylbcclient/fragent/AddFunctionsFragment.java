package valiant.mylbcclient.fragent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.AuthActivity;
import valiant.mylbcclient.MainActivity;
import valiant.mylbcclient.R;
import valiant.mylbcclient.model.FunctionsList;
import valiant.mylbcclient.utils.Config;
import valiant.mylbcclient.utils.FunctionsDatabase;
import valiant.mylbcclient.utils.SessionManager;

public class AddFunctionsFragment extends Fragment {

    private Context context;
    private View parentView;
    private SessionManager sessionManager;
    private FunctionsDatabase functionsDatabase;
    private AppCompatEditText function_name, ad_id, ad_ids_to_compare, amount, new_trade_first_msg;
    private RadioGroup radioGroup;
    private AppCompatButton save_fun_button;
    private String str_function_name;
    private String str_ad_id;
    private String str_ad_ids_to_compare;
    private String str_amount;
    private String str_new_trade_first_msg;
    private int radioGroup_id;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null){
            parentView = inflater.inflate(R.layout.add_function_fragment, container, false);
        }

        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(context);
        functionsDatabase = new FunctionsDatabase(context);

        if (getArguments() != null){
            str_function_name = getArguments().getString("function");

            Needle.onBackgroundThread().execute(new UiRelatedTask<String>() {

                @Override
                protected String doWork() {
                    FunctionsDatabase functionsDatabase = new FunctionsDatabase(context);
                    Cursor data = functionsDatabase.getFunctions();
                    if (data != null){
                        while (data.moveToNext()){

                            str_function_name = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_function_name));
                            str_ad_id = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_ad_id));
                            str_ad_ids_to_compare = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_ad_ids_to_compare));
                            str_amount = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_amount));
                            str_new_trade_first_msg = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_new_trade_first_msg));
                            radioGroup_id = data.getInt(data.getColumnIndexOrThrow(FunctionsDatabase.key_isAbove));

                        }
                        data.close();
                        functionsDatabase.close();
                    }

                    return null;
                }

                @Override
                protected void thenDoUiRelatedWork(String result) {
                    function_name.setText(str_function_name);
                    ad_id.setText(str_ad_id);
                    ad_ids_to_compare.setText(str_ad_ids_to_compare);
                    amount.setText(str_amount);
                    new_trade_first_msg.setText(str_new_trade_first_msg);
                    RadioButton button;
                    if (radioGroup_id == 0){
                        button = view.findViewById(R.id.above_price);
                    } else {
                        button = view.findViewById(R.id.below_price);
                    }
                    button.setChecked(true);

                }
            });
        }

        function_name = view.findViewById(R.id.function_name);
        ad_id = view.findViewById(R.id.ad_id);
        ad_ids_to_compare = view.findViewById(R.id.ad_ids_to_compare);
        amount = view.findViewById(R.id.amount);
        new_trade_first_msg = view.findViewById(R.id.new_trade_first_msg);
        radioGroup = view.findViewById(R.id.radio_group);
        save_fun_button = view.findViewById(R.id.save_function);


        save_fun_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_function_name = function_name.getText().toString();
                str_ad_id = ad_id.getText().toString();
                str_ad_ids_to_compare = ad_ids_to_compare.getText().toString();
                str_amount = amount.getText().toString();
                str_new_trade_first_msg = new_trade_first_msg.getText().toString();

                if (radioGroup.getCheckedRadioButtonId() == R.id.above_price){
                    radioGroup_id = 0;
                } else {
                    radioGroup_id = 1;
                }
                if (TextUtils.isEmpty(str_function_name)){
                    function_name.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(str_ad_id)){
                    ad_id.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(str_ad_ids_to_compare)){
                    ad_ids_to_compare.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(str_amount)){
                    amount.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(str_new_trade_first_msg)){
                    new_trade_first_msg.setError("Required");
                    return;
                }





                functionsDatabase.addFunction(str_function_name, str_ad_id, str_ad_ids_to_compare, str_amount, str_new_trade_first_msg, radioGroup_id);

            }
        });

    }
}
