package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button submitBtn;
    RadioGroup pizzaSize;
    Spinner toppings;
    CheckBox xtraCheese, delivery;
    EditText specialInstructions;

    // Extract contact details
    EditText userName, userAddress, userPhone, userEmail;

    double toppingsCost = 0.0, totalCost = 0.0; // total cost and current price

//    ActivityResultLauncher<Intent> startForResult =
//            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result != null){
//                        if(result.getData()!=null && result.getResultCode()==RESULT_OK) {
////                            textView.setText(result.getData().getStringExtra("KEY_NAME"));
//
//
//                        }
//                    }
//                }
//            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.name);
        userAddress = findViewById(R.id.postalAddress);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);

        xtraCheese = findViewById(R.id.xtraCheese);
        delivery = findViewById(R.id.delivery);
        specialInstructions = findViewById(R.id.specialInstructions);
        submitBtn = findViewById(R.id.submitBtn);

        // Calculate toppings cost & extras
        toppings = findViewById(R.id.toppings);
        ArrayAdapter<CharSequence> toppingsAdapter = new ArrayAdapter<CharSequence>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.toppingsItems));
        toppingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toppings.setAdapter(toppingsAdapter);
        toppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toppingsCost= calcToppingsCost(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO: Return msg nothing was selected
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calculate pizza size cost and add to total
                pizzaSize = findViewById(R.id.rdioGrp);
                int selectedSizeID = pizzaSize.getCheckedRadioButtonId();
                calcTotalCost(calcPizzaSizeCost(selectedSizeID));


                if (xtraCheese.isChecked()) {
                    calcTotalCost(5.0); // Calculating the price of including extra cheese
                }
                if (delivery.isChecked()) {
                    calcTotalCost(5.0); // Calculating the price of including and delivery
                }

                calcTotalCost(toppingsCost);

                // Intent to navigate user to new activity
                Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
                String totalCostString = String.valueOf(totalCost);

                intent.putExtra("totalCost", totalCostString);
                intent.putExtra("name", userName.getText());
                intent.putExtra("address", userAddress.getText());
                intent.putExtra("phone", userPhone.getText());
                intent.putExtra("email", userEmail.getText());
                intent.putExtra("specialInstructions", specialInstructions.getText());
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Directed to Checkout", Toast.LENGTH_LONG).show();
            }
        });
    }

    private double calcToppingsCost(int pos) {
        switch (pos) {
            case 1:
            case 5:
            case 7:
            case 8:
                return 5.0;
            case 2:
                return 7.0;
            case 3:
            case 9:
                return 8.0;
            case 4:
                return 10.0;
            case 6:
                return 9.0;
            default:
                return 0;
        }
    }

    private double calcPizzaSizeCost(int selectedSizeID) {
        // Prints in a toast the selected pizza
        RadioButton radioButton = findViewById(selectedSizeID);
        Toast.makeText(MainActivity.this, "Selected: " + radioButton.getText(), Toast.LENGTH_LONG).show();
        switch (selectedSizeID) {
            case R.id.p6Slices:
                return 5.5;
            case R.id.p8Slices:
                return 7.99;
            case R.id.p10Slices:
                return 9.50;
            case R.id.p12Slices:
                return 11.38;
            default:
                return 0;
        }
    }

    private void calcTotalCost(double price) {
        totalCost += price;
        Toast.makeText(MainActivity.this, "Total: " + totalCost, Toast.LENGTH_LONG).show();

    }
}
