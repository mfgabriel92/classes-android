package com.jjep.classes.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.jjep.classes.R
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes
import com.jjep.classes.ui.add.AddActivity
import com.jjep.classes.util.Constants
import com.jjep.classes.util.DateUtils

class MainActivity : AppCompatActivity(), ClassesAdapter.OnItemClickListener {
    private var mToolbar: Toolbar? = null
    private var mCalendar: CalendarView? = null
    private var mRvClasses: RecyclerView? = null
    private var mAdapter: ClassesAdapter? = null
    private var mTvNoClasses: TextView? = null
    private var mImgNoClasses: ImageView? = null
    private var mPbLoading: ProgressBar? = null
    private var mDb: AppDatabase? = null

    private var mSelectedDate: String? = null

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
                intent.putExtra(Constants.EXTRA_STRING_DATE, mSelectedDate)
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
        mRvClasses?.isNestedScrollingEnabled = false
        mRvClasses?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRvClasses?.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        mRvClasses?.adapter = mAdapter

        mDb = AppDatabase.getInstance(applicationContext)

        mTvNoClasses = findViewById(R.id.tv_no_classes)
        mImgNoClasses = findViewById(R.id.img_no_classes)
        mPbLoading = findViewById(R.id.pb_loading)

        fetchClasses()
    }

    /**
     * Fetch the classes according to the selected day of the calendar
     */
    private fun fetchClasses() {
        mSelectedDate = DateUtils.today(applicationContext)
        Log.d("Main", mSelectedDate)

        val mModelFactory = MainViewModelFactory(applicationContext, mSelectedDate!!)
        val mClassesViewModel = ViewModelProviders.of(this, mModelFactory).get(MainViewModel::class.java)

        mCalendar = findViewById(R.id.calendar)
        mCalendar?.setOnDateChangeListener { _, y, m, d ->
            val year = y.toString()
            val month = (m + 1).toString()
            val day = d.toString()

            mSelectedDate = DateUtils.convertToDate(year, month, day)
            mClassesViewModel.setDate(mSelectedDate!!)
        }

        mClassesViewModel.classes.observe(this, Observer<List<Classes>> {
            if (it!!.isEmpty())
                displayEmptyList()
            else
                displayClassesList()

            mAdapter?.setClasses(it)
        })
    }

    /**
     * Show a funny image indicating there are no classes for the selected day
     */
    private fun displayEmptyList() {
        mRvClasses?.visibility = View.GONE
        mTvNoClasses?.visibility = View.VISIBLE
        mImgNoClasses?.visibility = View.VISIBLE
    }

    /**
     * Show the list of classes for the selected day
     */
    private fun displayClassesList() {
        mRvClasses?.visibility = View.VISIBLE
        mTvNoClasses?.visibility = View.GONE
        mImgNoClasses?.visibility = View.GONE
    }
}
