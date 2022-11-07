package com.mtpms.lr8.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mtpms.lr8.Bass;
import com.mtpms.lr8.CreateBass;
import com.mtpms.lr8.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Bass> basses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        try
        {
            basses = JSON.importFromJSON(getContext());
            if(basses == null)
            {
                basses = new ArrayList<>();
            }
        }
        catch (Exception exc)
        {

        }

        final RecyclerView bassRV = binding.list;
        StateAdapter adapter = new StateAdapter(getContext(), basses);
        bassRV.setAdapter(adapter);

        Intent intent = new Intent(getContext(), CreateBass.class);
        startActivity(intent);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}