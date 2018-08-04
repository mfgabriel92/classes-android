package com.jjep.classes.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.jjep.classes.R
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.ui.add.AddActivity

class MainActivity : AppCompatActivity(), ClassesAdapter.OnItemClickListener {
    private var mToolbar: Toolbar? = null
    private var mCalendar: CalendarView? = null
    private var mRvClasses: RecyclerView? = null
    private var mAdapter: ClassesAdapter? = null
    private var mTvNoClasses: TextView? = null
    private var mImgNoClasses: ImageView? = null
    private var mPbLoading: ProgressBar? = null
    private var mDb: AppDatabase? = null

    private var mSelectedYear: Int? = null
    private var mSelectedMonth: Int? = null
    private var mSelectedDay: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.my_classes_title)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add_new -> {
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra("DAY", mSelectedDay.toString())
                intent.putExtra("MONTH", mSelectedMonth.toString())
                intent.putExtra("YEAR", mSelectedYear.toString())
                startActivity(intent)
            }
        }

        return true
    }

    override fun onItemClick(id: Int) {
        Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
    }

    /**
     * Initializes the variables and listeners
     */
    private fun init() {
        mAdapter = ClassesAdapter(this, this)
        mRvClasses = findViewById(R.id.rv_classes)
        mRvClasses?.layoutManager = LinearLayoutManager(this)
        mRvClasses?.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        mRvClasses?.adapter = mAdapter

        mDb = AppDatabase.getInstance(applicationContext)

        mCalendar = findViewById(R.id.calendar)
        mCalendar?.setOnDateChangeListener { _, y, m, d ->
            mSelectedYear = y
            mSelectedMonth = m
            mSelectedDay = d
        }

        mTvNoClasses = findViewById(R.id.tv_no_classes)
        mImgNoClasses = findViewById(R.id.img_no_classes)
        mPbLoading = findViewById(R.id.pb_loading)
    }
}
