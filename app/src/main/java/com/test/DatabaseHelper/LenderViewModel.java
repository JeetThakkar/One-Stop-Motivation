package com.test.DatabaseHelper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.Database.LendersDetail;
import com.test.Database.LenderRepository;

import java.util.List;

public class LenderViewModel extends AndroidViewModel {

    private LenderRepository repository;
    private LiveData<List<LendersDetail>> detailsList;

    public LenderViewModel(@NonNull Application application) {
        super(application);
        repository = new LenderRepository(application);
        detailsList = repository.getDetailsList();
    }

    public LiveData<List<LendersDetail>> getDetailsList(){
        return detailsList;
    }

    public void AddPerson(LendersDetail details){
        repository.AddPerson(details);
    }

    public void RemovePerson(LendersDetail details){
        repository.RemovePerson(details);
    }

    public void UpdatePerson(LendersDetail details){
        repository.UpdatePerson(details);
    }

    public LiveData<LendersDetail> getLenderById(int id){
        return repository.getLenderById(id);
    }

}
