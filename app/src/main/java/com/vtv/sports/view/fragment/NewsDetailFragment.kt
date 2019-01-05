package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.google.gson.Gson
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentNewsDetailBinding
import com.vtv.sports.model.detail.DetailRespone
import com.vtv.sports.model.news.News
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.adapter.NewsDetailAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Tab1
 */

class NewsDetailFragment : BaseFragment() {
    lateinit var news: News
    lateinit var binding: FragmentNewsDetailBinding
    lateinit var adapter: NewsDetailAdapter

    companion object {
        fun newInstance(news: String): NewsDetailFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_STRING_OBJECT, news)
            val fragment = NewsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_news_detail
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentNewsDetailBinding

        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(context!!, R.color.red),
            ContextCompat.getColor(context!!, R.color.green),
            ContextCompat.getColor(context!!, R.color.blue)
        )

        binding.recyclerDetail.layoutManager = Utils.getLayoutManagerVer(context!!)

        val obj = arguments!!.getString(Constant.KEY_STRING_OBJECT)
        news = Gson().fromJson(obj, News::class.java)
        adapter = NewsDetailAdapter(context!!, news)
        binding.recyclerDetail.adapter = adapter
    }

    override fun initData() {
        fetchData()
    }


    fun fetchData() {
        showRefresh()
        val call = BaseService.getService().getNewsDetail(ApiConstant.SECRET_KEY, news.newsId)
        call.enqueue(object : Callback<DetailRespone> {
            override fun onResponse(call: Call<DetailRespone>, response: Response<DetailRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.news != null) {
                    adapter.updateData(response.body()!!.news)
                }
            }

            override fun onFailure(call: Call<DetailRespone>, t: Throwable) {
                hideRefresh()
                Logs.e(t.toString())
            }
        })
    }

    private fun showRefresh() {
        if (!binding.swipeRefresh.isRefreshing) 5
        binding.swipeRefresh.isRefreshing = true
    }

    private fun hideRefresh() {
        binding.swipeRefresh.isRefreshing = false

    }

}
