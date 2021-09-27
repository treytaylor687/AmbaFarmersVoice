package com.example.ambafamersvoice;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        //all the buttons in categories
        Button btn_aqua = (Button) view.findViewById(R.id.btn_aqua);
        Button btn_post = (Button) view.findViewById(R.id.btn_post);
        Button btn_fruit = (Button) view.findViewById(R.id.btn_fruit);
        Button btn_vege = (Button) view.findViewById(R.id.btn_vege);
        Button btn_cer = (Button) view.findViewById(R.id.btn_cer);
        Button btn_fert = (Button) view.findViewById(R.id.btn_fert);
        Button btn_irri = (Button) view.findViewById(R.id.btn_irri);
        Button btn_tubers = (Button) view.findViewById(R.id.btn_tubers);
        Button btn_live = (Button) view.findViewById(R.id.btn_live);
        Button btn_pest = (Button) view.findViewById(R.id.btn_pest);
        Button btn_business = (Button) view.findViewById(R.id.btn_business);
        Button btn_bee = (Button) view.findViewById(R.id.btn_bee);

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_fert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_irri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_tubers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_pest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_bee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });





        btn_aqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

        btn_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Video_1.class);
                startActivity(in);}
        });

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
