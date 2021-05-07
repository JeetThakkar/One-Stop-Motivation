package com.test.DatabaseHelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spotify.test.R;

public class LendersDetailVH extends RecyclerView.ViewHolder{

    TextView Name, Amount, Date;
    ImageView photo;
    View view;

    public LendersDetailVH(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        Name = itemView.findViewById(R.id.Name);
        Amount = itemView.findViewById(R.id.LendedAmount);
        Date = itemView.findViewById(R.id.Date);
        photo = itemView.findViewById(R.id.PersonImage);
    }
}
