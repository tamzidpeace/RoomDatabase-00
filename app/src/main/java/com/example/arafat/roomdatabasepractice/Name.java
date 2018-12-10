package com.example.arafat.roomdatabasepractice;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// making of table in this class

@Entity(tableName = "name_table")
public class Name {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String first_name;
    private String last_name;

    public Name(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

}
