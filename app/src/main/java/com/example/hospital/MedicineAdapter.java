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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicineList;
    private Context context;
    private DatabaseReference cartRef;

    public MedicineAdapter(Context context, List<Medicine> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
        cartRef = FirebaseDatabase.getInstance().getReference("cart");
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        holder.nameTextView.setText(medicine.getName());
        holder.priceTextView.setText("Price: $" + medicine.getPrice());
        holder.discountTextView.setText("Discount: " + medicine.getDiscount() + "%");

        // Load the image into the ImageView using Glide
        Glide.with(context)
                .load(medicine.getImageUrl())
                .placeholder(R.drawable.bottle1)
                .into(holder.imageView);

        holder.addToCartButton.setOnClickListener(v -> {
            addToCart(medicine);
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    private void addToCart(Medicine medicine) {
        String cartItemId = cartRef.push().getKey();
        if (cartItemId != null) {
            cartRef.child(cartItemId).setValue(medicine);
        }
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
        TextView discountTextView;
        Button addToCartButton;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.medicineImage);
            nameTextView = itemView.findViewById(R.id.medicineName);
            priceTextView = itemView.findViewById(R.id.medicinePrice);
            discountTextView = itemView.findViewById(R.id.medicineDiscount);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
