package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Medicine> medicines;

    public MedicineAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.medicine_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {
        holder.medicineName_edt.setText(medicines.get(position).getName());
        holder.medicineManufacturer_edt.setText(medicines.get(position).getManufacturer());
        holder.medicinePrice_edt.setText(String.valueOf(medicines.get(position).getPrice()));

        String imageName = medicines.get(position).getImage();
        int resID = ctx.getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        holder.medicine_iv.setImageResource(resID);

        holder.cv.setOnClickListener(e -> {
            Intent intent = new Intent(ctx,MedicineDetailPage.class);
            intent.putExtra("medname",medicines.get(position).getName());
            intent.putExtra("medman",medicines.get(position).getManufacturer());
            intent.putExtra("medprice",String.valueOf(medicines.get(position).getPrice()));
            intent.putExtra("medimage",medicines.get(position).getImage());
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicineName_edt, medicinePrice_edt, medicineManufacturer_edt;
        ImageView medicine_iv;
        CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName_edt = itemView.findViewById(R.id.medicineName);
            medicinePrice_edt = itemView.findViewById(R.id.medicinePrice);
            medicineManufacturer_edt = itemView.findViewById(R.id.medicineManufacturer);
            medicine_iv = itemView.findViewById(R.id.medicineImage);
            cv = itemView.findViewById(R.id.medicineCv);
        }
    }


}
