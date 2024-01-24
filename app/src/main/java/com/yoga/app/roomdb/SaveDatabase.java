package com.yoga.app.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Save.class},version = 2)
public abstract class SaveDatabase extends RoomDatabase {
    public abstract SaveDao getDao();
    public static SaveDatabase INSTANCE;

    public static SaveDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, SaveDatabase.class,"saveDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
