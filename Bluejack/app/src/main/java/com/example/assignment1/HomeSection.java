package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class HomeSection extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_section, container, false);

        RecyclerView rv = view.findViewById(R.id.rvMedicine);
        ImageView medlogo_iv = view.findViewById(R.id.ivLogoMedicine);

        medlogo_iv.setOnClickListener(e -> {
            Intent intent = new Intent(getContext(),AboutUs.class);
            getContext().startActivity(intent);
        });

        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("Tolak angin",35000,"sidomuncul","tolakangin"));
        medicines.add(new Medicine("Panadol",15000,"kalbe","panadol"));
        medicines.add(new Medicine("CDR",40000,"vitamin","cdr"));
        medicines.add(new Medicine("Imboost",77000,"sehat","imboost"));
        medicines.add(new Medicine("Lohankuo",20000,"china","lohankuo"));
        medicines.add(new Medicine("Betadine",30000,"lukaluka","betadine"));

        MedicineAdapter ma = new MedicineAdapter(getActivity());
        ma.setMedicines(medicines);

        rv.setAdapter(ma);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        return view;
    }
}