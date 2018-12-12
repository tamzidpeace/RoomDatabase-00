package com.example.arafat.roomdatabasepractice;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class DatabaseRepository {

    private NameDao nameDao;
    private LiveData<List<Name>> allName;

    DatabaseRepository(Application application) {
        NameDatabase db = NameDatabase.getDatabase(application);
        nameDao = db.nameDao();
        allName = nameDao.getAllNames();
    }

    // wrapper for gerAllName()
    LiveData<List<Name>> getAllName() {
        return allName;
    }

    // wrapper for insert()
    void insert(Name name) {
        new insertAsyncTask(nameDao).execute(name);
    }

    private static class insertAsyncTask extends AsyncTask<Name, Void, Void> {

        private NameDao asyncTaskDao;

        insertAsyncTask(NameDao dao) {
            asyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Name... names) {
            asyncTaskDao.insert(names[0]);
            return null;
        }
    }
}
