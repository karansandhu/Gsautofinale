package com.example.karan.gsautofinale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchDetail extends AppCompatActivity {

    TextView tvSearchCode,tvSearchSize,tvSearchModel,tvSearchPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        tvSearchCode = (TextView) findViewById(R.id.tvSearchCode);
        tvSearchSize = (TextView) findViewById(R.id.tvSearchSize);
        tvSearchModel = (TextView) findViewById(R.id.tvSearchModel);
        tvSearchPrice = (TextView) findViewById(R.id.tvSearchPrice);

        Intent intent = getIntent();
            String code = intent.getStringExtra("Code");
            String size = intent.getStringExtra("Size");
            String model = intent.getStringExtra("Model");
            String price = intent.getStringExtra("Price");

            tvSearchCode.setText(code);
            tvSearchSize.setText(size);
            tvSearchModel.setText(model);
            tvSearchPrice.setText(price);


    }

}
