package com.jjep.classes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.jjep.classes.database.Classes

class ClassesAdapter(context: Context, onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ClassesAdapter.ClassViewHolder>() {
    private var mContext: Context? = null
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mList = listOf<Classes>()

    init {
        mContext = context
        mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesAdapter.ClassViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.class_item, parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mList != null) mList.size else 0
    }

    override fun onBindViewHolder(holder: ClassesAdapter.ClassViewHolder, position: Int) {
        val classEntry: Classes = mList[position]
        val studentName: String = classEntry.studentName!!
        val studentObs: String = classEntry.studentObs!!
        val classTime: String = classEntry.classTime!!

        holder.tvStudentName?.text = studentName
        holder.tvStudentObs?.text = studentObs
        holder.tvClassTime?.text = classTime
    }

    fun setClasses(classes: List<Classes>) {
        mList = classes
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