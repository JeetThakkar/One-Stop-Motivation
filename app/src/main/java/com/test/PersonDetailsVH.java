package com.test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spotify.test.R;

public class PersonDetailsVH extends RecyclerView.ViewHolder{

    TextView Name, Amount, Date;
    ImageView photo;

    public PersonDetailsVH(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.Name);
        Amount = itemView.findViewById(R.id.LendedAmount);
        Date = itemView.findViewById(R.id.Date);
        photo = itemView.findViewById(R.id.PersonImage);
    }
}
