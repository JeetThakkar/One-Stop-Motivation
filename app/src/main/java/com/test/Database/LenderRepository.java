package com.test.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LenderRepository {

    private LendersDetailDao dao;
    private LiveData<List<LendersDetail>> detailsList;

    public LenderRepository(final Context context){

        AppDatabase database = AppDatabase.getInstance(context);
        dao = database.dao();
        detailsList = dao.getPersons();

    }

    public LiveData<List<LendersDetail>> getDetailsList(){
        return detailsList;
    }

    public void AddPerson(LendersDetail details){
        dao.AddPerson(details);
    }

    public void RemovePerson(LendersDetail details){
        dao.RemovePerson(details);
    }

    public void UpdatePerson(LendersDetail details){
        dao.UpdatePerson(details);
    }

    public LiveData<LendersDetail> getLenderById(int id){
        return dao.getLenderById(id);
    }

}
