package com.jjep.classes.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class ScheduleViewModelFactory(private val context: Context, val date: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return ScheduleViewModel(context, date) as T
    }
}