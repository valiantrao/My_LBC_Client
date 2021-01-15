package valiant.mylbcclient.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import valiant.mylbcclient.MainActivity;
import valiant.mylbcclient.R;
import valiant.mylbcclient.fragent.AddFunctionsFragment;
import valiant.mylbcclient.model.BuyAdList;
import valiant.mylbcclient.model.FunctionsList;

public class FunctionListAdapter extends RecyclerView.Adapter<FunctionListAdapter.ViewHolder> {

    private Context context;
    private List<FunctionsList> functionsList;

    public FunctionListAdapter(Context context, List<FunctionsList> functionsList) {
        this.context = context;
        this.functionsList = functionsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.function_list_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.function_name.setText(functionsList.get(position).getFunction_name());
        holder.ad_id.setText(functionsList.get(position).getAd_id());


    }


    @Override
    public int getItemCount() {
        return functionsList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView function_name, ad_id;


        public ViewHolder(View itemView) {
            super(itemView);
            function_name = itemView.findViewById(R.id.function_name);
            ad_id = itemView.findViewById(R.id.ad_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            MainActivity mainActivity = (MainActivity) view.getContext();
            mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new AddFunctionsFragment(), "fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }


        }
    }