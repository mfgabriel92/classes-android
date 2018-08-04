package com.jjep.classes.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private var classes: LiveData<List<Classes>>?  = null
    private var db: AppDatabase? = null

    init {
        db = AppDatabase.getInstance(this.getApplication())
        classes = db?.classesDao()?.getAllClasses()
    }

    fun getClasses() : LiveData<List<Classes>> {
        return classes!!
    }
}