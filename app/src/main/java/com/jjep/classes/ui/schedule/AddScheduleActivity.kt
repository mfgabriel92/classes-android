package com.jjep.classes.ui.schedule

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import com.jjep.classes.R
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Schedule
import com.jjep.classes.database.Student
import com.jjep.classes.ui.student.AddStudentActivity
import com.jjep.classes.ui.student.StudentViewModel
import com.jjep.classes.ui.student.StudentViewModelFactory
import com.jjep.classes.util.Constants
import com.jjep.classes.util.TimeUtils
import java.util.*
import kotlin.concurrent.thread

class AddScheduleActivity : AppCompatActivity() {
    private var mToolbar: Toolbar? = null
    private var mSpinnerStudentName: Spinner? = null
    private var mEdtStudentObs: EditText? = null
    private var mEdtTime: EditText? = null
    private var mDate: String? = null
    private var mBtnDeleteSchedule: ImageButton? = null
    private var mStudentAdapter: StudentSpinner? = null
    private var mStudentName: String? = null
    private var mStudentPos: Int? = null
    private var mDb: AppDatabase? = null
    private var mScheduleId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        init()

        mToolbar = findViewById(R.id.add_activity_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.new_schedule_title, mDate)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Goes to the screen for adding a new student
     */
    fun onClickAddStudent(view: View?) {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivity(intent)
    }

    /**
     * Saves into the database the new schedule
     */
    fun onClickSaveClassButton(view: View?) {
        val studentObs: String = mEdtStudentObs?.text.toString()
        val time: String = mEdtTime?.text.toString()

        thread {
            if (mScheduleId != null && mScheduleId != -1) {
                val scheduleEntry = Schedule(mScheduleId, mStudentName, studentObs, mDate, time, mStudentPos)
                mDb?.scheduleDao()?.update(scheduleEntry)
            } else {
                val scheduleEntry = Schedule(null, mStudentName, studentObs, mDate, time, mStudentPos)
                mDb?.scheduleDao()?.insert(scheduleEntry)
            }
        }

        finish()
    }

    /**
     * Deletes a schedule
     */
    fun onClickDeleteSchedule(view: View?) {
        thread {
            val schedule = Schedule(mScheduleId, null, null, null, null, null)
            mDb?.scheduleDao()?.delete(schedule)
        }

        finish()
    }

    /**
     * Initializes the variables and listeners
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        mSpinnerStudentName = findViewById(R.id.spinner_student_name)
        mEdtStudentObs = findViewById(R.id.edt_student_obs)
        mEdtTime = findViewById(R.id.edt_time)
        mEdtTime?.setOnTouchListener { _, motionEvent -> openTimer(motionEvent); true}
        mBtnDeleteSchedule = findViewById(R.id.btn_delete_schedule)

        mScheduleId = intent.getIntExtra(Constants.EXTRA_INT_SCHEDULE_ID, -1)
        mDb = AppDatabase.getInstance(applicationContext)
        mDate = intent.getStringExtra(Constants.EXTRA_STRING_DATE)
        mSpinnerStudentName?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mStudentName = mStudentAdapter?.getItem(position)?.name
                mStudentPos = position
            }
        }

        populateStudentsSpinner()

        if (intent != null && intent.hasExtra(Constants.EXTRA_INT_SCHEDULE_ID)) {
            mBtnDeleteSchedule?.visibility = View.VISIBLE

            val scheduleModelFactory = ScheduleViewModelFactory(applicationContext, null, mScheduleId!!)
            val scheduleViewModel = ViewModelProviders.of(this, scheduleModelFactory).get(ScheduleViewModel::class.java)

            scheduleViewModel.getSchedule().observe(this, object : Observer<Schedule> {
                override fun onChanged(schedule: Schedule?) {
                    scheduleViewModel.getSchedule().removeObserver(this)
//                    mSpinnerStudentName?.setSelection(schedule!!._student_pos!!)
                    mEdtStudentObs?.setText(schedule!!.studentObs)
                    mEdtTime?.setText(schedule!!.time)
                }
            })
        }
    }

    /**
     * Populates the drop-down with the students name from the database
     */
    private fun populateStudentsSpinner() {
        val studentViewModelFactory = StudentViewModelFactory(applicationContext)
        val studentViewModel = ViewModelProviders.of(this, studentViewModelFactory).get(StudentViewModel::class.java)

        studentViewModel.getStudents().observe(this, Observer<List<Student>> {
            mStudentAdapter = StudentSpinner(applicationContext, android.R.layout.simple_spinner_item, it!!)
            mStudentAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            mSpinnerStudentName?.adapter = mStudentAdapter
        })
    }

    /**
     * Open the dialog to choose the time of the class
     */
    private fun openTimer(motionEvent: MotionEvent) {
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val calendar: Calendar = Calendar.getInstance()
            val calendarHour = calendar.get(Calendar.HOUR)
            val calendarMinute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, h, m ->
                val time = TimeUtils.get12HoursTimeFormat(this, h, m)
                mEdtTime?.setText(time)
            }, calendarHour, calendarMinute, false).show()
        }
    }
}
