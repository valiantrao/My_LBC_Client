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

import valiant.mylbcclient.R;
import valiant.mylbcclient.model.BuyAdList;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.ViewHolder> {

    private Context context;
    private List<BuyAdList> buyAdList;

    public BuyListAdapter(Context context, List<BuyAdList> buyAdList) {
        this.context = context;
        this.buyAdList = buyAdList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_list_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.name.setText(buyAdList.get(position).getName());
        holder.last_online.setText(buyAdList.get(position).getLast_online());
        holder.price.setText(buyAdList.get(position).getPrice());
        holder.limit.setText(buyAdList.get(position).getLimit());
        holder.payment_window.setText(buyAdList.get(position).getPayment_window());
        holder.payment_method.setText(buyAdList.get(position).getPayment_method());


    }


    @Override
    public int getItemCount() {
        return buyAdList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name, last_online, price, limit, payment_window, payment_method;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            last_online = itemView.findViewById(R.id.last_online);
            price = itemView.findViewById(R.id.price);
            limit = itemView.findViewById(R.id.limit);
            payment_window = itemView.findViewById(R.id.payment_window);
            payment_method = itemView.findViewById(R.id.payment_method);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
/*            MainActivity mainActivity = (MainActivity) view.getContext();
            SearchResultFragment searchResultFragment = new SearchResultFragment();

            Bundle bundle = new Bundle();
            bundle.putString("search", tagsList.get(getAdapterPosition()));

            searchResultFragment.setArguments(bundle);

            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, searchResultFragment, "fragment")
                    .addToBackStack("fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();*/
            Toast.makeText(context, getAdapterPosition() + " clicked", Toast.LENGTH_SHORT).show();


        }
    }

}
