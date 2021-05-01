package com.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.test.Database.LendersDetail;
import com.spotify.test.R;

public class DetailsAdapter extends ListAdapter<LendersDetail,PersonDetailsVH> {


    public DetailsAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<LendersDetail> diffCallback = new DiffUtil.ItemCallback<LendersDetail>() {
        @Override
        public boolean areItemsTheSame(@NonNull LendersDetail oldItem, @NonNull LendersDetail newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull LendersDetail oldItem, @NonNull LendersDetail newItem) {
            return (oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getAmountLend().equals(newItem.getAmountLend())  &&
                    oldItem.getPaymentHistory().equals(newItem.getPaymentHistory()));
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
        LendersDetail currentPerson = getItem(position);

        holder.Name.setText(currentPerson.Name);
        holder.Amount.setText(currentPerson.AmountLend);
        holder.Date.setText(currentPerson.PaymentHistory.substring(4));

    }

}
