package com.jjep.classes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Schedule::class, Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
    abstract fun studentsDao(): StudentDao

    companion object {
        private var sInstance: AppDatabase? = null
        private var database: String = "classes"

        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                synchronized(AppDatabase::class) {
                    sInstance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        database
                    ).build()
                }
            }

            return sInstance
        }
    }
}