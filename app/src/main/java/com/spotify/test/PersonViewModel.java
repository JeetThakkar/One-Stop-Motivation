package com.spotify.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spotify.test.Database.PersonDetails;
import com.spotify.test.Database.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository repository;
    private LiveData<List<PersonDetails>> detailsList;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
        detailsList = repository.getDetailsList();
    }

    public LiveData<List<PersonDetails>> getDetailsList(){
        return detailsList;
    }

    public void AddPerson(PersonDetails details){
        repository.AddPerson(details);
    }

    public void RemovePerson(PersonDetails details){
        repository.RemovePerson(details);
    }

    public void UpdatePerson(PersonDetails details){
        repository.UpdatePerson(details);
    }

}
