package com.jjep.classes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jjep.classes.util.Constants

@Database(entities = [(Schedule::class), (Student::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = sInstance ?: synchronized(this) {
            sInstance ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constants.DATABASE_NAME
            ).build().also {
                sInstance = it
            }
        }
    }

    abstract fun scheduleDao(): ScheduleDao
    abstract fun studentsDao(): StudentDao
}