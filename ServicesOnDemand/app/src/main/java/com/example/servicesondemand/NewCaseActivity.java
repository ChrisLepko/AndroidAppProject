package com.example.servicesondemand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewCaseActivity extends AppCompatActivity {

    private TextView categoryInfoTextView, detailsInfoTextView, phoneInfoTextView;
    private EditText categoryEditText, detailsEditText, phoneEditText;
    private Button submitButton;
    private String category, problemDetails, phoneNumber;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase, firebaseUserDatabase;
    DatabaseReference databaseReference, demoRef, databaseUserReference, demoUserRef;

    private void setupUIViews(){
        categoryInfoTextView = findViewById(R.id.categoryInfoTextView);
        detailsInfoTextView = findViewById(R.id.detailsInfoTextView);
        phoneInfoTextView = findViewById(R.id.phoneInfoTextView);
        categoryEditText = findViewById(R.id.categoryEditText);
        detailsEditText = findViewById(R.id.detailsEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        submitButton = findViewById(R.id.submitButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_case);
        setupUIViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUserDatabase = FirebaseDatabase.getInstance();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendUserData();
                    Toast.makeText(getApplicationContext(), "Case added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainScreenActivity.class));
                }
            }
        });

    }

    private Boolean validate(){
        Boolean result = false;

        category = categoryEditText.getText().toString();
        problemDetails = detailsEditText.getText().toString();
        phoneNumber = phoneEditText.getText().toString();

        if(category.isEmpty() || problemDetails.isEmpty() || phoneNumber.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    private void sendUserData(){
        databaseReference = firebaseDatabase.getReference();
        demoRef = databaseReference.child("Cases");
        UserCase userCase = new UserCase(category, problemDetails, phoneNumber);
        demoRef.push().setValue(userCase);

        //Upload data to certain user Table
        databaseUserReference = firebaseUserDatabase.getReference(firebaseAuth.getUid());
        demoUserRef = databaseUserReference.child("Cases");
        demoUserRef.push().setValue(userCase);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
