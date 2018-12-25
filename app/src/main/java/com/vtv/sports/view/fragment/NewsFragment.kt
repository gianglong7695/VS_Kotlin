package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentNewsBinding
import com.vtv.sports.model.news.NewsRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Logs
import com.vtv.sports.view.adapter.PagerNewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Tab1
 */

class NewsFragment : BaseFragment() {


    lateinit var pagerAdapter: PagerNewsAdapter
    lateinit var binding: FragmentNewsBinding
    var isLoaded = false


    override fun getLayoutRes(): Int {
        return R.layout.fragment_news
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentNewsBinding
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && isResumed)
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }


    }

    override fun initData() {
        if (userVisibleHint) { // fragment is visible
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }
        }
    }

    private fun fetchData() {
        val call = BaseService.getService().getNewsHome(ApiConstant.SECRET_KEY)
        call.enqueue(object : Callback<NewsRespone> {
            override fun onResponse(call: Call<NewsRespone>, response: Response<NewsRespone>) {
                if (response.isSuccessful && response.body()?.childZone != null) {
                    pagerAdapter =
                            PagerNewsAdapter(fragmentManager, response.body()!!.childZone.toMutableList())
                    binding.pagerNews.offscreenPageLimit = 1
                    binding.pagerNews.adapter = pagerAdapter
                    binding.tabLayoutNews.setupWithViewPager(binding.pagerNews)

                }
            }

            override fun onFailure(call: Call<NewsRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }

}
