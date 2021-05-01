package com.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.Database.LendersDetail;
import com.test.Database.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository repository;
    private LiveData<List<LendersDetail>> detailsList;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
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

}
