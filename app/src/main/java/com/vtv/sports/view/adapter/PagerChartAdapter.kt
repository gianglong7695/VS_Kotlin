package com.vtv.sports.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vtv.sports.model.chart.StandingCategory
import com.vtv.sports.view.fragment.ChartZoneFragment

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerChartAdapter(fm: FragmentManager?, listZone: List<StandingCategory>) :
    FragmentStatePagerAdapter(fm) {
    private var listZone: List<StandingCategory> = listZone

    override fun getItem(pos: Int): Fragment {
        return ChartZoneFragment.newInstance(listZone[pos].categoryId.toString())
    }

    override fun getCount(): Int {
        return listZone.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listZone[position].name
    }
}