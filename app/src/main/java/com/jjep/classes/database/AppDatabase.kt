package com.jjep.classes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.jjep.classes.database.util.DateConverter

@Database(entities = [Classes::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var obj = Object()
        private var database: String = "db_classes"
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            if (sInstance == null) {
                synchronized(obj) {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        database
                    ).build()
                }
            }

            return sInstance!!
        }
    }

    abstract fun classesDao() : ClassesDao
}