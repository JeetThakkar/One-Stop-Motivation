package com.spotify.test.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonRepository {

    private PersonDetailsDao dao;
    private LiveData<List<PersonDetails>> detailsList;

    public PersonRepository(final Context context){

        AppDatabase database = AppDatabase.getInstance(context);
        dao = database.dao();
        detailsList = dao.getPersons();

    }

    public LiveData<List<PersonDetails>> getDetailsList(){
        return detailsList;
    }

    public void AddPerson(PersonDetails details){
        dao.AddPerson(details);
    }

    public void RemovePerson(PersonDetails details){
        dao.RemovePerson(details);
    }

    public void UpdatePerson(PersonDetails details){
        dao.UpdatePerson(details);
    }

}
