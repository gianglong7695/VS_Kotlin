package com.vtv.sports.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.vtv.sports.R
import com.vtv.sports.databinding.ActivityMainBinding
import com.vtv.sports.model.menu.MenuLeft
import com.vtv.sports.model.menu.MenuRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.*
import com.vtv.sports.view.adapter.MenuAdapter
import com.vtv.sports.view.adapter.PagerMainAdapter
import com.vtv.sports.view.custom.NonSwipeableViewPager
import com.vtv.sports.view.listener.IFragmentCallBack
import kotlinx.android.synthetic.main.layout_content_main.view.*
import kotlinx.android.synthetic.main.layout_navigation_menu.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class MainActivity : AppCompatActivity(), IFragmentCallBack {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private var isDrawerInitialized = false
    private var menuList = mutableListOf<MenuLeft>()
    private var menuAdapter: MenuAdapter? = null
    private lateinit var pagerMain: NonSwipeableViewPager
    private lateinit var pagerAdapter: PagerMainAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private fun init() {
        setSupportActionBar(binding.layoutContent.toolbar_main)
        supportActionBar?.setHomeButtonEnabled(true)
        drawerToggle = object : ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.layoutContent.toolbar_main,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(view: View) {
                super.onDrawerOpened(view)
                if (!isDrawerInitialized) {
                    initDrawerMenu()
                }
            }
        }


        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        pagerMain = binding.layoutContent.pager_main
        pagerAdapter = PagerMainAdapter(supportFragmentManager, this)
        tabLayout = binding.layoutContent.tablayout_main

        pagerMain.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pagerMain)
        pagerMain.offscreenPageLimit = Constant.arrTabIconsDefault.size

        for (i in 0..tabLayout.tabCount) {
            tabLayout.getTabAt(i)?.customView = pagerAdapter.getTabViewDefault(i)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                pagerAdapter.setTabState(tabLayout, tab!!.position, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pagerAdapter.setTabState(tabLayout, tab!!.position, true)
                binding.layoutContent.text_title.text = resources.getStringArray(R.array.arrTabTitle)[tab!!.position]

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })

        //Set current tab
        setCurrentTabDefault(Constant.TAB_DEFAULT)

    }

    private fun initDrawerMenu() {
        var recyclerMenu = binding.layoutNavigation.recycler_menu as RecyclerView

        var call: Call<MenuRespone> = BaseService.getService().getMenuLeft(ApiConstant.SECRET_KEY)
        call.enqueue(object : Callback<MenuRespone> {
            override fun onResponse(call: Call<MenuRespone>, response: Response<MenuRespone>) {
                if (response.isSuccessful) {
                    menuList.addAll(response.body()!!.menuLeft)
                    // Add menu static items
                    addMenuItemStatic()

                    menuAdapter = MenuAdapter(applicationContext, menuList)
                    recyclerMenu.layoutManager = Utils.getLayoutManagerVer(applicationContext)
                    recyclerMenu.adapter = menuAdapter
                    isDrawerInitialized = true
                }
            }

            override fun onFailure(call: Call<MenuRespone>, t: Throwable) {
                ToastUtil(applicationContext, "onFailure")
                addMenuItemStatic()
            }
        })



    }

    fun setCurrentTabDefault(pos: Int) {
        pagerAdapter.setTabState(tabLayout, pos, true)
        pagerMain.currentItem = Constant.TAB_DEFAULT
        binding.layoutContent.text_title.text = resources.getStringArray(R.array.arrTabTitle)[pos]
    }


    fun addMenuItemStatic() {
        menuList.add(
                0,
                MenuLeft(
                        Constant.MENU_ID_BREAKING_NEWS,
                        Constant.MENU_NAME_BREAKING_NEWS,
                        "",
                        R.drawable.ic_breaking_news
                )
        )

        menuList.add(
                MenuLeft(
                        Constant.MENU_ID_SAVING_NEWS,
                        Constant.MENU_NAME_SAVING_NEWS,
                        "",
                        R.drawable.ic_bookmark_black_24dp
                )
        )

        menuList.add(
                MenuLeft(
                        Constant.MENU_ID_CONTACT,
                        Constant.MENU_NAME_CONTACT,
                        "",
                        R.drawable.ic_contact_blue_large
                )
        )

        menuList.add(
                MenuLeft(
                        Constant.MENU_ID_WEBSITE,
                        Constant.MENU_NAME_WEBSITE,
                        "",
                        R.drawable.ic_arrow_forward_black_24dp
                )
        )

        if (menuAdapter == null) {
            menuAdapter = MenuAdapter(applicationContext, menuList)
            binding.layoutNavigation.recycler_menu.layoutManager = Utils.getLayoutManagerVer(applicationContext)
            binding.layoutNavigation.recycler_menu.adapter = menuAdapter
            isDrawerInitialized = true
        }
    }


    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onAddFragment(frm: Fragment, style: Int) {
        if (NetworkUtil.isNetworkAvailable(this)) {
            FragmentUtil.addFragment(this, R.id.frame_main, frm, true, FragmentUtil.SLIDE_RIGHT)
        }else{
            ToastUtil(this, Constant.NO_CONNECT)
        }
    }


}
