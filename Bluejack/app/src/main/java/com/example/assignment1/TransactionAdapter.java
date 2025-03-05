package com.example.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context ctx;
    ArrayList<Transaction> transactions;

    public TransactionAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setTransactions(ArrayList<Transaction> transactions){
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.transaction_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        if (TransactionDatabase.getInstance().getLoggedIn().equals(transactions.get(position).getUser())){
            holder.transQty_edt.setText(transactions.get(position).getQuantity());
            holder.transName_edt.setText(transactions.get(position).getName());
            holder.transDate_edt.setText(transactions.get(position).getDate());
            holder.transPrice_edt.setText(transactions.get(position).getPrice());
            holder.transPrice_edt.setText(transactions.get(position).getPrice());
        }

        holder.delete_btn.setOnClickListener(view -> {
            TransactionDatabase.getInstance().removeFromMyList(position);
            notifyItemRemoved(position);
            notifyItemChanged(position,transactions.size());
        });

        holder.edit_btn.setOnClickListener(view -> {
            if (holder.transQty_edt.getVisibility() == View.VISIBLE) {
                holder.editText.setText(holder.transQty_edt.getText().toString());

                holder.transQty_edt.setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);

                holder.editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(holder.editText, InputMethodManager.SHOW_IMPLICIT);
            }
            else {
                holder.transQty_edt.setText(holder.editText.getText().toString());

                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = dateFormat.format(currentDate);

                String[] input = {dateString,holder.transName_edt.getText().toString()
                        ,holder.transPrice_edt.getText().toString(),holder.editText.getText().toString()};

                TransactionDatabase.getInstance().updateMyList(position,input);

                holder.editText.setVisibility(View.GONE);
                holder.transQty_edt.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(holder.editText.getWindowToken(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView transDate_edt, transName_edt, transPrice_edt, transQty_edt, transUser;
        Button edit_btn, delete_btn;
        EditText editText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edit_btn = itemView.findViewById(R.id.btnEdit);
            delete_btn = itemView.findViewById(R.id.btnDelete);
            transName_edt = itemView.findViewById(R.id.tvTransName);
            transDate_edt = itemView.findViewById(R.id.tvTransDate);
            transPrice_edt = itemView.findViewById(R.id.tvTransPrice);
            transQty_edt = itemView.findViewById(R.id.tvTransQuantity);
            editText = itemView.findViewById(R.id.etTransQuantity);
        }
    }
}
