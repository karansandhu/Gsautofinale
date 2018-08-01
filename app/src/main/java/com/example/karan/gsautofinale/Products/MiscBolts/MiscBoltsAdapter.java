package com.example.karan.gsautofinale.Products.MiscBolts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karan.gsautofinale.DetailActivity;
import com.example.karan.gsautofinale.ProductModel;
import com.example.karan.gsautofinale.R;

import java.util.List;

public class MiscBoltsAdapter extends RecyclerView.Adapter<MiscBoltsAdapter.MiscBoltsViewHolder> {

    List<ProductModel> productModels;
    Context context;

    public MiscBoltsAdapter(List<ProductModel> productModels, Context context) {
        this.productModels = productModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MiscBoltsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_model,parent,false);
        return new MiscBoltsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiscBoltsViewHolder holder, final int position) {

        ProductModel productModel = productModels.get(position);

        holder.tvCode.setText(productModel.getCode());
        holder.tvSize.setText(productModel.getSize());
        holder.tvModel.setText(productModel.getModel());
        holder.tvPrice.setText(productModel.getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("Code",productModels.get(position).getCode());
                intent.putExtra("Size",productModels.get(position).getSize());
                intent.putExtra("Model",productModels.get(position).getModel());
                intent.putExtra("Price",productModels.get(position).getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class MiscBoltsViewHolder extends RecyclerView.ViewHolder{

        TextView tvCode,tvSize,tvModel,tvPrice;
        CardView cardView;

        public MiscBoltsViewHolder(View itemView) {
            super(itemView);

            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
            tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            tvModel = (TextView) itemView.findViewById(R.id.tvModel);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

}
