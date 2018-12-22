package com.vtv.sports.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vtv.sports.R
import com.vtv.sports.databinding.ActivityMainBinding
import com.vtv.sports.util.Constant
import com.vtv.sports.view.adapter.PagerMainAdapter
import kotlinx.android.synthetic.main.layout_content_main.view.*

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(binding.layoutContent.toolbar_main)
        supportActionBar?.setHomeButtonEnabled(true)
        drawerToggle = ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.layoutContent.toolbar_main,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        var pagerMain = binding.layoutContent.pager_main
        var pagerAdapter = PagerMainAdapter(supportFragmentManager, this)
        var tabLayout = binding.layoutContent.tablayout_main

        pagerMain.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pagerMain)

        for (i in 0..tabLayout.tabCount){
            tabLayout.getTabAt(i)?.setCustomView(pagerAdapter.getTabView(i))
        }


        Toast.makeText(this, "ahihi", Toast.LENGTH_SHORT).show()

    }


}
