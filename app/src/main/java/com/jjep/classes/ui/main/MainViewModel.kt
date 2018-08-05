package com.jjep.classes.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes

class MainViewModel(context: Context, date: String) : ViewModel() {
    private var db: AppDatabase? = null
    private val chosenDate = MutableLiveData<String>()
    val classes: LiveData<List<Classes>>

    init {
        db = AppDatabase.getInstance(context.applicationContext)
        this.chosenDate.value = date
        classes = Transformations.switchMap(this.chosenDate) {
            db?.classesDao()?.getClassesByDate(it)
        }
    }

    fun setDate(date: String) {
        this.chosenDate.value = date
    }
}