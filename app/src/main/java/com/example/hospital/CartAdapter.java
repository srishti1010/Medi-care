package com.example.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private OnQuantityChangeListener quantityChangeListener;

    public CartAdapter(Context context, List<CartItem> cartItems, OnQuantityChangeListener quantityChangeListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.quantityChangeListener = quantityChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(String.format("$%.2f", item.getPrice()));
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
        Glide.with(context).load(item.getImageUrl()).into(holder.imageView);

        holder.minusButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
                quantityChangeListener.onQuantityChanged(item);
            }
        });

        holder.plusButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
            quantityChangeListener.onQuantityChanged(item);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface OnQuantityChangeListener {
        void onQuantityChanged(CartItem item);
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, priceTextView, quantityTextView;
        Button minusButton, plusButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_item_image);
            nameTextView = itemView.findViewById(R.id.cart_item_name);
            priceTextView = itemView.findViewById(R.id.cart_item_price);
            quantityTextView = itemView.findViewById(R.id.cart_item_quantity);
            minusButton = itemView.findViewById(R.id.cart_item_minus);
            plusButton = itemView.findViewById(R.id.cart_item_plus);
        }
    }
}
