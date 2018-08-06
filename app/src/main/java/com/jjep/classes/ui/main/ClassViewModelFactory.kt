package com.jjep.classes.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context

class ClassViewModelFactory(private val context: Context, val date: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return ClassViewModel(context, date) as T
    }
}