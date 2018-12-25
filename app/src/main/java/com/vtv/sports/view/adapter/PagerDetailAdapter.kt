package com.vtv.sports.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.google.gson.Gson
import com.vtv.sports.model.news.News
import com.vtv.sports.view.fragment.NewsDetailFragment

/**
 * Created by Giang Long on 12/24/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class PagerDetailAdapter(fm: FragmentManager?, listNews: List<News>) : FragmentStatePagerAdapter(fm) {
    var listNews: List<News> = listNews

    override fun getItem(pos: Int): Fragment {
        return NewsDetailFragment.newInstance(Gson().toJson(listNews[pos]))
    }

    override fun getCount(): Int {
        if (listNews != null)
            return listNews.size
        return 0
    }


}