package com.jjep.classes.ui.student

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.jjep.classes.R
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Student
import kotlin.concurrent.thread

class AddStudentActivity : AppCompatActivity() {
    private var mToolbar: Toolbar? = null
    private var mEdtStudentName: EditText? = null
    private var mDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        mToolbar = findViewById(R.id.add_student_activity_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.new_student_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mEdtStudentName = findViewById(R.id.edt_student_name)
        mDb = AppDatabase.getInstance(applicationContext)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun onClickSaveStudentButton(view: View?) {
        val studentName: String = mEdtStudentName?.text.toString()
        val studentEntry = Student(null, studentName)

        thread { mDb?.studentsDao()?.insert(studentEntry) }

        finish()
    }
}
