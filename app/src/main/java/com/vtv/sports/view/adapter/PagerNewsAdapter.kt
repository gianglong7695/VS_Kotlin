package com.vtv.sports.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.vtv.sports.model.news.Zone
import com.vtv.sports.view.fragment.NewsZoneFragment

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerNewsAdapter(fm: FragmentManager?, listZone: MutableList<Zone>) :
        FragmentStatePagerAdapter(fm) {
    private var listZone: MutableList<Zone> = listZone

    init {
        listZone.add(0, Zone(
                392,
                "Thể thao",
                "",
                392
        ))
    }

    override fun getItem(pos: Int): Fragment {
        return NewsZoneFragment.newInstance(listZone[pos].id.toString())
    }

    override fun getCount(): Int {
        return listZone.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listZone[position].name
    }
}