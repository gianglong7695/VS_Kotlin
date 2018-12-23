package com.vtv.sports.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vtv.sports.model.news.Zone
import com.vtv.sports.view.fragment.LiveScoreFragment
import com.vtv.sports.view.fragment.NewsZoneFragment

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerNewsAdapter(fm: FragmentManager?, context: Context?, listZone: MutableList<Zone>) :
    FragmentStatePagerAdapter(fm) {
    private var listZone: MutableList<Zone>

    init {
        this.listZone = listZone
    }

    override fun getItem(pos: Int): Fragment {
        return NewsZoneFragment.newInstance(listZone.get(pos).id.toString())
    }

    override fun getCount(): Int {
        if (listZone != null) return listZone.size else return 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listZone.get(position).name
    }
}