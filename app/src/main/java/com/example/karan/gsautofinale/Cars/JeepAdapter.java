package com.example.karan.gsautofinale.Cars;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karan.gsautofinale.DetailActivity;
import com.example.karan.gsautofinale.ProductModelTwo;
import com.example.karan.gsautofinale.R;

import java.util.List;

public class JeepAdapter extends RecyclerView.Adapter<JeepAdapter.JeepViewHolder> {

    List<ProductModelTwo> productModelTwos;
    Context context;

    public JeepAdapter(List<ProductModelTwo> productModelTwos, Context context) {
        this.productModelTwos = productModelTwos;
        this.context = context;
    }

    @NonNull
    @Override
    public JeepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_model,parent,false);
        return new JeepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JeepViewHolder holder, final int position) {

        ProductModelTwo productModelTwo = productModelTwos.get(position);
        holder.tvCode.setText(productModelTwo.getCode());
        holder.tvSize.setText(productModelTwo.getSize());
        holder.tvModel.setText(productModelTwo.getModel());
        holder.tvPrice.setText(productModelTwo.getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("Code",productModelTwos.get(position).getCode());
                intent.putExtra("Size",productModelTwos.get(position).getSize());
                intent.putExtra("Model",productModelTwos.get(position).getModel());
                intent.putExtra("Price",productModelTwos.get(position).getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModelTwos.size();
    }

    public class JeepViewHolder extends RecyclerView.ViewHolder{

        TextView tvCode,tvSize,tvModel,tvPrice;
        CardView cardView;

        public JeepViewHolder(View itemView) {
            super(itemView);

            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
            tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            tvModel = (TextView) itemView.findViewById(R.id.tvModel);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }

}
