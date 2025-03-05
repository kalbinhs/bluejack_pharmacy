package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TransactionSection extends Fragment {
    private TransactionAdapter ta;

    ArrayList<Transaction> fullArray;
    ArrayList<Transaction> transactions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_section, container, false);
        RecyclerView rv = view.findViewById(R.id.rvTransaction);

        fullArray = TransactionDatabase.getInstance().getMyList();

        transactions = (ArrayList<Transaction>) fullArray.stream()
                .filter(p -> p.getUser().equals(TransactionDatabase.getInstance().getLoggedIn())).collect(Collectors.toList());

        ta = new TransactionAdapter(getActivity());
        ta.setTransactions(transactions);

        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}