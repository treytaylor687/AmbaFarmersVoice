package com.example.ambafamersvoice;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;


import androidx.navigation.fragment.NavHostFragment;

import com.example.ambafamersvoice.databinding.FragmentCategoryBinding;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //String[] fruitItems = {"Apple", "Strawberry", "Watermelon"};
        //ListView listView = (ListView) view.findViewById(R.id.fruitView);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}