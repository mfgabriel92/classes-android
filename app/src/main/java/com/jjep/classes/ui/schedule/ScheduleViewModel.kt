package com.jjep.classes.ui.schedule

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Schedule

class ScheduleViewModel(context: Context, date: String?, id: Int?) : ViewModel() {
    private var db: AppDatabase? = null
    private val chosenDate = MutableLiveData<String>()
    private var schedule: LiveData<Schedule>? = null

    val schedules: LiveData<List<Schedule>>

    init {
        db = AppDatabase.getInstance(context.applicationContext)
        this.chosenDate.value = date

        if (id != null) {
            schedule = db?.scheduleDao()?.getSchedule(id)!!
        }

        schedules = Transformations.switchMap(this.chosenDate) {
            db?.scheduleDao()?.getSchedules(it)
        }
    }

    fun setDate(date: String) {
        this.chosenDate.value = date
    }

    fun getSchedule() : LiveData<Schedule> {
        return schedule!!
    }
}