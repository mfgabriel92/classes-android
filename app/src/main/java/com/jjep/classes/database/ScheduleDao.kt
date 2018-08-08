package com.jjep.classes.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedules WHERE date = :date ORDER BY time ASC")
    fun getSchedules(date: String): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun getSchedule(id: Int): LiveData<Schedule>

    @Insert
    fun insert(data: Schedule)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: Schedule)

    @Delete
    fun delete(data: Schedule)
}