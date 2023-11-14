package com.example.assignment1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    TextView userName, userAddress, userPhone, userEmail, totalCost, specialInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        userName = findViewById(R.id.name);
        userAddress = findViewById(R.id.postalAddress);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);

        String totalCostval = getIntent().getStringExtra("totalCost");
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        String specialInstructionsVal = getIntent().getStringExtra("specialInstructions");

        totalCost.setText(totalCostval);
        userName.setText(name);
        userAddress.setText(address);
        userPhone.setText(phone);
        userEmail.setText(email);
        specialInstructions.setText(specialInstructionsVal);
    }
}