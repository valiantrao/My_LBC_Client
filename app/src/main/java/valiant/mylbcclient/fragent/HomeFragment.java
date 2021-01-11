package valiant.mylbcclient.fragent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import valiant.mylbcclient.R;
import valiant.mylbcclient.adapter.HomePagerAdapter;

public class HomeFragment extends Fragment {

    private View parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (parentView == null){
            parentView = inflater.inflate(R.layout.home_fragment, container, false);
        }

        return parentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.view_pager);

        TabLayout tabLayout = view.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());

        adapter.addFragment(new BuyFragment(), "Buy");
        adapter.addFragment(new SellFragment(), "Sell");
        adapter.addFragment(new AdsFragment(), "Ads");

        viewPager.setAdapter(adapter);
    }
}
