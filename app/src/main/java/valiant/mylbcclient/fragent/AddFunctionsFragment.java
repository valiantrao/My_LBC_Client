package valiant.mylbcclient.fragent;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import valiant.mylbcclient.R;
import valiant.mylbcclient.utils.FunctionsDatabase;
import valiant.mylbcclient.utils.SessionManager;

public class AddFunctionsFragment extends Fragment {

    private Context context;
    private View parentView;
    private SessionManager sessionManager;
    private FunctionsDatabase functionsDatabase;
    private AppCompatEditText function_name, ad_id, ad_ids_to_compare, amount, new_trade_first_msg;
    private RadioGroup radioGroup;
    private boolean isAbove = true;
    private AppCompatButton save_fun_button;

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

        function_name = view.findViewById(R.id.function_name);
        ad_id = view.findViewById(R.id.ad_id);
        ad_ids_to_compare = view.findViewById(R.id.ad_ids_to_compare);
        amount = view.findViewById(R.id.amount);
        new_trade_first_msg = view.findViewById(R.id.new_trade_first_msg);
        radioGroup = view.findViewById(R.id.radio_group);
        save_fun_button = view.findViewById(R.id.save_function);

        String str_function_name = function_name.getText().toString();
        String str_ad_id = ad_id.getText().toString();
        String str_ad_ids_to_compare = ad_ids_to_compare.getText().toString();
        String str_amount = amount.getText().toString();
        String str_new_trade_first_msg = new_trade_first_msg.getText().toString();
        int radioGroup_id = radioGroup.getCheckedRadioButtonId();

        if (radioGroup_id == R.id.below_price){
            isAbove = false;
        } else if (radioGroup_id == R.id.above_price){
            isAbove = true;
        }

        save_fun_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                functionsDatabase.addFunction(str_function_name, str_ad_id, str_ad_ids_to_compare, str_amount, str_new_trade_first_msg, isAbove);

            }
        });

    }
}
