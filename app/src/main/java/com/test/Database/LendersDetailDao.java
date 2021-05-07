package com.test.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LendersDetailDao {

    @Query("SELECT * FROM LendersDetail")
    LiveData<List<LendersDetail>> getPersons();

    @Query("SELECT * FROM LendersDetail WHERE Id = :lenderId")
    LiveData<LendersDetail> getLenderById(int lenderId);

    @Insert
    void AddPerson(LendersDetail details);

    @Delete
    void RemovePerson(LendersDetail details);

    @Update
    void UpdatePerson(LendersDetail details);

}
