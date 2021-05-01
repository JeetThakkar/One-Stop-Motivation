package com.test.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "LendersDetail")
public class LendersDetail {

    @PrimaryKey(autoGenerate = true)
    public int Id;

    public String Name;

    public String AmountLend;

    public String PaymentHistory;

    public Boolean status;

    public LendersDetail(String Name, String AmountLend, String PaymentHistory, Boolean status) {
        this.Name = Name;
        this.AmountLend = AmountLend;
        this.PaymentHistory = PaymentHistory;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmountLend() {
        return AmountLend;
    }

    public void setAmountLend(String amountLend) {
        AmountLend = amountLend;
    }

    public String getPaymentHistory() {
        return PaymentHistory;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
