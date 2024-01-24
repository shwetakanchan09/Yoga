package com.yoga.app.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SaveDao {
    @Insert
    void Insert(Save save);
    @Update
    void Update(Save save);
    @Query("delete from Save where id= :id")
    void delete(double id);

    @Query("Select * from Save")
    List<Save> getAllVideo();
}
