package com.spotify.test.Database;

import android.app.Person;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDetailsDao {

    @Query("SELECT * FROM PersonDetails")
    LiveData<List<PersonDetails>> getPersons();

    @Insert
    void AddPerson(PersonDetails details);

    @Delete
    void RemovePerson(PersonDetails details);

    @Update
    void UpdatePerson(PersonDetails details);

}
