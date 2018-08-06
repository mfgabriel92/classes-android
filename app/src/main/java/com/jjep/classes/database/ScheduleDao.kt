package com.jjep.classes.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedules WHERE date = :date")
    fun getClassesByDate(date: String): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedules WHERE id = :id")
    fun getClassById(id: Int): LiveData<Schedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Schedule)
}