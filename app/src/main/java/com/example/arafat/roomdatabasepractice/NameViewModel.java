package com.example.arafat.roomdatabasepractice;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NameViewModel extends AndroidViewModel {

    private DatabaseRepository databaseRepository;
    private LiveData<List<Name>> allName;

    public  NameViewModel(@NonNull Application application) {
        super(application);
        databaseRepository = new DatabaseRepository(application);
        allName = databaseRepository.getAllName();
    }

    LiveData<List<Name>> getAllName() {
        return allName;
    }

    void insert(Name name) {
        databaseRepository.insert(name);
    }
}
