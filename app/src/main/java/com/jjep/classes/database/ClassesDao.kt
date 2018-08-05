package com.jjep.classes.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ClassesDao {
    @Query("SELECT * FROM classes WHERE date = :date")
    fun getClassesByDate(date: String): LiveData<List<Classes>>

    @Query("SELECT * FROM classes WHERE id = :id")
    fun getClassById(id: Int): LiveData<Classes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Classes)
}