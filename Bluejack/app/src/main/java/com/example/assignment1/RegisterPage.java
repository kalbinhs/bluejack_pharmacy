package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    public EditText username_edt, email_edt, password_edt, confirmPassword_edt, phoneNumber_edt;
    public Button loginPage_btn, register_btn;

    private boolean isEmpty(){
        return username_edt.getText().toString().matches("")
                || email_edt.getText().toString().matches("")
                || password_edt.getText().toString().matches("")
                || confirmPassword_edt.getText().toString().matches("")
                || phoneNumber_edt.getText().toString().matches("");
    }

    private boolean nameCheck(){
        return username_edt.getText().toString().length() < 5;
    }

    private boolean emailCheck(){
        return !email_edt.getText().toString().endsWith(".com");
    }

    private boolean passwordCheck(){
        String myString = password_edt.getText().toString();
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean confirmPasswordCheck(){
        return !confirmPassword_edt.getText().toString().equals(password_edt.getText().toString());
    }

    private boolean phoneNumberCheck(){
        String myString = phoneNumber_edt.getText().toString();
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username_edt = findViewById(R.id.editTextUsername);
        email_edt = findViewById(R.id.editTextEmail);
        password_edt = findViewById(R.id.editTextPassword);
        confirmPassword_edt = findViewById(R.id.editTextConfirmPassword);
        phoneNumber_edt = findViewById(R.id.editTextPhoneNumber);
        register_btn = findViewById(R.id.buttonRegister);
        loginPage_btn = findViewById(R.id.buttonLoginPage);

        register_btn.setOnClickListener(e -> {
            if(isEmpty()){
                Toast.makeText(this, "Please fill form!", Toast.LENGTH_SHORT).show();
            }
            else if(nameCheck()){
                Toast.makeText(this, "Username must be at least 5 letters!", Toast.LENGTH_SHORT).show();
            }
            else if(emailCheck()){
                Toast.makeText(this, "Input valid email!", Toast.LENGTH_SHORT).show();
            }
            else if(passwordCheck()){
                Toast.makeText(this, "Password must be alphanumeric!", Toast.LENGTH_SHORT).show();
            }
            else if(confirmPasswordCheck()){
                Toast.makeText(this, "Input valid phone number!", Toast.LENGTH_SHORT).show();
            }
            else if(phoneNumberCheck()){
                Toast.makeText(this, "!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, LoginPage.class);
                intent.putExtra("username",username_edt.getText().toString());
                intent.putExtra("password",password_edt.getText().toString());
                startActivity(intent);
            }

        });

        loginPage_btn.setOnClickListener(e->{
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });
    }
}