package com.jjep.classes.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jjep.classes.R
import com.jjep.classes.database.Student

class StudentSpinner(context: Context, res: Int, students: List<Student>) : ArrayAdapter<Student>(context, res, students) {
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
        return display(convertView, parent, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return display(convertView, parent, position)
    }

    private fun display(convertView: View?, parent: ViewGroup?, position: Int): View {
        var view: View? = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.tv_lst_student_name)
        name?.text = mStudents!![position].name

        val pos = view?.findViewById<TextView>(R.id.tv_lst_student_position)
        pos?.text = position.toString()

        return view!!
    }
}