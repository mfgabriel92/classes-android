package com.jjep.classes.ui.schedule

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jjep.classes.database.Student

class StudentSpinner(context: Context, res: Int, students: List<Student>) : ArrayAdapter<Student>(context, res, students) {
    private var mRes: Int? = null
    private var mStudents: List<Student>? = null

    init {
        this.mStudents = students
    }

    override fun getCount(): Int {
        return mStudents!!.size
    }

    override fun getItem(position: Int): Student {
        return mStudents!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val name: TextView = super.getView(position, convertView, parent) as TextView
        name.text = mStudents!![position].name

        return name
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val name: TextView = super.getDropDownView(position, convertView, parent) as TextView
        name.text = mStudents!![position].name

        return name
    }
}