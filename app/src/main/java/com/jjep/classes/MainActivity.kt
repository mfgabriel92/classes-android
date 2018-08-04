package com.jjep.classes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import com.jjep.classes.database.AppDatabase
import com.jjep.classes.database.Classes
import com.jjep.classes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), ClassesAdapter.OnItemClickListener {
    private var mToolbar: Toolbar? = null
    private var mCalendar: CalendarView? = null
    private var mRvClasses: RecyclerView? = null
    private var mAdapter: ClassesAdapter? = null
    private var mTvNoClasses: TextView? = null
    private var mImgNoClasses: ImageView? = null
    private var mPbLoading: ProgressBar? = null
    private var mDb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.main_activity_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.my_classes_title)

        init()
        setUpViewModel()
    }

    override fun onItemClick(id: Int) {
        Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show()
    }

    private fun init() {
        mAdapter = ClassesAdapter(this, this)
        mRvClasses = findViewById(R.id.rv_classes)
        mRvClasses?.layoutManager = LinearLayoutManager(this)
        mRvClasses?.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        mRvClasses?.adapter = mAdapter

        mDb = AppDatabase.getInstance(applicationContext)

        mCalendar = findViewById(R.id.calendar)
        mTvNoClasses = findViewById(R.id.tv_no_classes)
        mImgNoClasses = findViewById(R.id.img_no_classes)
        mPbLoading = findViewById(R.id.pb_loading)
    }

    private fun setUpViewModel() {
        mPbLoading?.visibility = View.VISIBLE

        val classesViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        classesViewModel.getClasses().observe(this, Observer<List<Classes>> {
            if (it!!.isEmpty()) {
                mRvClasses?.visibility = View.GONE
                mTvNoClasses?.visibility = View.VISIBLE
                mImgNoClasses?.visibility = View.VISIBLE
            }

            mAdapter?.setClasses(it)
        })

        mPbLoading?.visibility = View.GONE
    }
}
