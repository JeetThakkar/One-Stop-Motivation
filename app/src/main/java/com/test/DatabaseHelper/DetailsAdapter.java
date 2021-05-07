package com.test.DatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.test.Database.LendersDetail;
import com.spotify.test.R;
import com.test.LendersInfo;
import com.test.MainActivity;

public class DetailsAdapter extends ListAdapter<LendersDetail, LendersDetailVH> {

    private Context context;

    public DetailsAdapter(Context context) {
        super(diffCallback);
        this.context = context;
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
    public LendersDetailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View detailItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_detail_view, parent, false);
        return new LendersDetailVH(detailItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LendersDetailVH holder, int position) {
        LendersDetail currentPerson = getItem(position);

        holder.view.setOnClickListener(v -> {
            Intent infoActivity = new Intent(context, LendersInfo.class);
            infoActivity.putExtra(MainActivity.LENDERID, currentPerson.Id);
            context.startActivity(infoActivity);
        });

        holder.Name.setText(currentPerson.Name);
        holder.Amount.setText(currentPerson.AmountLend);
        holder.Date.setText(currentPerson.PaymentHistory.substring(4));

    }

}
