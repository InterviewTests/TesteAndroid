package com.avanade.santander.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.avanade.santander.fundos.domain.model.Screen;

import java.util.List;

/**
 * Data Access Object for the tasks table.
 */
@Dao
public interface FundosDao {

    /**
     * Select all tasks from the tasks table.
     *
     * @return all tasks.
     */
    @Query("SELECT * FROM screen")
    List<Screen> getTasks();


    /**
     * Insert a task in the database. If the task already exists, replace it.
     *
     * @param task the task to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Screen task);

    /**
     * Update a task.
     *
     * @param task task to be updated
     * @return the number of tasks updated. This should always be 1.
     */
    @Update
    int updateTask(Screen task);



    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM Screen")
    void deleteTasks();

}

