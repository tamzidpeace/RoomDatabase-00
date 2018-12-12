package com.example.arafat.roomdatabasepractice;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

@Database(entities = {Name.class}, version = 1)
public abstract class NameDatabase extends RoomDatabase {

    //this is an abstract getter method to work with this database
    private static  NameDatabase instance;

    public abstract NameDao nameDao();




    public static NameDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (NameDatabase.class) {
                if (instance == null) {
                    // create database here

                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NameDatabase.class, "name_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return instance;
    }

    // creating callback method

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulatedDbAsync(instance).execute();

        }
    };

    private static class PopulatedDbAsync extends AsyncTask<Void, Void, Void> {

        private final NameDao nameDao;

        String[] myFirstName = {"Tamin", "Musfique", "Sakib", "Fizz"};
        String[] myLastName = {"Virat", "Dhoni", "Lara", "Rashid", "Gayle"};

        public PopulatedDbAsync(NameDatabase db) {
            nameDao = db.nameDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            nameDao.deleteAll();
            //nameDao.insert(new Name("Arafat", "Kamal"));
            for (int i = 0; i <myFirstName.length ; i++) {
                nameDao.insert(new Name(myFirstName[i], myLastName[i]));
            }
            return null;
        }

    }
}






/*
    <-- pattern for singleton class implementation -->

class A {
    private static A obj;

    private A() {
    }

    public static A getA() {
        if (obj == null) {
            synchronized (Singleton.class) {
                if (obj == null) {
                    obj = new Singleton();//instance will be created at request time
                }
            }
        }
        return obj;
    }

    public void doSomething() {
        //write your code
    }
}

*/


