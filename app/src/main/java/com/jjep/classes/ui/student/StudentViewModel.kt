package com.jjep.classes.ui.student

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Student

class StudentViewModel(context: Context) : ViewModel() {
    private var db: AppDatabase? = null
    private var students: LiveData<List<Student>>? = null

    init {
        db = AppDatabase.getInstance(context)
        students = db?.studentsDao()?.getStudents()
    }

    fun getStudents() : LiveData<List<Student>> {
        return students!!
    }
}