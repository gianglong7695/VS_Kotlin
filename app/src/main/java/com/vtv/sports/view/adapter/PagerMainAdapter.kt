package com.vtv.sports.view.adapter

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.vtv.sports.R
import com.vtv.sports.util.Constant
import com.vtv.sports.view.fragment.*

/**
 * Created by Giang Long on 12/21/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerMainAdapter(fm: FragmentManager?, context: Context?) : FragmentStatePagerAdapter(fm) {
    var context: Context? = null

    init {
        this.context = context
    }

    override fun getCount(): Int {
        return Constant.TAB_SIZE
    }

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> return VideoFragment()
            1 -> return NewsFragment()
            2 -> return LiveScoreFragment()
            3 -> return TableFragment()
            else -> return BaseFragment()
        }
    }


    fun getTabViewDefault(position: Int): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_tab_layout, null)
        var textName = view.findViewById(R.id.text_tab_name) as TextView
        textName.text = context!!.resources.getStringArray(R.array.arrTabName)[position]
        textName.setTextColor(ContextCompat.getColor(context!!, R.color.white_menubar_text))


        var icon = view.findViewById(R.id.img_tab_icon) as ImageView
        icon.setImageResource(Constant.arrTabIconsDefault[position])

        return view
    }


    fun getTabViewSelected(position: Int): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_tab_layout, null)
        var textName = view.findViewById(R.id.text_tab_name) as TextView
        textName.text = context!!.resources.getStringArray(R.array.arrTabName)[position]
        textName.setTextColor(ContextCompat.getColor(context!!, R.color.white))

        var icon = view.findViewById(R.id.img_tab_icon) as ImageView
        icon.setImageResource(Constant.arrTabIconsSelected[position])

        return view
    }

    fun setTabState(tabLayout: TabLayout, pos: Int, isSelected: Boolean) {
        var view = tabLayout.getTabAt(pos)!!.customView
        var textName = view!!.findViewById(R.id.text_tab_name) as TextView
        var icon = view.findViewById(R.id.img_tab_icon) as ImageView
        if(isSelected){
            textName.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            icon.setImageResource(Constant.arrTabIconsSelected[pos])
        }else {
            textName.setTextColor(ContextCompat.getColor(context!!, R.color.white_menubar_text))
            icon.setImageResource(Constant.arrTabIconsDefault[pos])
        }
    }

}