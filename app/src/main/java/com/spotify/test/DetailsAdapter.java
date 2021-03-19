package com.spotify.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.spotify.test.Database.PersonDetails;

public class DetailsAdapter extends ListAdapter<PersonDetails,PersonDetailsVH> {


    public DetailsAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<PersonDetails> diffCallback = new DiffUtil.ItemCallback<PersonDetails>() {
        @Override
        public boolean areItemsTheSame(@NonNull PersonDetails oldItem, @NonNull PersonDetails newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PersonDetails oldItem, @NonNull PersonDetails newItem) {
            return oldItem.getFirstName().equals(newItem.getFirstName()) &&
                    oldItem.getLastName().equals(newItem.getLastName()) &&
                    oldItem.getEmail().equals(newItem.getEmail());
        }
    };


    @NonNull
    @Override
    public PersonDetailsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View detailItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_detail_view, parent, false);
        return new PersonDetailsVH(detailItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonDetailsVH holder, int position) {
        PersonDetails currentPerson = getItem(position);

        holder.firstName.setText(currentPerson.FirstName);
        holder.lastName.setText(currentPerson.LastName);
        holder.email.setText(currentPerson.Email);

    }

}
