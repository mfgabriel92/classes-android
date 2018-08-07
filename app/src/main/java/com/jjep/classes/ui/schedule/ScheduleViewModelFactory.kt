package com.jjep.classes.ui.schedule

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class ScheduleViewModelFactory(private val context: Context, val date: String?, val id: Int?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return ScheduleViewModel(context, date, id) as T
    }
}