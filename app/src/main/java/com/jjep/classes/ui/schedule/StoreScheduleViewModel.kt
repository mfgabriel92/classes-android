//package com.jjep.classes.ui.schedule
//
//import android.arch.lifecycle.LiveData
//import android.arch.lifecycle.MutableLiveData
//import android.arch.lifecycle.Transformations
//import android.arch.lifecycle.ViewModel
//import android.content.Context
//import com.jjep.classes.database.AppDatabase
//import com.jjep.classes.database.Schedule
//
//class StoreScheduleViewModel(context: Context, id: String) : ViewModel() {
//    private var db: AppDatabase? = null
//    private val chosenDate = MutableLiveData<String>()
//    val schedule: LiveData<List<Schedule>>
//
//    init {
//        db = AppDatabase.getInstance(context.applicationContext)
//        this.chosenDate.value = date
//        schedule = Transformations.switchMap(this.chosenDate) {
//            db?.scheduleDao()?.getSchedules(it)
//        }
//    }
//
//    fun setDate(date: String) {
//        this.chosenDate.value = date
//    }
//}