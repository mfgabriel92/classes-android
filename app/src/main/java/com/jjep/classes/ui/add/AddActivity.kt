package com.jjep.classes.ui.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jjep.classes.R

class AddActivity : AppCompatActivity() {
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mToolbar = findViewById(R.id.add_activity_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.new_schedule_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
