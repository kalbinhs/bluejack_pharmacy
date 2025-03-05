package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicineDetailPage extends AppCompatActivity {
    public ImageView medImage_iv;
    public TextView medName_edt, medMan_edt, medPrice_edt, medDesc_edt;
    public EditText quantity_edt;
    public Button purchase_btn;
    TransactionSection ts;
    private boolean isQuantityValid(){
        if(quantity_edt.getText().toString().matches("")){
            return true;
        }
        int value = Integer.parseInt(quantity_edt.getText().toString());
        return value < 1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail_page);

        medName_edt = findViewById(R.id.textViewMedicineName);
        medMan_edt = findViewById(R.id.textViewMedicineManufacturer);
        medPrice_edt = findViewById(R.id.textViewMedicinePrice);
        medDesc_edt = findViewById(R.id.textViewMedicineDescription);
        medImage_iv = findViewById(R.id.imageViewMedicine);
        quantity_edt = findViewById(R.id.editTextQuantity);
        purchase_btn = findViewById(R.id.buttonPurchase);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            medName_edt.setText(extras.getString("medname"));
            medMan_edt.setText(extras.getString("medman"));
            medPrice_edt.setText(extras.getString("medprice"));
            if (extras.getString("medimage").equals("betadine")){
                medDesc_edt.setText(R.string.betadine);
            } else if (extras.getString("medimage").equals("cdr")){
                medDesc_edt.setText(R.string.cdr);
            } else if (extras.getString("medimage").equals("lohankuo")){
                medDesc_edt.setText(R.string.lohankuo);
            } else if (extras.getString("medimage").equals("panadol")){
                medDesc_edt.setText(R.string.panadol);
            } else if (extras.getString("medimage").equals("tolakangin")){
                medDesc_edt.setText(R.string.tolakangin);
            } else if (extras.getString("medimage").equals("imboost")) {
                medDesc_edt.setText(R.string.imboost);
            }
            String imageName = extras.getString("medimage");
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            medImage_iv.setImageResource(resID);
        }
        purchase_btn.setOnClickListener(e -> {
            if(isQuantityValid()){
                Toast.makeText(this, "Input valid quantity!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Purchase has been made!", Toast.LENGTH_SHORT).show();
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = dateFormat.format(currentDate);

                String[] input = {dateString,medName_edt.getText().toString()
                ,medPrice_edt.getText().toString(),quantity_edt.getText().toString()};

                TransactionDatabase.getInstance().addToMyList(input);

                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}