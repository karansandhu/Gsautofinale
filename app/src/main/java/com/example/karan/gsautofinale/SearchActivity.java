package com.example.karan.gsautofinale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    SearchAdapter searchAdapter;
    DatabaseReference ref;
    List<ProductModel> productModels;
    EditText etSearch;
    Button bSearch,bSearchTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        productModels = new ArrayList<>();
        searchAdapter = new SearchAdapter(productModels,getApplicationContext());

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        etSearch = (EditText) findViewById(R.id.etSearch);
        bSearch = (Button) findViewById(R.id.bSearch);
        bSearchTwo = (Button) findViewById(R.id.bSearchTwo);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String word = etSearch.getText().toString().toUpperCase();
                SearchFirebase(word);
                hideSoftKeyboard();
            }
        });

        bSearchTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = etSearch.getText().toString();
                SearchAgainFirebase(word);
                hideSoftKeyboard();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void SearchAgainFirebase(String word){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Searching....");
        progressDialog.show();

        ref = database.getReference("data");
        Query query = ref.orderByChild("model").startAt(word).endAt(word + "\uf8ff");

        FirebaseRecyclerAdapter<ProductModel,SearchAdapter.SearchViewHolder> firebaseRecyclerAdapterTwo = new FirebaseRecyclerAdapter<ProductModel, SearchAdapter.SearchViewHolder>(
                ProductModel.class,R.layout.product_model, SearchAdapter.SearchViewHolder.class,query
        ) {
            @Override
            protected void populateViewHolder(SearchAdapter.SearchViewHolder viewHolder, ProductModel model, int position) {
                progressDialog.dismiss();
                viewHolder.setDetails(model.getCode(),model.getSize(),model.getModel(),model.getPrice());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapterTwo);
    }

    public void SearchFirebase(String word){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Searching....");
        progressDialog.show();

        ref = database.getReference("data");
        Query query = ref.orderByChild("code").startAt(word).endAt(word + "\uf8ff");

       FirebaseRecyclerAdapter<ProductModel,SearchAdapter.SearchViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductModel, SearchAdapter.SearchViewHolder>(
               ProductModel.class,R.layout.product_model, SearchAdapter.SearchViewHolder.class,query
       ) {
           @Override
           protected void populateViewHolder(SearchAdapter.SearchViewHolder viewHolder, ProductModel model,final int position) {

               progressDialog.dismiss();
               viewHolder.setDetails(model.getCode(),model.getSize(),model.getModel(),model.getPrice());
               final String single_view = getRef(position).getKey();
               viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent = new Intent(SearchActivity.this,SearchDetail.class);
                       intent.putExtra("single_view",SearchDetail.class);

                       startActivity(intent);
                   }
               });
           }
       };

       recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void hideSoftKeyboard(){
        if(getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
