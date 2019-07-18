package com.droid.hp.screen.container

import android.os.Bundle
import com.droid.hp.R
import com.droid.hp.screen.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)

        if (savedInstanceState == null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.addToBackStack(ContainerFragment.TAG)
            val fragment = ContainerFragment()
            ft.add(R.id.container, fragment, ContainerFragment.TAG).commit()
        }
    }
}
