package com.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spotify.test.R;
import com.test.Database.LendersDetail;
import com.test.DatabaseHelper.LenderViewModel;

import java.util.Date;

import static java.text.DateFormat.getDateInstance;

public class LendersInfo extends AppCompatActivity {

    TextView Name,Amount, LendingDate,DuePayment,Status;
    LiveData<LendersDetail> lendersDetail;
    LenderViewModel lvm;
    int lenderId;
    float dueAmount;
    String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lenderId = getIntent().getIntExtra(MainActivity.LENDERID, -1);
        Log.i("Info", "ID " + lenderId);
        if(lenderId != -1) {
            setContentView(R.layout.activity_lenders_info);
            init();
            lvm = new ViewModelProvider(this).get(LenderViewModel.class);
            lendersDetail = lvm.getLenderById(lenderId);
            lendersDetail.observe(this, lendersDetail -> {
                Name.setText(lendersDetail.Name);
                Amount.setText(lendersDetail.AmountLend);
                LendingDate.setText(lendersDetail.PaymentHistory);
                history = lendersDetail.PaymentHistory;
                dueAmount = Float.parseFloat(lendersDetail.AmountLend)/10;
                DuePayment.setText(String.valueOf(dueAmount));
                if(lendersDetail.status)
                    Status.setText("Payment Due");
                else
                    Status.setText("Payment Done");
            });
        }
        else
            setContentView(R.layout.error_view);
    }

    private void init() {
        Name = findViewById(R.id.lenderName);
        Amount = findViewById(R.id.AmountLended);
        LendingDate = findViewById(R.id.LendingDate);
        DuePayment = findViewById(R.id.PaymentDue);
        Status = findViewById(R.id.Status);
    }

    public void ChangeStatus(View view){
        String date = getDateInstance().format(new Date());
        history += MainActivity.COMMA + date;
        LendersDetail newLDetails = lendersDetail.getValue();
        newLDetails.setStatus(false);
        newLDetails.setPaymentHistory(history);
        lvm.UpdatePerson(newLDetails);
        ViewGroup vg = findViewById(R.id.BaseLayout);
        vg.removeView(view.findViewById(R.id.PaymentClearedButton));
    }

}