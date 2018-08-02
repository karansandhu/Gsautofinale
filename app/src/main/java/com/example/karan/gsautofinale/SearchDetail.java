package com.example.karan.gsautofinale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchDetail extends AppCompatActivity {
    private static final String TAG = "EXTRA_POSITION";
    public static final String EXTRA_POSITION = "search_detail";
    private Context mContext;
    List<ProductModelTwo> productModelTwos;
    String itemId = null;
    FirebaseDatabase database;
    DatabaseReference ref;
    TextView tvSearchCode,tvSearchSize,tvSearchModel,tvSearchPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        productModelTwos = new ArrayList<>();

        itemId = Objects.requireNonNull(getIntent().getExtras()).getString(SearchDetail.EXTRA_POSITION);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("data");

        ref.keepSynced(true);
        tvSearchCode = (TextView) findViewById(R.id.tvSearchCode);
        tvSearchSize = (TextView) findViewById(R.id.tvSearchSize);
        tvSearchModel = (TextView) findViewById(R.id.tvSearchModel);
        tvSearchPrice = (TextView) findViewById(R.id.tvSearchPrice);

        ref.child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    ProductModelTwo productModelTwo = dataSnapshot.getValue(ProductModelTwo.class);
                    if (productModelTwos != null) {
                        tvSearchCode.setText(productModelTwo.getCode());
                        tvSearchModel.setText(productModelTwo.getModel());

                    }
                }else {
                    Toast.makeText(mContext, "Not found any ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
//        Intent intent = getIntent();
//            String code = intent.getStringExtra("Code");
//            String size = intent.getStringExtra("Size");
//            String model = intent.getStringExtra("Model");
//            String price = intent.getStringExtra("Price");
//
//            tvSearchCode.setText(code);
//            tvSearchSize.setText(size);
//            tvSearchModel.setText(model);
//            tvSearchPrice.setText(price);


    }

}
