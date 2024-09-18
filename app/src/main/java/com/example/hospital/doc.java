package com.example.hospital;

import android.os.Parcel;
import android.os.Parcelable;

public class doc implements Parcelable {
    private String name;
    private String email;
    private String location;
    private String description;
    private String specialization; // New field for specialization
    private int imageResId; // Use int for drawable resource ID
    private float rating;

    // Constructor
    public doc(String name, String email, String location, String description, String specialization, int imageResId, float rating) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.description = description;
        this.specialization = specialization; // Initialize specialization
        this.imageResId = imageResId;
        this.rating = rating;
    }

    protected doc(Parcel in) {
        name = in.readString();
        email = in.readString();
        location = in.readString();
        description = in.readString();
        specialization = in.readString(); // Read specialization
        imageResId = in.readInt(); // Read drawable resource ID
        rating = in.readFloat();
    }

    public static final Creator<doc> CREATOR = new Creator<doc>() {
        @Override
        public doc createFromParcel(Parcel in) {
            return new doc(in);
        }

        @Override
        public doc[] newArray(int size) {
            return new doc[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(location);
        parcel.writeString(description);
        parcel.writeString(specialization); // Write specialization
        parcel.writeInt(imageResId); // Write drawable resource ID
        parcel.writeFloat(rating);
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getSpecialization() { return specialization; } // Getter for specialization
    public int getImageResId() { return imageResId; }
    public float getRating() { return rating; }
}
