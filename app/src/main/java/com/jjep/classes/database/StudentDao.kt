package com.jjep.classes.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getStudents() : LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Student)
}