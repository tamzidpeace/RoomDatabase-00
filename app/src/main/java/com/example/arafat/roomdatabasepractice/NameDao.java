package com.example.arafat.roomdatabasepractice;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// necessary queries for database
@Dao
public interface NameDao {

    @Insert
    void insert(Name name);

    @Delete
    void delete(Name name);

    @Query("delete from name_table")
    void deleteAll();

    @Query("select * from name_table")
    LiveData<List<Name>> getAllNames();
}
