package valiant.mylbcclient.fragent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import needle.Needle;
import needle.UiRelatedTask;
import valiant.mylbcclient.AuthActivity;
import valiant.mylbcclient.MainActivity;
import valiant.mylbcclient.R;
import valiant.mylbcclient.adapter.BuyListAdapter;
import valiant.mylbcclient.adapter.FunctionListAdapter;
import valiant.mylbcclient.model.BuyAdList;
import valiant.mylbcclient.model.FunctionsList;
import valiant.mylbcclient.utils.Config;
import valiant.mylbcclient.utils.FunctionsDatabase;

public class FunctionsFragment extends Fragment {

    private Context context;
    private View parentView;
    private AVLoadingIndicatorView avi;
    private String functionName, ad_id;
    private RecyclerView recyclerView;
    private List<FunctionsList> functionsLists;
    private FunctionListAdapter functionListAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null){
            parentView = inflater.inflate(R.layout.functions_fragment, container, false);
        }

        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        avi = view.findViewById(R.id.avi);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.add_new_function);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) view.getContext();
                mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new AddFunctionsFragment(), "fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });



    }

    private void loadData(String key, String secret){
        avi.smoothToShow();
        Needle.onBackgroundThread().execute(new UiRelatedTask<String>() {

            @Override
            protected String doWork() {

                FunctionsDatabase functionsDatabase = new FunctionsDatabase(context);
                Cursor data = functionsDatabase.getFunctionNames();
                if (data != null){
                    while (data.moveToNext()){

                        functionName = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_function_name));
                        ad_id = data.getString(data.getColumnIndexOrThrow(FunctionsDatabase.key_ad_id));

                    }
                    data.close();
                    functionsDatabase.close();
                }

                return null;
            }

            @Override
            protected void thenDoUiRelatedWork(String result) {
                avi.smoothToHide();

            }
        });
    }
}
