package com.jjep.classes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Classes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun classesDao(): ClassesDao

    companion object {
        private var sInstance: AppDatabase? = null
        private var database: String = "db_classes"

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

        fun destroyInstance() {
            sInstance = null
        }
    }
}