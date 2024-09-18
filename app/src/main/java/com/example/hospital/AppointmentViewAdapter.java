package com.example.hospital;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentViewAdapter extends RecyclerView.Adapter<AppointmentViewAdapter.ViewHolder> {

    private List<Appointment> appointmentList;
    private Context context;

    // Constructor to initialize the adapter with the data and context
    public AppointmentViewAdapter(List<Appointment> appointmentList, Context context) {
        this.appointmentList = appointmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the appointment for the current position
        Appointment appointment = appointmentList.get(position);

        // Bind the data to the views
        holder.textViewDoctorName.setText(appointment.getDoctorName());
        holder.textViewDate.setText(appointment.getDate());
        holder.textViewTime.setText(appointment.getTime());
        holder.textViewSpecialization.setText(appointment.getSpecialization());
        holder.textViewPatientName.setText(appointment.getPatientName());

        // Set up the click listener to open the AppointmentViewActivity with the appointment ID
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AppointmentViewActivity.class);
            intent.putExtra("APPOINTMENT_ID", appointment.getId()); // Ensure getId() returns the appointment ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // Return the size of the appointment list
        return appointmentList.size();
    }

    // ViewHolder class to hold the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDoctorName, textViewDate, textViewTime, textViewSpecialization, textViewPatientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            textViewDoctorName = itemView.findViewById(R.id.textViewDoctorName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewSpecialization = itemView.findViewById(R.id.textViewSpecialization);
            textViewPatientName = itemView.findViewById(R.id.textViewPatientName);
        }
    }
}
