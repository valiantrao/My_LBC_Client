package valiant.mylbcclient.fragent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import valiant.mylbcclient.R;

public class SellFragment extends Fragment {

    private View parentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (parentView == null){
            parentView = inflater.inflate(R.layout.sell_fragment, container, false);
        }

        return parentView;
    }
}
