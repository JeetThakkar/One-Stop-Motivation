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

    @Insert
    void AddPerson(LendersDetail details);

    @Delete
    void RemovePerson(LendersDetail details);

    @Update
    void UpdatePerson(LendersDetail details);

}
