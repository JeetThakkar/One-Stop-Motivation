package com.spotify.test.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PersonDetails")
public class PersonDetails {

    @PrimaryKey(autoGenerate = true)
    public int Id;

    public String FirstName;

    public String LastName;

    public String Email;

    public PersonDetails(String FirstName, String LastName, String Email) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
