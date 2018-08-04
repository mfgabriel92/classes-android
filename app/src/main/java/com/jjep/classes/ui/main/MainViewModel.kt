package com.jjep.classes.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes
import java.util.*

class MainViewModel(app: Application, date: Date) : AndroidViewModel(app) {
    private var classes: LiveData<List<Classes>>?  = null
    private var db: AppDatabase? = null

    init {
        db = AppDatabase.getInstance(this.getApplication())
        classes = db?.classesDao()?.getClassesByDate(date)
    }

    fun getClasses() : LiveData<List<Classes>> {
        return classes!!
    }
}