package com.jjep.classes.ui.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.jjep.classes.R
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes
import com.jjep.classes.util.Constants
import kotlin.concurrent.thread

class AddActivity : AppCompatActivity() {
    private var mToolbar: Toolbar? = null
    private var mEdtStudentName: EditText? = null
    private var mEdtStudentObs: EditText? = null
    private var mEdtTime: EditText? = null

    private var mDate: String? = null
    private var mDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

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
     * Saves into the database the new schedule
     */
    fun onClickSaveButton(view: View?) {
        val studentName: String = mEdtStudentName?.text.toString()
        val studentObs: String = mEdtStudentObs?.text.toString()
        val time: String = mEdtTime?.text.toString()
        val classEntry = Classes(null, studentName, studentObs, mDate, time)

        thread { mDb?.classesDao()?.insert(classEntry) }

        finish()
    }

    /**
     * Initializes the variables and listeners
     */
    private fun init() {
        mDb = AppDatabase.getInstance(applicationContext)

        mDate = intent.getStringExtra(Constants.EXTRA_STRING_DATE)

        mEdtStudentName = findViewById(R.id.edt_student_name)
        mEdtStudentObs = findViewById(R.id.edt_student_obs)
        mEdtTime = findViewById(R.id.edt_time)
    }
}
