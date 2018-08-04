package com.jjep.classes.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface ClassesDao {
    @Query("SELECT * FROM classes")
    fun getAllClasses(): LiveData<List<Classes>>

    @Query("SELECT * FROM classes WHERE id = :id")
    fun getClassById(id: Int): LiveData<Classes>
}