package com.vtv.sports.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vtv.sports.model.news.Zone
import com.vtv.sports.model.score.ScheduleCategory
import com.vtv.sports.view.fragment.LiveScoreZoneFragment
import com.vtv.sports.view.fragment.NewsZoneFragment

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerScoreAdapter(fm: FragmentManager?, listZone: List<ScheduleCategory>) :
    FragmentStatePagerAdapter(fm) {
    private var listZone: List<ScheduleCategory> = listZone

    override fun getItem(pos: Int): Fragment {
        return LiveScoreZoneFragment.newInstance(listZone[pos].id.toString())
    }

    override fun getCount(): Int {
        return listZone.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listZone[position].name
    }
}