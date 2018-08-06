package com.jjep.classes.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jjep.classes.R
import com.jjep.classes.database.Schedule

class ScheduleAdapter(context: Context, onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ScheduleAdapter.ClassViewHolder>() {
    private var mContext: Context? = null
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mList = listOf<Schedule>()

    init {
        mContext = context
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.schedule_item, parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mList != null) mList.size else 0
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classEntry: Schedule = mList[position]
        val studentName: String = classEntry.studentName!!
        val studentObs: String = classEntry.studentObs!!
        val time: String = classEntry.time!!

        holder.tvStudentName?.text = studentName
        holder.tvStudentObs?.text = studentObs
        holder.tvClassTime?.text = time
    }

    fun setClasses(schedules: List<Schedule>?) {
        mList = schedules!!
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    inner class ClassViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var tvStudentName: TextView? = null
        var tvStudentObs: TextView? = null
        var tvClassTime: TextView? = null

        init {
            tvStudentName = view.findViewById(R.id.tv_student_name)
            tvStudentObs = view.findViewById(R.id.tv_student_obs)
            tvClassTime = view.findViewById(R.id.tv_class_time)
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            mOnItemClickListener?.onItemClick(mList[adapterPosition].id!!)
        }
    }
}