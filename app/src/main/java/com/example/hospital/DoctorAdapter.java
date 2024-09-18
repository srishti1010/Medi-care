package com.example.hospital;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<doc> doctorList;
    private Context context;

    public DoctorAdapter(List<doc> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        doc doctor = doctorList.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.specialtyTextView.setText(doctor.getEmail()); // Adjust if needed
        holder.locationTextView.setText(doctor.getLocation());
        holder.ratingBar.setRating(doctor.getRating());
        holder.profileImageView.setImageResource(doctor.getImageResId()); // Use getImageResId()

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, doctordesc.class);
            intent.putExtra("doctor_name", doctor.getName());
            intent.putExtra("doctor_email", doctor.getEmail());
            intent.putExtra("doctor_location", doctor.getLocation());
            intent.putExtra("doctor_description", doctor.getDescription());
            intent.putExtra("doctor_image", doctor.getImageResId()); // Pass drawable resource ID
            intent.putExtra("doctor_rating", doctor.getRating());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, specialtyTextView, locationTextView;
        RatingBar ratingBar;
        ImageView profileImageView;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.doctor_name);
            specialtyTextView = itemView.findViewById(R.id.doctor_speciality); // Ensure this ID matches your XML
            locationTextView = itemView.findViewById(R.id.doctor_location);
            ratingBar = itemView.findViewById(R.id.doctor_rating);
            profileImageView = itemView.findViewById(R.id.profile_image); // Ensure this ID matches your XML
        }
    }
}
