package com.example.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAppointmentAdapter extends RecyclerView.Adapter<BookAppointmentAdapter.AppointmentViewHolder> {

    private List<SimpleDoctor> doctorList;
    private Context context;
    private OnDoctorClickListener onDoctorClickListener;
    private String patientName; // New field to store the patient's name

    public BookAppointmentAdapter(List<SimpleDoctor> doctorList, Context context, String patientName, OnDoctorClickListener onDoctorClickListener) {
        this.doctorList = doctorList;
        this.context = context;
        this.patientName = patientName; // Initialize the patient's name
        this.onDoctorClickListener = onDoctorClickListener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        SimpleDoctor doctor = doctorList.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.specializationTextView.setText(doctor.getSpecialization());

        holder.itemView.setOnClickListener(v -> {
            if (onDoctorClickListener != null) {
                onDoctorClickListener.onDoctorClick(doctor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView specializationTextView;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.doctor_name);
            specializationTextView = itemView.findViewById(R.id.doctor_speciality);
        }
    }

    public interface OnDoctorClickListener {
        void onDoctorClick(SimpleDoctor doctor);
    }

    public String getPatientName() {
        return patientName;
    }
}
