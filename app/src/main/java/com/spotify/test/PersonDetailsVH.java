package com.spotify.test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonDetailsVH extends RecyclerView.ViewHolder{

    TextView firstName, lastName, email;
    ImageView photo;

    public PersonDetailsVH(@NonNull View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.FisrtName);
        lastName = itemView.findViewById(R.id.LastName);
        email = itemView.findViewById(R.id.Email);
        photo = itemView.findViewById(R.id.PersonImage);
    }
}
